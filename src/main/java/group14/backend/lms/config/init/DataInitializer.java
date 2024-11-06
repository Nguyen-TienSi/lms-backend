package group14.backend.lms.config.init;

import group14.backend.lms.service.IDataInitializerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private IDataInitializerService dataInitializerService;

    @Override
    public void run(String... args) {
        dataInitializerService.init();
    }
}
