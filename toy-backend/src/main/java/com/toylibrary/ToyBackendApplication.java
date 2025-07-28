package com.toylibrary;

import com.toylibrary.exception.ToyNotFoundException;
import com.toylibrary.model.MemberType;
import com.toylibrary.model.Rental;
import com.toylibrary.model.Role;
import com.toylibrary.model.Toy;
import com.toylibrary.model.User;
import com.toylibrary.repository.RentalRepository;
import com.toylibrary.repository.ToyRepository;
import com.toylibrary.repository.UserRepository;
import com.toylibrary.service.ToyService;
import com.toylibrary.service.UserService;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ToyBackendApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ToyRepository toyRepository;

	@Autowired
	private RentalRepository rentalRepository;

	@Autowired
	ToyService toyService;

	@Autowired
	UserService userService;

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

		if (toyRepository.count() == 0 ){
			User alice = userRepository.findByEmail("alice@example.com").orElseThrow();
			User bob = userRepository.findByEmail("bob@example.com").orElseThrow();

			Toy toy1 = new Toy("Lego Star Wars", "Intergalactic building set with 500 pieces", 120, alice);
			Toy toy2 = new Toy("Barbie Dream House", "3-story house with elevator and furniture", 150, bob);
			Toy toy3 = new Toy("Hot Wheels Track", "Ultimate racing set with loop and booster", 100, bob);

			toyRepository.save(toy1);
			toyRepository.save(toy2);
			toyRepository.save(toy3);
		}

		if (rentalRepository.count() == 0) {
            User alice = userRepository.findByEmail("alice@example.com").orElseThrow();
            User bob = userRepository.findByEmail("bob@example.com").orElseThrow();

            Toy toy1 = toyRepository.findByName("Lego Star Wars");
            Toy toy2 = toyRepository.findByName("Barbie Dream House");

            LocalDate startDate = LocalDate.now().minusDays(5);
            LocalDate endDate = startDate.plusDays(30);

            // Seed rental: Alice rents Lego
            Rental rental1 = new Rental(alice, toy1, startDate, endDate);
            rentalRepository.save(rental1);
            toy1.setRented(true);
            toyService.updateToy(toy1);
            userService.deductPoints(alice.getId(), toy1.getPointCost());

            // Seed rental: Bob rents Barbie House
            Rental rental2 = new Rental(bob, toy2, startDate.minusDays(10), endDate.minusDays(10));
            rentalRepository.save(rental2);
            toy2.setRented(true);
            toyService.updateToy(toy2);
            userService.deductPoints(bob.getId(), toy2.getPointCost());
        }
	}
}
