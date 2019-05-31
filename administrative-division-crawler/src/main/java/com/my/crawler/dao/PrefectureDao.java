package com.my.crawler.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.my.crawler.model.Prefecture;

@Mapper
public interface PrefectureDao {
	int insertByBatch(List<Prefecture> prefectures);
	
	void truncateTable();
}
