package com.my.crawler;

import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;

public class CliTest {
	private static final String ACTIVE_PROFILES= "spring.profiles.active";
	private static final String TEST_PROFILES = "test";
	public static void main(String[] args) {
		
		ApplicationArguments arguments = new DefaultApplicationArguments(args);
		if(arguments.containsOption(ACTIVE_PROFILES)) {
			List<String> profiles = arguments.getOptionValues(ACTIVE_PROFILES);
			if(profiles.contains(TEST_PROFILES)) {
				System.out.println(arguments.getOptionValues(ACTIVE_PROFILES));
			}
		}
	}
}
