package fr.simplon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;


public class DemandeAbsencesApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(DemandeAbsencesApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(Config.class, args);
		
		logger.info("##### Application démarée ######");
	}
}
