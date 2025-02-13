package br.com.sorteio_daily;

import br.com.sorteio_daily.services.PeopleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SorteioDailyApplication {

    public static void main(String[] args) {
		SpringApplication.run(SorteioDailyApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		peopleService.loadPeoples();
//	}
}
