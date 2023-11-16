package com.jeebase.system.common.dto;

import io.swagger.annotations.ApiModelProperty;

public class CreateZjkjListEntity {

	@ApiModelProperty(value = "")
	private String seqNo;

	@ApiModelProperty(value = "患者编码")
	private String patientId;

	@ApiModelProperty(value = "患者性别")
	private String patientSex;

	@ApiModelProperty(value = "患者年龄")
	private String patientAge;

	@ApiModelProperty(value = "")
	private String patientaAgeunit;

	@ApiModelProperty(value = "患者电话")
	private String patientTel;

	@ApiModelProperty(value = "")
	private String barCode;

	@ApiModelProperty(value = "")
	private String machineId;

	@ApiModelProperty(value = "")
	private String sampleId;

	@ApiModelProperty(value = "")
	private String sampleTime;

	@ApiModelProperty(value = "")
	private String testDate;

	@ApiModelProperty(value = "")
	private String CHECKDATE;

	@ApiModelProperty(value = "检验项目编码")
	private String itemId;

	@ApiModelProperty(value = "检验项目名称")
	private String itemName;

	@ApiModelProperty(value = "")
	private String rangeInfo;

	@ApiModelProperty(value = "")
	private String unit;

	@ApiModelProperty(value = "")
	private String resultFlag;

	@ApiModelProperty(value = "")
	private String reportValue;

	@ApiModelProperty(value = "")
	private String sampleType;

	@ApiModelProperty(value = "患者类型")
	private String patientType;

	@ApiModelProperty(value = "患者姓名")
	private String patientName;

	@ApiModelProperty(value = "收费项目编码")
	private String hisitemId;

	@ApiModelProperty(value = "收费项目名称")
	private String hisitemName;

	@ApiModelProperty(value = "医生姓名")
	private String doctorName;

	@ApiModelProperty(value = "科室名称")
	private String deptName;

	@ApiModelProperty(value = "")
	private String printSeq;



}
