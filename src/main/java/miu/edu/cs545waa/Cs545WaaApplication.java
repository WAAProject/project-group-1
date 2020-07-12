package miu.edu.cs545waa;

import miu.edu.cs545waa.service.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Cs545WaaApplication {

    public static void main(String[] args) {
        SpringApplication.run(Cs545WaaApplication.class, args);
    }

}
