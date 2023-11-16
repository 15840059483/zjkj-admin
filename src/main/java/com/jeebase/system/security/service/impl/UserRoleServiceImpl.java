package com.jeebase.system.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jeebase.system.security.entity.Role;
import com.jeebase.system.security.entity.UserRole;
import com.jeebase.system.security.mapper.UserRoleMapper;
import com.jeebase.system.security.service.IRoleService;
import com.jeebase.system.security.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: RoleServiceImpl
 * @Description: 角色相关操作接口实现类
 * @author fyy
 * @date  
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Autowired
    private IRoleService roleService;

    @Override
    public UserRole selectByUserId(Long userId) {
        QueryWrapper<UserRole> ew = new QueryWrapper<>();
        ew.eq("user_id", userId);
        return this.getOne(ew);
    }

    @Override
    @Cacheable(value = "roles", key = "'user_id_'.concat(#userId)")
    public List<Role> queryRolesByUserId(Long userId) {
        QueryWrapper<UserRole> ew = new QueryWrapper<>();
        ew.eq("user_id", userId);
        System.out.println("你好嘿嘿啊 a ");
        List<UserRole> userRoleList = this.list(ew);
        System.out.println("userRoleList"+userRoleList);
        if (!CollectionUtils.isEmpty(userRoleList)) {
            List<Long> roleIds = new ArrayList<Long>();
            for (UserRole userRole : userRoleList) {
                roleIds.add(userRole.getRoleId());
            }
            QueryWrapper<Role> ewRole = new QueryWrapper<>();
            ewRole.in("id", roleIds);
            List<Role> roleList = roleService.list(ewRole);
            System.out.println("roleLis"+roleList);
            return roleList;
        } else {
            return null;
        }
    }
}
