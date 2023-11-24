package com.jeebase.system.webchat.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author 小郭
 * @version 1.0
 */
@TableName("T_SYS_PDCA")
@ApiModel(value = "pdca对象", description = "立项实体类")
@Data
public class Pdca {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;
    @ApiModelProperty(value = "项目id")   //项目表
    @TableField("pmis_id")
    private Long pmisId;             //项目id
    @ApiModelProperty(value = "项目名称")
    @TableField("pmis_name")
    private String pmisName;        //项目名称
    @ApiModelProperty(value = "产品id")    //从字典表拿
    @TableField("pdm_id")
    private Long pdmId;             //产品id
    @ApiModelProperty(value = "产品名称")
    @TableField("pdm_name")
    private String pdmName;         //产品名称
    @ApiModelProperty(value = "产品负责人")  //根据id从用户表拿
    @TableField("pmp_name")
    private String pmpName;         //产品负责人
    @ApiModelProperty(value = "计划申请人")  //根据id从用户表拿
    @TableField("pdca_name")
    private String pdcaName;        //计划申请人
    @ApiModelProperty(value = "审核0通过1未审核")
    @TableField("qa_code")
    private String qaCode;          //审核
    @ApiModelProperty(value = "需求id")   //字典表拿
    @TableField("brd_id")
    private Long brdId;             //需求id
    @ApiModelProperty(value = "需求名称")
    @TableField("brd_name")
    private String brdName;         //需求名称
    @ApiModelProperty(value = "项目开始时间")
    @TableField("begin_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate beginDate;  //项目开始时间
    @ApiModelProperty(value = "项目结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField("end_date")
    private LocalDate endDate;   //项目结束时间
    @ApiModelProperty(value = "项目完成时间")
    @TableField("fct_date")
    private LocalDateTime fctDate;   //项目完成时间(年月日，时分秒）
    @ApiModelProperty(value = "备注")
    @TableField("description")
    private String description;      //备注
    @ApiModelProperty(value = "创建时间自动生成")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @ApiModelProperty(value = "创建人id自动生成")
    @TableField(value = "creator", fill = FieldFill.INSERT)
    private Long creator;              //创建人id
    @ApiModelProperty(value = "更新日期自动生成")
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
    @ApiModelProperty(value = "修改人id自动生成")
    @TableField(value = "operator", fill = FieldFill.UPDATE)
    private Long operator;             //修改人id
    @ApiModelProperty(value = "1：删除 0：不删除自动生成")
    @TableField(value="del_flag", fill = FieldFill.INSERT)
    @TableLogic
    private String delFlag;           // 1：删除， 0：不删除

    @ApiModelProperty(value = "产品负责人id")
    @TableField(value = "pmp_id")
    private String pmpId;
    @ApiModelProperty(value = "计划申请者id自动生成")
    @TableField(value = "pdca_id")
    private Long pdcaId;

}
