package com.my.crawler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.crawler.service.PrefectureService;

@RestController
@RequestMapping("/prefecture")
public class PrefectureController {
	@Autowired
	PrefectureService prefectureService;

	@GetMapping("/combox")
	public Object combox(Long pid) {
		return prefectureService.combox(pid);
	}
}
