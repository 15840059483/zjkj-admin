package com.jeebase.system.common.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @Description 创建医院医生对象
 * @data 2020年11月18日上午11:58:53
 */
public class CreateHospDoctor implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "医生ID")
	private String doctorId;

	@ApiModelProperty(value = "医生姓名")
	private String doctorName;

	@ApiModelProperty(value = "号源级别名称")
	private String regLevelName;

	@ApiModelProperty(value = "号源级别ID")
	private String regLevelId;

	@ApiModelProperty(value = "医生级别名称")
	private String scheduleName;

	@ApiModelProperty(value = "医生级别ID")
	private String scheduleId;

	@ApiModelProperty(value = "介绍")
	private String introduce;

	@ApiModelProperty(value = "描述")
	private String description;

	public String getDoctorId() {
		return doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public String getRegLevelName() {
		return regLevelName;
	}

	public String getRegLevelId() {
		return regLevelId;
	}

	public String getScheduleName() {
		return scheduleName;
	}

	public String getScheduleId() {
		return scheduleId;
	}

	public String getIntroduce() {
		return introduce;
	}

	public String getDescription() {
		return description;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public void setRegLevelName(String regLevelName) {
		this.regLevelName = regLevelName;
	}

	public void setRegLevelId(String regLevelId) {
		this.regLevelId = regLevelId;
	}

	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "CreateHospDoctor [doctorName=" + doctorName + ", regLevelName="
				+ regLevelName + ", regLevelId=" + regLevelId + ", scheduleName=" + scheduleName + ", scheduleId="
				+ scheduleId + ", introduce=" + introduce + ", description=" + description + "]";
	}

}
