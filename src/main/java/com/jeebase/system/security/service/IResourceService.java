package com.jeebase.system.security.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jeebase.system.security.entity.Resource;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @author fyy
 */
public interface IResourceService extends IService<Resource> {

    /**
     * 查询用户资源角色
     * @param userId
     * @return
     */
    List<Resource> queryResourceByUserId(Long userId);

    @Cacheable(value = "resources", key = "'all_user_id_'.concat(#userId)")
    List<String> queryResourceListByUserId(Long userId);

    /**
     * 查询资源权限列表
     * @param parentId
     * @return
     */
    List<Resource> queryResourceByParentId(Long parentId);

    /**
     * 查询资源权限列表
     * @param wrapper
     * @return
     */
    List<Resource> selectResourceList(QueryWrapper<Resource> wrapper);

    /**
     * 创建资源权限
     * @param resource
     * @return
     */
    boolean createResource(Resource resource);

    /**
     * 更新资源权限
     * @param resource
     * @return
     */
    boolean updateResource(Resource resource);

    /**
     * 删除资源权限
     * @param resourceId
     * @return
     */
    boolean deleteResource(Long resourceId);
}
