package scraper.song;

import org.apache.xerces.xs.StringList;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scraper.math.Subsets;
import scraper.mood.Mood;
import scraper.mood.MoodRepository;

import java.util.*;
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
            String moodRelativeLink = subset.stream().map(Mood::getMood).reduce((a, b) -> a + "+" + b).get();
            webDriver.get(URL + moodRelativeLink);
            List<String> colors = subset.stream().map(Mood::getColor).collect(Collectors.toList());
            if (!webDriver.getCurrentUrl().equals(URL + "all")) {
                List<Song> songs = findMusicForMood(colors);
                songRepository.save(songs);
            }
            webDriver.manage().deleteAllCookies();
        });
        webDriver.quit();
    }

    private List<Song> findMusicForMood(List<String> colors) {
        List<Song> songs = new ArrayList<>();
        try {
            List<WebElement> mixUrlElements = webDriver.findElements(By.className("mix_square"));
            List<String> mixUrls = mixUrlElements.stream()
                    .map(it -> {
                        String link = it
                                .findElement(By.className("backside"))
                                .findElement(By.tagName("a")).getAttribute("href") + "&play=1";
                        webDriver.manage().timeouts().implicitlyWait(50, TimeUnit.MILLISECONDS);
                        return link;
                    })
                    .collect(Collectors.toList());
            mixUrls.forEach(mixUrl -> {
                webDriver.get(mixUrl);
                webDriver.manage().timeouts().implicitlyWait(50, TimeUnit.MILLISECONDS);

                String youtubeLink = webDriver.findElement(By.id("mix_youtube_embed")).getAttribute("src");
                webDriver.manage().timeouts().implicitlyWait(50, TimeUnit.MILLISECONDS);
                String title = webDriver.findElement(By.className("title_artist")).findElement(By.className("t")).getText();
                webDriver.manage().timeouts().implicitlyWait(50, TimeUnit.MILLISECONDS);
                String artist = webDriver.findElement(By.className("title_artist")).findElement(By.className("a")).getText();
                webDriver.manage().timeouts().implicitlyWait(50, TimeUnit.MILLISECONDS);
                if (!title.equals("")) {
                    songs.add(getBuiltSong(youtubeLink, title, artist, colors));
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return songs;
    }

    private Song getBuiltSong(String link, String title, String artist, List<String> colors) {
        Song song = new Song();
        song.setLink(link);
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