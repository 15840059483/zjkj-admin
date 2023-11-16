package com.jeebase.system.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jeebase.system.security.entity.Organization;

import java.util.List;

/**
 *
 * @author fyy
 */
public interface IOrganizationService extends IService<Organization> {

    /**
     * 查询机构列表
     * @param parentId
     * @return
     */
    List<Organization> queryOrganizationByPanentId(Long parentId);

    /**
     * 查询机构列表，不组装父子节点
     * @param parentId
     * @return
     */
    List<Organization> queryOrgList(Long parentId);

    /**
     * 创建组织
     * @param organization
     * @return
     */
    boolean createOrganization(Organization organization);

    /**
     * 更新组织
     * @param organization
     * @return
     */
    boolean updateOrganization(Organization organization);

    /**
     * 删除组织
     * @param organizationId
     * @return
     */
    boolean deleteOrganization(Long organizationId);
}
