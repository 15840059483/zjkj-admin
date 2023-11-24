package com.jeebase.system.webchat.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author 小郭
 * @version 1.0
 */
@Data
public class PdcaNo extends Pdca{
    private Integer pageNo;
    private Integer pageSize;
}
