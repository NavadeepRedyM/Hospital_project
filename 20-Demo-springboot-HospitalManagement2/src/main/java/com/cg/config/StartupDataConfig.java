package com.cg.config;

import com.cg.model.Department;
import com.cg.model.User;
import com.cg.repository.DepartmentRepository;
import com.cg.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class StartupDataConfig {

    @Bean
    CommandLineRunner initData(UserRepository users, DepartmentRepository departments, BCryptPasswordEncoder encoder) {
        return args -> {
            // Create ADMIN if missing
            users.findByUsername("admin").orElseGet(() -> {
                User u = new User();
                u.setUsername("admin");
                u.setPassword(encoder.encode("admin123")); // change for prod
                u.setRole("ROLE_ADMIN");
                return users.save(u);
            });

            if (departments.count() == 0) {
                Department d1 = new Department();
                d1.setDeptName("Cardiology");
                Department d2 = new Department();
                d2.setDeptName("Neurology");
                Department d3 = new Department();
                d3.setDeptName("Orthopedics");
                departments.save(d1);
                departments.save(d2);
                departments.save(d3);
            }
        };
    }
}