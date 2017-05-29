package saci.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import saci.backend.mood.ColorToMoodService;

/**
 * @author Stănilă Ioan, 5/28/2017.
 */
@Configuration
public class Initializer implements ApplicationContextInitializer {

    @Autowired
    public Initializer(ConfigurableApplicationContext applicationContext) {
        initialize(applicationContext);
    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ColorToMoodService service = applicationContext.getBeanFactory().getBean(ColorToMoodService.class);
        service.insert();
    }
}
