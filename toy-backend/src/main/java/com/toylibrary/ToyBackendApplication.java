package com.toylibrary;

import com.toylibrary.model.MemberType;
import com.toylibrary.model.Role;
import com.toylibrary.model.User;
import com.toylibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ToyBackendApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(ToyBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (userRepository.count() == 0) {
			User user1 = new User("Alice Smith", "alice@example.com", "secure123", Role.MEMBER, MemberType.REGULAR, 400);
			User user2 = new User("Bob Johnson", "bob@example.com", "strongpass", Role.MEMBER, MemberType.GOLD, 800);
			User admin = new User("Admin User", "admin@toy.com", "adminpass", Role.ADMIN, null, 0);

			userRepository.save(user1);
			userRepository.save(user2);
			userRepository.save(admin);
		}
	}
}
