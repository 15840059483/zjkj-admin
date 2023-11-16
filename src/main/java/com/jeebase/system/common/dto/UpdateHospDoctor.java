package com.jeebase.system.common.dto;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Description 更新医院医生对象
 * @data 2020年11月18日上午11:58:23
 */
public class UpdateHospDoctor implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键")
	private Long id;

	@ApiModelProperty(value = "医生ID")
	private String doctorId;
	
	@ApiModelProperty(value = "科室id")
	private String deptId;
	
	@ApiModelProperty(value = "科室编码")
	private String deptcode;
	
	@ApiModelProperty(value = "科室名称")
	private String deptName;

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

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

	public void setId(Long id) {
		this.id = id;
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
		return "UpdateHospDoctor [id=" + id + ", doctorId=" + doctorId + ", doctorName=" + doctorName
				+ ", regLevelName=" + regLevelName + ", regLevelId=" + regLevelId + ", scheduleName=" + scheduleName
				+ ", scheduleId=" + scheduleId + ", introduce=" + introduce + ", description=" + description + "]";
	}

}
