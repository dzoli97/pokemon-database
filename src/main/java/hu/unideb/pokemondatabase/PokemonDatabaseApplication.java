package hu.unideb.pokemondatabase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class PokemonDatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokemonDatabaseApplication.class, args);
	}

}
