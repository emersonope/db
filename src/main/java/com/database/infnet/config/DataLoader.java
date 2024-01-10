package com.database.infnet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.database.infnet.model.User;
import com.database.infnet.repository.UserRepository;

import java.math.BigDecimal;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadInitialData();
    }

    private void loadInitialData() {
        User user1 = new User();
        user1.setName("John Doe");
        user1.setEmail("john@example.com");
        user1.setDebt(new BigDecimal("200.2"));
        userRepository.save(user1);

        User user2 = new User();
        user2.setName("Jane Doe");
        user2.setEmail("jane@example.com");
        user2.setDebt(new BigDecimal("250.2"));
        userRepository.save(user2);
    }
}

