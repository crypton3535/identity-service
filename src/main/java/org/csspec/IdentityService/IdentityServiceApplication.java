package org.csspec.IdentityService;

import org.csspec.IdentityService.api.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IdentityServiceApplication implements CommandLineRunner {
    @Autowired
    public UserRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(IdentityServiceApplication.class, args);
    }

    public void run(String... args) throws Exception {


        //System.out.println(repository.findThis());

    }
}
