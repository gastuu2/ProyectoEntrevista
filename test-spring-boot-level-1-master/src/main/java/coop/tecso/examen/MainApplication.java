package coop.tecso.examen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication {
	
	private static final Logger logger= LogManager.getLogger(MainApplication.class);

	public static void main(final String[] args) {
		logger.info("Lalalalala");
		SpringApplication.run(MainApplication.class, args);
	}
	
}
