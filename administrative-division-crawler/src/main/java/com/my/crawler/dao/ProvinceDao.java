package com.my.crawler.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.my.crawler.model.Province;

@Mapper
public interface ProvinceDao {
	List<Map<String, Object>> combox();
	
	Integer count();
	
	int insertBatch(List<Province> provinces);
	
	void truncateTable();
}
