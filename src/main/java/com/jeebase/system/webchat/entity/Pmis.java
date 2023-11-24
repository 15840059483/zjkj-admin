package com.jeebase.system.webchat.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Component
@TableName("t_sys_pmis")
@Data
@ApiModel(value="Pmis对象", description="项目信息表")
public class Pmis {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @TableField("name")
    private String name;

    @TableField("pmp")
    private String pmp;

    @TableField("plc")
    private String plc;

    @TableField("begin_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private LocalDate beginDate;

    @TableField("end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private LocalDate endDate;

    @TableField("description")
    private String description;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "creator", fill = FieldFill.INSERT)
    private Long creator;

    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    @TableField(value = "operator", fill = FieldFill.UPDATE)
    private Long operator;

    @TableField(value = "del_flag", fill = FieldFill.INSERT)
    @TableLogic
    private String delFlag;

    @TableField("pdm")
    private String pdm;


}
