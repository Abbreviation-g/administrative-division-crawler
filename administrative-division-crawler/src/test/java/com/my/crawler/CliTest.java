package com.my.crawler;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		
		int[] levelChannel = new int[2];

		String line = "JC:<level:0><channel:9> Received message id:31";
		Pattern pattern = Pattern.compile("JC:<level:(\\d*)><channel:(\\d*)>");
		Matcher matcher = pattern.matcher(line);
		if (matcher.find()) {
			int level = Integer.parseInt(matcher.group(1));
			int channel = Integer.parseInt(matcher.group(2));
			levelChannel[0] = level;
			levelChannel[1] = channel;
			System.out.println(level+"->"+channel);
		}
	}
}
