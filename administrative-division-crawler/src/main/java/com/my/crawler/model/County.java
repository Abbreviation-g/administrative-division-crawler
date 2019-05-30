package com.my.crawler.model;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

public class County implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long pid;

	private String countyName;
	/** 行政区号 */
	private String divisionCode;
	/** 电话区号 */
	private String phoneCode;

	public String getCountyName() {
		return countyName;
	}

	@JSONField(name = "xianji")
	public void setCountyName(String countyName) {
		this.countyName = countyName;
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
