package hh.harkkas23v02;

import java.time.LocalDateTime;
/*
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner; 	*/
import org.springframework.boot.SpringApplication;	
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;

/*
import hh.harkkas23v02.domain.ApplicationUser;
import hh.harkkas23v02.domain.ApplicationUserRepository;
import hh.harkkas23v02.domain.Article;
import hh.harkkas23v02.domain.ArticleRepository;
import hh.harkkas23v02.domain.Adtype;
import hh.harkkas23v02.domain.AdtypeRepository; */

@SpringBootApplication
public class Harkkas23v02Application {
	
//	private static final Logger log = LoggerFactory.getLogger(Harkkas23v02Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Harkkas23v02Application.class, args);
		
		System.out.println("");


		
		System.out.println("Versio 02 (10-11-2023)");


		System.out.println("- muista päivittää WebSecurityConfig.java WHITE LIST URLS");
		
		
		System.out.println("");
		
		

		LocalDateTime ldt = LocalDateTime.now();

		System.out.println("Myyntipalsta running!");
		System.out.println("Käytössä MariaDb - harkkasyksydb");

		System.out.println(ldt);
		

	
	}
	
	
	/*
	@Bean
	public CommandLineRunner demo(ArticleRepository repository, AdtypeRepository crepository, ApplicationUserRepository urepository) {
		return(args) ->{
			
			Adtype ykkonen =new Adtype("IT");
			Adtype kakkonen=new Adtype("Ohjelmointi");
			
			crepository.save(ykkonen);
			crepository.save(kakkonen);
			
			Article kirjayks = new Article("Ohjelmoinnin salat", "tuntematon", "978-951-12345-0-1" , 2010, 24.95, crepository.findByName("IT").get(0));
			Article kirjakaks = new Article("Tietokannat", "Severi Hakkeri", "978-951-12345-0-2", 2018, 29.90, crepository.findByName("Ohjelmointi").get(0));
			Article kirjakolme = new Article("Koodaajan käsikirja", "Hilda Koodinkirjoittaja", "978-951-12345-0-3", 2020, 24.95, crepository.findByName("Ohjelmointi").get(0));
			//Article kirjakolme = new Article("Koodaajan käsikirja", "Hilda Koodinkirjoittaja", "978-951-12345-0-3", 2020, 24.95, kakkonen);
			
			
			//Tallentaa objektin tietokantaan
			System.out.println("sinne meni!");
			repository.save(kirjayks);
			repository.save(kirjakaks);
			repository.save(kirjakolme);
			
			log.info("lisätty kirjat 1,2,3");
			
			//Lisätään käyttäjät tietokantaan, admin/admin ja user/user, pentti/pentti(admin)
			ApplicationUser user1 = new ApplicationUser("user","$2a$10$RIqlxElPXzQKJayHKJwSNOxDMnMh.j.OHwQvOoPj0gld.sbXsqqgK" ,"USER");
			ApplicationUser user2 = new ApplicationUser("admin", "$2a$10$aGjp6jEUEspwUkQrCbGAWuKScc9DRHTQ6LXMRX2TAM5A6tzHdy8/6", "ADMIN");
			ApplicationUser pentti = new ApplicationUser("pentti", "$2a$10$KzsfhslxfLsSivybtKfyguhx5zS9vzG.LIUwqC2SWOIOQZ7ml/gJi", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			urepository.save(pentti);
			System.out.println("LISÄTTY KÄYTTÄJÄT: " + user1 + " " + user2 + " " + pentti );
			log.info("Lisätty käyttäjät admin ja user ja pentti");
			
			System.out.println("nouda, fetch");
			for (Article book : repository.findAll()) {
			System.out.println(book.toString());
			}
		};
	}*/

}
