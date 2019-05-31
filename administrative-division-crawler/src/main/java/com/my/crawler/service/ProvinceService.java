package com.my.crawler.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.crawler.dao.ProvinceDao;
import com.my.crawler.model.Province;

@Service
public class ProvinceService {
	@Autowired
	ProvinceDao dao;

	public List<Map<String, Object>> combox() {
		return dao.combox();
	}

	public Integer count() {
		return dao.count();
	}
	
	public void initProvince(List<Province> provinces) {
		dao.truncateTable();
		dao.insertBatch(provinces);
	}
}
