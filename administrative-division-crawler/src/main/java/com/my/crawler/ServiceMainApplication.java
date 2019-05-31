package com.my.crawler;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import com.my.crawler.model.Province;
import com.my.crawler.model.util.Crawler;
import com.my.crawler.service.InitDbService;

@SpringBootApplication
@ServletComponentScan
public class ServiceMainApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(ServiceMainApplication.class, args);
	}

	@Autowired
	InitDbService initDbService;

	@Autowired
	ApplicationArguments arguments;
	
	private static final String ACTIVE_PROFILES= "spring.profiles.active";
	private static final String INIT_PROFILES = "init";
	@Override
	public void run(String... args) throws Exception {
		//--spring.profiles.active=test 
		// java -jar administrative-division-crawler-0.0.1-SNAPSHOT.jar --spring.profiles.active=init
		System.out.println("----------------------------------------------------------------");
		System.out.println(Arrays.toString(args));
		ApplicationArguments arguments = new DefaultApplicationArguments(args);
		if(arguments.containsOption(ACTIVE_PROFILES)) {
			List<String> profiles = arguments.getOptionValues(ACTIVE_PROFILES);
			if(profiles.contains(INIT_PROFILES)) {
				Crawler crawler = new Crawler();
				List<Province> provinces = crawler.getProvinces();
				initDbService.init(provinces);
			}
		}
	}
}
