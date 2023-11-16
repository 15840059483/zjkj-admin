package com.jeebase.system.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("身份证证件信息")
public class ImgIDCardInfo {

	@ApiModelProperty("卡号")
	private String id;
	
	@ApiModelProperty("性别")
	private String sex;
	
	@ApiModelProperty("姓名")
	private String name;
	
	@ApiModelProperty("民族")
	private String nation;
	
	@ApiModelProperty("住址")
	private String address;
	
	@ApiModelProperty("生日")
	private String birthday;
	
	@ApiModelProperty("失效日期")
	private String expirationDate;
	
	@ApiModelProperty("签发机关")
	private String issueOffice;
	
	@ApiModelProperty("签发日期")
	private String issueDate;

	public ImgIDCardInfo(String expirationDate, String issueOffice, String issueDate) {
		super();
		this.expirationDate = expirationDate;
		this.issueOffice = issueOffice;
		this.issueDate = issueDate;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getIssueOffice() {
		return issueOffice;
	}

	public void setIssueOffice(String issueOffice) {
		this.issueOffice = issueOffice;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public ImgIDCardInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ImgIDCardInfo(String id, String sex, String name, String nation, String address, String birthday) {
		super();
		this.id = id;
		this.sex = sex;
		this.name = name;
		this.nation = nation;
		this.address = address;
		this.birthday = birthday;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ImgIDCardInfo [id=" + id + ", sex=" + sex + ", name=" + name + ", nation=" + nation + ", address="
				+ address + ", birthday=" + birthday + "]";
	}
}
