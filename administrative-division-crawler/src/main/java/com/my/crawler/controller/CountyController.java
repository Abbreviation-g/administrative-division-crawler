package com.my.crawler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.crawler.service.CountyService;

@RestController
@RequestMapping("/county")
public class CountyController {
	@Autowired
	CountyService countyService;

	@GetMapping("/combox")
	public Object combox(Long pid) {
		return countyService.combox(pid);
	}
}
