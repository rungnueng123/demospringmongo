package com.example.demospringmongo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Address {

	private String houseNo;
	private String tambon;
	private String amphur;
	private String province;
	private String postCode;
	
	public Address(String houseNo, String tambon, String amphur, String province, String postCode) {
		this.houseNo = houseNo;
		this.tambon = tambon;
		this.amphur = amphur;
		this.province = province;
		this.postCode = postCode;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getTambon() {
		return tambon;
	}

	public void setTambon(String tambon) {
		this.tambon = tambon;
	}

	public String getAmphur() {
		return amphur;
	}

	public void setAmphur(String amphur) {
		this.amphur = amphur;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	
	
}
