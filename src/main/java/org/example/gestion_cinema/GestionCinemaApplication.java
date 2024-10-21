package org.example.gestion_cinema;

import org.example.gestion_cinema.entities.AppRole;
import org.example.gestion_cinema.entities.AppUser;
import org.example.gestion_cinema.service.AccountService;
import org.example.gestion_cinema.service.AccountServiceImpl;
import org.example.gestion_cinema.service.ICinemaInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class GestionCinemaApplication implements CommandLineRunner {
    private ICinemaInit iCinemaInit;
    private AccountService accountService;

    public GestionCinemaApplication(ICinemaInit iCinemaInit,AccountService accountService){
        this.accountService=accountService;
        this.iCinemaInit=iCinemaInit;
    }
	public static void main(String[] args) {
		SpringApplication.run(GestionCinemaApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
//        iCinemaInit.initVilles();
//        iCinemaInit.initCinemas();
//        iCinemaInit.initSalles();
//        iCinemaInit.initPlace();
//        iCinemaInit.initCategories();
//        iCinemaInit.initFilms();
//        iCinemaInit.initSeance();
   //     iCinemaInit.initProjections();
//     iCinemaInit.initTickets();
        //accountService.addRole(new AppRole(null,"USER"));
        //accountService.addRole(new AppRole(null,"ADMIN"));
        //accountService.addRole(new AppRole(null,"CUSTOMER_MANAGER"));
        //accountService.addRole(new AppRole(null,"PRODUCT_MANAGER"));
        //accountService.addRole(new AppRole(null,"BILLS_MANAGER"));
        //accountService.addUser(new AppUser(null,"user1","1234",new ArrayList<>()));
        //accountService.addUser(new AppUser(null,"admin","1234",new ArrayList<>()));
        //accountService.addUser(new AppUser(null,"user2","1234",new ArrayList<>()));
        //accountService.addUser(new AppUser(null,"user3","1234",new ArrayList<>()));
        //accountService.addUser(new AppUser(null,"user4","1234",new ArrayList<>()));
        //accountService.addRoleToUser("user1","USER");
        //accountService.addRoleToUser("admin","ADMIN");
        //accountService.addRoleToUser("user2","CUSTOMER_MANAGER");
        //accountService.addRoleToUser("user3","PRODUCT_MANAGER");
        //accountService.addRoleToUser("user4","BILLS_MANAGER");
    }
}
