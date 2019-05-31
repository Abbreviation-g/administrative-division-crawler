package com.my.crawler.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.crawler.model.County;
import com.my.crawler.model.Prefecture;
import com.my.crawler.model.Province;

@Service
public class InitDbService {
	@Autowired
	ProvinceService provinceService;
	@Autowired
	PrefectureService prefectureService;
	@Autowired
	CountyService countyService;

	public void init(List<Province> provinces) {
		provinceService.initProvince(provinces);

		List<Prefecture> allPrefectures = new ArrayList<>();
		provinces.forEach((province) -> {
			List<Prefecture> prefectures = province.getPrefectures();
			prefectures.forEach((prefecture) -> prefecture.setPid(province.getId()));
			allPrefectures.addAll(prefectures);
		});
		prefectureService.initPrefecture(allPrefectures);

		List<County> allCounties = new ArrayList<>();
		allPrefectures.forEach((prefecture) -> {
			List<County> counties = prefecture.getCounties();
			counties.forEach((county) -> county.setPid(prefecture.getId()));
			allCounties.addAll(counties);
		});
		countyService.initCounty(allCounties);
	}
}
