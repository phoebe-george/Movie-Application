package io.springboot.movieapp.confg;

import io.springboot.movieapp.domain.entity.User;
import io.springboot.movieapp.domain.enums.UserRole;
import io.springboot.movieapp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminUserConfig {

    private final PasswordEncoder passwordEncoder;

    public AdminUserConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner createAdminUser(UserRepository userRepository) {
        return args -> {
            // Check if the admin user already exists to avoid duplication
            if (!userRepository.existsByUsername("admin")) {
                // Create and save an admin user
                User adminUser = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("1234")) // You should encode the password properly
                        .fullName("Admin User")
                        .email("admin@example.com")
                        .role(UserRole.ADMIN)
                        .build();

                userRepository.save(adminUser);
                System.out.println("Admin user created successfully!");
            }
        };
    }
}
