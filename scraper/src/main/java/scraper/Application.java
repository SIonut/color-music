package scraper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import scraper.song.SongService;

/**
 * @author Stănilă Ioan, 6/19/2017.
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ion\\Documents\\git\\color-music\\scraper\\chromedriver.exe");
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        SongService songService = applicationContext.getBeanFactory().getBean(SongService.class);
        songService.scrape();
        SpringApplication.exit(applicationContext);
    }
}
