package com.platner.jobtime;

import com.platner.jobtime.model.Job;
import com.platner.jobtime.repo.JobRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner demo(JobRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new Job("Jack"));
			repository.save(new Job("Chloe"));
			repository.save(new Job("Kim"));
			repository.save(new Job("David"));
			repository.save(new Job("Michelle"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Job job : repository.findAll()) {
				log.info(job.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			repository.findById(1L)
				.ifPresent(job -> {
					log.info("Job found with findById(1L):");
					log.info("--------------------------------");
					log.info(job.toString());
					log.info("");
				});

			// fetch customers by last name
			log.info("Job found with findByName('Bauer'):");
			log.info("--------------------------------------------");
			repository.findByName("Bauer").forEach(bauer -> {
				log.info(bauer.toString());
			});
			// for (Job bauer : repository.findByLastName("Bauer")) {
			// 	log.info(bauer.toString());
			// }
			log.info("");
		};
	}

}
