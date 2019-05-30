package com.my.crawler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.my.crawler.service.ProvinceService;

@SpringBootApplication
public class ServiceMainApplication implements CommandLineRunner{
	public static void main(String[] args) {
		SpringApplication.run(ServiceMainApplication.class, args);
	}

	@Autowired
	ProvinceService provinceService;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("----------------------------------------------------------------");
		System.out.println(provinceService.combox());
	}
}
