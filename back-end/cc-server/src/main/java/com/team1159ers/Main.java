
package com.team1159ers;

import com.team1159ers.coffee_coder_db.CoffeeCoderDb;
import org.springframework.boot.SpringApplication;

import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        SpringApplication coffeeCoderApp = new SpringApplication(CoffeeCoderDb.class);
        coffeeCoderApp.setDefaultProperties(Collections
            .singletonMap("server.port", "8080"));
        coffeeCoderApp.run(args);
    }
}
