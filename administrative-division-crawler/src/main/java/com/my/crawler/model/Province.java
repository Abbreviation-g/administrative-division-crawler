package com.my.crawler.model;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 省级行政区
 * 
 * @author guo
 *
 */
public class Province implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	/** 行政区号 */
	private String divisionCode;

	private String provinceName;
	/** 省份简称 */
	private String abbreviation;

	private List<Prefecture> prefectures;

	@JSONField(name = "quHuaDaiMa")
	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}

	@JSONField(name = "shengji")
	public void setProvinceName(String shengji) {
		int from = shengji.indexOf('(');
		int end = shengji.indexOf(')');
		this.provinceName = shengji.substring(0, from);
		this.abbreviation = shengji.substring(from + 1, end);
		if (abbreviation.equals("内蒙古")) {
			this.abbreviation = "蒙";
		} else if(abbreviation.length() != 1) {
			this.abbreviation = abbreviation.substring(0,1);
		}
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public String getDivisionCode() {
		return divisionCode;
	}

	public String getProvinceName() {
		return provinceName;
	}

	@JSONField(serialize = false)
	public String getNameAndAbbreviation() {
		return this.provinceName + '(' + abbreviation + ')';
	}

	public void setPrefectures(List<Prefecture> prefectures) {
		this.prefectures = prefectures;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	@JSONField(serialize = true)
	public List<Prefecture> getPrefectures() {
		return prefectures;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

	public static void main(String[] args) {
		// Province province = new Province();
		// province.setDivisionCode("code");
		// province.setPrefectures(new ArrayList<>());
		// System.out.println(province.toString());

		String jsonStr = "{\"children\":[],\"diji\":\"\",\"quHuaDaiMa\":\"110000\",\"quhao\":\"\",\"shengji\":\"北京市(京)\",\"xianji\":\"\"}";

		Province province = JSON.parseObject(jsonStr, Province.class);
		System.out.println(province);
	}
}
