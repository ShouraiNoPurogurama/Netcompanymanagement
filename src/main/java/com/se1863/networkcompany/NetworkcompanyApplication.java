package com.se1863.networkcompany;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@SpringBootApplication
public class NetworkcompanyApplication implements CommandLineRunner {
	
	// @EventListener(classes = ApplicationReadyEvent.class)
	// public void sendMail() {
	// 	mailSenderService.sendEmail("nhatanhtruong687@gmail.com", "Account Blocked Notification", "He he");
	// }

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(NetworkcompanyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//test vars
	}

}
