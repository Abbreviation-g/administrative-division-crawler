package com.my.crawler.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.my.crawler.model.County;

@Mapper
public interface CountyDao {
	int insertByBatch(List<County> counties);
	
	void truncateTable();
}
