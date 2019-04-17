package io.guessguru;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.guessguru.entities.User;
import io.guessguru.services.UserService;

@SpringBootApplication
public class OnlineShopAssignment2  implements  CommandLineRunner{
	   @Autowired
	   private UserService userService;
	     
	public static void main(String[] args) {
		SpringApplication.run(OnlineShopAssignment2.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		  {
    		  User newAdmin = new User("admin@mail.com", "Admin", "123456");
    		  userService.createAdmin(newAdmin);
    		  
    	  }
	}
}
