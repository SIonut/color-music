package saci.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import saci.backend.mood.ColorToMoodService;

@SpringBootApplication
public class BackendApplication implements ApplicationContextInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ColorToMoodService service = applicationContext.getBeanFactory().getBean(ColorToMoodService.class);
        service.insert();
    }
}
