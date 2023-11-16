package com.jeebase.system.common.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class CreateHospDept implements Serializable{
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "科室名称")
	private String deptName;
	
	@ApiModelProperty(value = "电话")
	private String deptMobile;

	@ApiModelProperty(value = "栏级")
	private String menuRankId;

	@ApiModelProperty(value = "路由地址")
	private String routLink;

	@ApiModelProperty(value = "类型：1109、门诊 1108、住院")
	private String deptType;

	@ApiModelProperty(value = "介绍")
	private String introduce;

	@ApiModelProperty(value = "备注")
	private String description;

	@ApiModelProperty(value = "科室编码")
	private String deptCode;

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptMobile() {
		return deptMobile;
	}

	public void setDeptMobile(String deptMobile) {
		this.deptMobile = deptMobile;
	}


	public String getMenuRankId() {
		return menuRankId;
	}

	public void setMenuRankId(String menuRankId) {
		this.menuRankId = menuRankId;
	}

	public String getRoutLink() {
		return routLink;
	}

	public void setRoutLink(String routLink) {
		this.routLink = routLink;
	}

	public String getDeptType() {
		return deptType;
	}

	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

}
