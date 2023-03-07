package com.example.demo;

import com.example.demo.dto.AuthenticationResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.UserType;
import com.example.demo.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import static com.example.demo.entity.UserType.COUNTRY;


@SpringBootApplication
@EnableWebSecurity(debug = true)
public class DemoApplication{


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}

}


