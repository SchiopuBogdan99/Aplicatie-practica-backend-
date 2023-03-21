package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.entity.UserType;
import com.example.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;




@SpringBootApplication
@EnableWebSecurity(debug = true)
public class DemoApplication{


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Bean
	CommandLineRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder){
		return args -> {
			User administrator = User.builder()
					.name("Teodor Strut")
					.email("teodorstrut@gmail.com")
					.password(passwordEncoder.encode("teodor"))
					.userType(UserType.ADMINISTRATOR)
					.build();
			userRepository.save(administrator);
		};
	}
}


