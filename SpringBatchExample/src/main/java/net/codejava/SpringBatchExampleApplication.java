package net.codejava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class SpringBatchExampleApplication {

	public static void main(String[] args) {
		//System.setProperty("server.servlet.context-path", "/webApp");
		SpringApplication.run(SpringBatchExampleApplication.class, args);
	}
	
	
}