package com.my.crawler.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProvinceMapper {
	List<Map<String, Object>> combox();
}
