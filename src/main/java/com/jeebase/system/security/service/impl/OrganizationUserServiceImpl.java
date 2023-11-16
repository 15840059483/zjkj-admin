package com.jeebase.system.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jeebase.system.security.entity.OrganizationUser;
import com.jeebase.system.security.mapper.OrganizationUserMapper;
import com.jeebase.system.security.service.IOrganizationUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author fyy
 * @since  
 */
@Service
public class OrganizationUserServiceImpl extends ServiceImpl<OrganizationUserMapper, OrganizationUser>
        implements IOrganizationUserService {
}
