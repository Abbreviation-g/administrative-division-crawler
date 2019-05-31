package com.my.crawler.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.crawler.dao.CountyDao;
import com.my.crawler.model.County;

@Service
public class CountyService {
	@Autowired
	CountyDao countyDao;
	
	public void initCounty(List<County> counties) {
		countyDao.truncateTable();
		countyDao.insertByBatch(counties);
	}
	
	public List<Map<String, Object>> combox(Long pid) {
		return countyDao.combox(pid);
	}
}
