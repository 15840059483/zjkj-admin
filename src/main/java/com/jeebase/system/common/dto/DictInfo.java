package com.jeebase.system.common.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 数据字典
 * </p>
 *
 * @author fyy
 * @since  
 */
@ApiModel(value="DictInfo对象", description="数据字典")
public class DictInfo implements Serializable {

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
    private Long dictOrder;

    @ApiModelProperty(value = "1有效，0禁用")
    private String dictStatus;

    @ApiModelProperty(value = "描述信息")
    private String description;
    
    @ApiModelProperty(value = "字典列表")
    @TableField(exist = false)
    private List<DictInfo> children = new ArrayList<>();

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private Long creator;

    @ApiModelProperty(value = "更新新建")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "最后修改人")
    private Long operator;

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

    
    public Long getDictOrder() {
        return dictOrder;
    }

    
    public void setDictOrder(Long dictOrder) {
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
    
    public List<DictInfo> getChildren() {
        return children;
    }

    
    public void setChildren(List<DictInfo> children) {
        this.children = children;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
    public Long getOperator() {
        return operator;
    }

    public void setOperator(Long operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "Dict{" +
        ", parentId=" + parentId +
        ", dictName=" + dictName +
        ", dictCode=" + dictCode +
        ", dictOrder=" + dictOrder +
        ", dictStatus=" + dictStatus +
        ", description=" + description +
        ", createTime=" + createTime +
        ", creator=" + creator +
        ", updateTime=" + updateTime +
        ", operator=" + operator +
        "}";
    }
}
