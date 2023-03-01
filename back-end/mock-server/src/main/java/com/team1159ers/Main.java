
package com.team1159ers;

import com.team1159ers.mock_csulb_db.MockCsulbDb;
import org.springframework.boot.SpringApplication;

import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        SpringApplication mockCsulbDbApp = new SpringApplication(MockCsulbDb.class);
        mockCsulbDbApp.setDefaultProperties(Collections
            .singletonMap("server.port", "8080"));
        mockCsulbDbApp.run(args);
    }
}
