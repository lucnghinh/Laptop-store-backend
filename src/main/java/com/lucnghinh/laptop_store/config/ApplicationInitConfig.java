package com.lucnghinh.laptop_store.config;

import com.lucnghinh.laptop_store.entity.User;
import com.lucnghinh.laptop_store.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
public class ApplicationInitConfig {

    @Bean
    public ApplicationRunner applicationRunner(UserRepository userRepository, PasswordEncoder passwordEncoder) {

        return args -> {
            if(userRepository.findByUsername("admin").isEmpty()) {
                User user = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin123"))
                        .firstName("System")
                        .lastName("Administrator")
                        .email("admin@localhost")
//                        .role(Role.ADMIN)
                        .build();

                userRepository.save(user);
            }
            log.warn("admin has been created with default password: admin");
        };
    }
}
