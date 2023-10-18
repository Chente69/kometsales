package co.micia.projects.restapi.pricelist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
/**
 * API Rest CRUD Spring Boot application.
 * @author José V Niño R
 * @version 1.0
 * @since 2023
 */

@SpringBootApplication
@EnableJpaRepositories("co.micia.projects.restapi.pricelist.daljpa.repositories")
public class PriceListApplication {
    /*
     * main() method is entry point of the program.
     * JVM searches for main() method
     */
	public static void main(String[] args) {
		SpringApplication.run(PriceListApplication.class, args);
	}

}
