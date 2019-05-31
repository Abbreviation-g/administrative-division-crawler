package com.my.crawler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.crawler.service.ProvinceService;

@RestController
@RequestMapping("/province")
public class ProvinceController {
	@Autowired
	ProvinceService provinceService;
	@GetMapping("/combox")
	public Object combox() {
		return provinceService.combox();
	}
}
