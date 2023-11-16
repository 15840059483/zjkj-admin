package com.jeebase.system.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 数据字典
 * </p>
 *
 * @author fyy
 * @since  
 */
@ApiModel(value="Dict对象", description="数据字典")
public class UpdateDict implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "字典类型")
    private Long parentId;

    @ApiModelProperty(value = "字典名称")
    private String dictName;

    @ApiModelProperty(value = "字典值")
    private String dictCode;

    @ApiModelProperty(value = "排序")
    private Integer dictOrder;

    @ApiModelProperty(value = "1有效，0禁用")
    private String dictStatus;

    @ApiModelProperty(value = "描述信息")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getParentId() {
        return parentId;
    }

    
    
    public String getDictName() {
        return dictName;
    }

    
    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    
    public String getDictCode() {
        return dictCode;
    }

    
    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    
    public Integer getDictOrder() {
        return dictOrder;
    }

    
    public void setDictOrder(Integer dictOrder) {
        this.dictOrder = dictOrder;
    }

    
    public String getDictStatus() {
        return dictStatus;
    }

    
    public void setDictStatus(String dictStatus) {
        this.dictStatus = dictStatus;
    }

    
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Dict{" +
        " id=" + id +
        ", parentId=" + parentId +
        ", dictName=" + dictName +
        ", dictCode=" + dictCode +
        ", dictOrder=" + dictOrder +
        ", dictStatus=" + dictStatus +
        ", description=" + description +
        "}";
    }
}
