package com.my.crawler.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.crawler.dao.PrefectureDao;
import com.my.crawler.model.Prefecture;

@Service
public class PrefectureService {
	@Autowired
	PrefectureDao prefectureDao;
	
	public void initPrefecture(List<Prefecture> prefectures) {
		prefectureDao.truncateTable();
		prefectureDao.insertByBatch(prefectures);
	}
}
