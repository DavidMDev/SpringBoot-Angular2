package com.web.atrio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.web.atrio.users.models.Account;
import com.web.atrio.users.repositories.AccountRepository;

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	@Bean
	CommandLineRunner init(final AccountRepository accountRepository) {

		return new CommandLineRunner() {

			@Override
			public void run(String... arg0) throws Exception {
				Account user = new Account();
				user.setEmail("dmelia@dmelia.fr");
				user.setFirstName("David");
				user.setLastName("Melia");
				user.setPassword("test");
				user.setUserName("dmelia");
				user.addRole("USER");
				user.addRole("ADMIN");
				accountRepository.save(user);
			}

		};

	}
}
