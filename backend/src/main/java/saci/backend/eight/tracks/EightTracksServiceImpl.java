package saci.backend.eight.tracks;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Stănilă Ioan, 6/19/2017.
 */
@Service
public class EightTracksServiceImpl implements EightTracksService {

    @Override
    public List<String> findByMoods(List<String> moods) {
        if (moods.isEmpty()) {
            return Collections.emptyList();
        }
        String link;
        Optional<String> optional = moods.stream().reduce((a, b) -> a + "+" + b);
        if (!optional.isPresent()) {
            return Collections.emptyList();
        }
        link = optional.get();
        Document doc = getDocument("https://8tracks.com/explore/" + link + "/hot");
        Elements mixUrls = doc.getElementsByClass("quick_play");
        List<String> playlistLinks = mixUrls.stream()
                .map(it -> it.attr("abs:href"))
                .collect(Collectors.toList());
        return playlistLinks.stream()
                .filter(it -> !it.contains("explore"))
                .map(this::findYouTubeUrl)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private String findYouTubeUrl(String link) {
        Document doc = getDocument(link);
        Elements video = doc.getElementsByAttributeValue("id", "mix_youtube_embed");
        if (video == null) {
            return null;
        }
        video.forEach(it -> System.out.println(it.attr("src")));
        return video.attr("src");
    }

    private Document getDocument(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            System.out.println("IOException in " + this.getClass() + " with URL: " + url);
        }
        return doc;
    }
}
