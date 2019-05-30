package com.my.crawler.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.crawler.dao.ProvinceMapper;

@Service
public class ProvinceService {
	@Autowired
	ProvinceMapper dao;

	public List<Map<String, Object>> combox() {
		return dao.combox();
	}
}
