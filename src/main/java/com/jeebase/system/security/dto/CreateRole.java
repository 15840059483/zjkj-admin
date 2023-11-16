
package com.jeebase.system.security.dto;

import java.util.Date;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author fyy
 * @since  
 */
public class CreateRole
{

    private Long id;
    
    private Long parentId;
    
    private String roleName;
    
    private String roleKey;
    
    private Long roleLevel;
    
    private String roleStatus;
    
    private String description;
    
    private Date createTime;
    
    private Long creator;
    
    private Date updateTime;
    
    private Long operator;
    
    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public Long getParentId()
    {
        return parentId;
    }

    public void setParentId( Long parentId )
    {
        this.parentId = parentId;
    }

    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName( String roleName )
    {
        this.roleName = roleName;
    }

    public String getRoleKey()
    {
        return roleKey;
    }

    public void setRoleKey( String roleKey )
    {
        this.roleKey = roleKey;
    }

    public Long getRoleLevel()
    {
        return roleLevel;
    }

    public void setRoleLevel( Long roleLevel )
    {
        this.roleLevel = roleLevel;
    }

    public String getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(String roleStatus) {
		this.roleStatus = roleStatus;
	}

	public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime( Date createTime )
    {
        this.createTime = createTime;
    }

    public Long getCreator()
    {
        return creator;
    }

    public void setCreator( Long creator )
    {
        this.creator = creator;
    }

    public Date getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime( Date updateTime )
    {
        this.updateTime = updateTime;
    }

    public Long getOperator()
    {
        return operator;
    }

    public void setOperator( Long operator )
    {
        this.operator = operator;
    }

    @Override
    public String toString()
    {
        return "Role{" + "id=" + id + ", parentId=" + parentId + ", roleName=" + roleName + ", roleKey=" + roleKey
                + ", roleStatus=" + roleStatus + ", description=" + description + ", createTime=" + createTime + ", creator="
                + creator + ", updateTime=" + updateTime + ", operator=" + operator + "}";
    }
}
