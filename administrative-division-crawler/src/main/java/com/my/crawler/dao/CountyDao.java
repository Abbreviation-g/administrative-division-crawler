package com.my.crawler.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.my.crawler.model.County;

@Mapper
public interface CountyDao {
	int insertByBatch(List<County> counties);
	
	void truncateTable();
	
	List<Map<String, Object>> combox(@Param("pid") Long pid);
}
