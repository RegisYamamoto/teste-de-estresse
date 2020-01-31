package com.regis.testedeestresse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.regis.testedeestresse.service.TesteService;

@SpringBootApplication
public class TesteDeEstresseApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(TesteDeEstresseApplication.class, args);
		
		TesteService teste = new TesteService();
		teste.teste();
	}

}
