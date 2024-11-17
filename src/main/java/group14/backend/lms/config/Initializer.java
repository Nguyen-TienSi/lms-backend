package group14.backend.lms.config;

import group14.backend.lms.service.IDataInitializerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Initializer implements CommandLineRunner {
    @Autowired
    private IDataInitializerService dataInitializerService;

    @Override
    public void run(String... args) {
        dataInitializerService.init();
    }
}
