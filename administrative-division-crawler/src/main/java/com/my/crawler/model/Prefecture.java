package com.my.crawler.model;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 地级行政区
 * 
 * @author guo
 *
 */
public class Prefecture implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long pid;

	private String prefectureName;
	/** 行政区号 */
	private String divisionCode;
	/** 电话区号 */
	private String phoneCode;

	private List<County> counties;

	public String getPrefectureName() {
		return prefectureName;
	}

	@JSONField(name = "diji")
	public void setPrefectureName(String prefectureName) {
		this.prefectureName = prefectureName;
	}

	public String getDivisionCode() {
		return divisionCode;
	}

	@JSONField(name = "quHuaDaiMa")
	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}

	public String getPhoneCode() {
		return phoneCode;
	}

	@JSONField(name = "quhao")
	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}

	public void setCounties(List<County> counties) {
		this.counties = counties;
	}

	@JSONField(serialize = true)
	public List<County> getCounties() {
		return counties;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
