package com.web.atrio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.web.atrio.users.models.Account;
import com.web.atrio.users.models.Address;
import com.web.atrio.users.models.Telephone;
import com.web.atrio.users.repositories.AccountRepository;
import com.web.atrio.users.repositories.AddressRepository;
import com.web.atrio.users.repositories.TelephoneRepository;

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	@Bean
	CommandLineRunner init(final AccountRepository accountRepository, final AddressRepository addressRepository, final TelephoneRepository telephoneRepository) {

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
				
				Address address = new Address();
				address.setStreetName("Rue du Luan");
				address.setCity("Balma");
				address.setHouseNumber("4");
				address.setPostcode("31130");
				address.setUser(user);
				addressRepository.save(address);
				
				Telephone telephone = new Telephone();
				telephone.setNumber("0689276241");
				telephone.setType("Mobile");
				telephone.setUser(user);
				telephoneRepository.save(telephone);
			}

		};

	}
}
