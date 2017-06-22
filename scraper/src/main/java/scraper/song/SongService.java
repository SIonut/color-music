package scraper.song;

import org.apache.xerces.xs.StringList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scraper.math.Subsets;
import scraper.mood.Mood;
import scraper.mood.MoodRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author Stănilă Ioan, 5/22/2017.
 */
@Service
public class SongService {

    private final String URL = "https://8tracks.com/explore/";

    private final MoodRepository moodRepository;
    private final SongRepository songRepository;

    private WebDriver webDriver;

    @Autowired
    public SongService(MoodRepository moodRepository, SongRepository songRepository) {
        this.moodRepository = moodRepository;
        this.songRepository = songRepository;
    }

    public void scrape() {
        List<Mood> moods = moodRepository.findAll();
        webDriver = new ChromeDriver();

        List<List<Mood>> moodSubsets = (new Subsets<>(moods)).generateSubsets();

        moodSubsets.forEach(subset -> {
            Optional<String> moodRelativeLink = subset.stream().map(it -> it.getMood()).reduce((a, b) -> a + "+" + b);
            webDriver.navigate().to(URL + moodRelativeLink);
            List<String> colors = subset.stream().map(it -> it.getColor()).collect(Collectors.toList());
            List<Song> songs = findMusicForMood(colors);
            songRepository.save(songs);
        });

        webDriver.quit();
    }

    private List<Song> findMusicForMood(List<String> colors) {
        List<Song> songs = new ArrayList<>();
        List<WebElement> mixUrls = webDriver.findElements(By.className("mix_square"));
        int playlistCount = mixUrls.size();
        int i = 0;
        while (i < playlistCount) {
            String playlistUrl = mixUrls.get(i)
                    .findElement(By.className("backside"))
                    .findElement(By.tagName("a")).getAttribute("href") + "&play=1";
            webDriver.navigate().to(playlistUrl);
            webDriver.manage().timeouts().implicitlyWait(300, TimeUnit.MILLISECONDS);

            String youtubeLink = webDriver.findElement(By.id("mix_youtube_embed")).getAttribute("src");
            webDriver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
            String title = webDriver.findElement(By.className("title_artist")).findElement(By.className("t")).getText();
            webDriver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
            String artist = webDriver.findElement(By.className("title_artist")).findElement(By.className("a")).getText();
            webDriver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
            songs.add(getBuiltSong(youtubeLink, title, artist, colors));

            webDriver.navigate().back();
            mixUrls = webDriver.findElements(By.className("mix_square"));
            i++;
        }
        return songs;
    }

    private Song getBuiltSong(String link, String title, String artist, List<String> colors) {
        Song song = new Song();
        song.setLink(cleanYouTubeLink(link));
        song.setTitle(title);
        song.setAuthor(artist);
        song.setColor(colors);
        return song;
    }

    private String cleanYouTubeLink(String raw) {
        String[] split = raw.split("\\?");
        return split[0].replace("embed/", "watch?v=");
    }
}

// Code for including each song in playlist
// Currently not working because of some iframe in the targeted website
//            WebElement skipButton = webDriver.findElement(By.id("youtube_skip_button"));
//            int tracksCount = Integer.parseInt(webDriver.findElement(By.id("tracks_count")).getAttribute("textContent").split(" ")[0]);
//            int j = 0;
//            do {
//                String youtubeLink = webDriver.findElement(By.id("mix_youtube_embed")).getAttribute("src");
//                songs.add(getBuiltSong(youtubeLink, Collections.singletonList(mood.getColor())));
//
//                skipButton.click();
//                webDriver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
//            } while (j++ < tracksCount);