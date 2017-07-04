package com.sml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.sml.repository.GlobalVariable;
import com.sml.repository.MWRepository;
import com.sml.service.MWService;

@SpringBootApplication
@ComponentScan
public class MainApp implements CommandLineRunner {
	
	@Autowired
	private MWService services;
	@Autowired
	private GlobalVariable gv;
	
	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);
	}
	
	@Override
	public void run(String... arg0) throws Exception {
		
	}
}
