package com.pufose.server;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

	/**
	 * to make application that populates database executable: 
	 * ServerApplication must implement CommandLineRunner
	 * """"""""""""""""" must have a field repo of type IGridRepository @Autowired
	 * """"""""""""""""" must have a run() method wich calls repo.save(a_DatabaseGrid);
	 */

	
	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
		
	}
	
	
}
