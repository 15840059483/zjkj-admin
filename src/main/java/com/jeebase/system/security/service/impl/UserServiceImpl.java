package com.jeebase.system.security.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jeebase.common.base.BusinessException;
import com.jeebase.common.base.Constant;
import com.jeebase.common.base.component.JwtComponent;
import com.jeebase.system.common.util.TimeUtil;
import com.jeebase.system.security.dto.CreateUser;
import com.jeebase.system.security.dto.QueryUser;
import com.jeebase.system.security.dto.UpdateUser;
import com.jeebase.system.security.dto.UserInfo;
import com.jeebase.system.security.entity.DataPermission;
import com.jeebase.system.security.entity.OrganizationUser;
import com.jeebase.system.security.entity.User;
import com.jeebase.system.security.entity.UserRole;
import com.jeebase.system.security.mapper.UserMapper;
import com.jeebase.system.security.service.IDataPermissionService;
import com.jeebase.system.security.service.IOrganizationUserService;
import com.jeebase.system.security.service.IUserRoleService;
import com.jeebase.system.security.service.IUserService;
import net.oschina.j2cache.CacheChannel;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: UserServiceImpl
 * @Description: 用户相关操作接口实现类
 * @author fyy
 * @date  
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private IOrganizationUserService organizationUserService;

    @Autowired
    private IDataPermissionService dataPermissionService;

    @Value("${system.defaultPwd}")
    private String defaultPwd;

    @Value("${system.defaultRoleId}")
    private Long defaultRoleId;

    @Value("${system.defaultOrgId}")
    private Long defaultOrgId;

    @Autowired
    private CacheChannel cacheChannel;

    @Override
    public Page<UserInfo> selectUserList(Page<UserInfo> page, QueryUser user) {
    	Page<UserInfo> pageUserInfo = null;
    	try {
    		if(user.getStartDate()!=null&&user.getEndDate()!=null) {
				String startDate = TimeUtil.ConversionTimeFormat(user.getStartDate().replace("Z", " UTC"), "yyyy-MM-dd'T'HH:mm:ss.SSS Z", "yyyy-MM-dd");
				String endDate = TimeUtil.ConversionTimeFormat(user.getEndDate().replace("Z", " UTC"), "yyyy-MM-dd'T'HH:mm:ss.SSS Z", "yyyy-MM-dd");
				user.setStartDate(startDate);
				user.setEndDate(endDate);
    		}
			pageUserInfo = userMapper.selectUserList(page, user);
    	} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return pageUserInfo;
		}
        return pageUserInfo;
    }

    @Override
    public boolean createUser(CreateUser user,String type) {
        QueryWrapper<User> ew = new QueryWrapper<>();
        ew.and(e -> e.eq("user_account", user.getUserAccount()).or().eq("user_account", user.getUserNickName()).or()
                .eq("user_account", user.getUserEmail()).or().eq("user_account", user.getUserMobile()).or()
                .eq("user_email", user.getUserAccount()).or().eq("user_email", user.getUserNickName()).or()
                .eq("user_email", user.getUserEmail()).or().eq("user_email", user.getUserMobile()).or()
                .eq("user_mobile", user.getUserAccount()).or().eq("user_mobile", user.getUserNickName()).or()
                .eq("user_mobile", user.getUserEmail()).or().eq("user_mobile", user.getUserMobile()))
                .isNull(StringUtils.isEmpty(type),"type")
                .eq(!StringUtils.isEmpty(type),"type",type);
        List<User> userList = this.list(ew);
        System.out.println("进来了");
        if (!CollectionUtils.isEmpty(userList)) {
            throw new BusinessException("账号已经存在");
        }

        if(null == user.getOrganizationId())
        {
            user.setOrganizationId(defaultOrgId);
        }

        Long roleId = user.getRoleId();
        List<Long> roleIds = user.getRoleIds();
        if (null == roleId && CollectionUtils.isEmpty(roleIds)) {
            // 默认值，改成配置
            roleId = defaultRoleId;
        }
        List<String> areas = user.getAreas();
        if (!CollectionUtils.isEmpty(areas)) {
            user.setProvince(areas.get(Constant.Address.PROVINCE));
            user.setCity(areas.get(Constant.Address.CITY));
            user.setArea(areas.get(Constant.Address.AREA));
        }
        User userEntity = new User();
        BeanCopier.create(CreateUser.class, User.class, false).copy(user, userEntity, null);
        String pwd = userEntity.getUserPassword();

        if(!StringUtils.isEmpty(type)){
            userEntity.setType("AL");
        }

        if (StringUtils.isEmpty(pwd)) {
            // 默认密码，配置文件配置
            pwd = defaultPwd;
            // 初次登录需要修改密码
            // userEntity.setUserStatus( "2" );
        }
        String cryptPwd = BCrypt.hashpw(userEntity.getUserAccount() + pwd, BCrypt.gensalt());
        userEntity.setUserPassword(cryptPwd);
        System.out.println(userEntity.getId()+"#########***********");
        boolean result = this.save(userEntity);
        System.out.println(userEntity.getId()+"#########***********");
        if (result) {
            //保存用户和组织机构的关系
        	Long organizationId = user.getOrganizationId();
            OrganizationUser orgUser = new OrganizationUser();
            orgUser.setUserId(userEntity.getId());
            orgUser.setOrganizationId(organizationId);
            organizationUserService.save(orgUser);

            //默认增加用户所在机构数据权限值，但是否有操作权限还是会根据资源权限判断
            DataPermission dataPermission = new DataPermission();
            dataPermission.setUserId(userEntity.getId());
            dataPermission.setOrganizationId(organizationId);
            dataPermissionService.save(dataPermission);

            //保存用户角色信息
            user.setId(userEntity.getId());
            user.setUserPassword(cryptPwd);
            
            if(!CollectionUtils.isEmpty(roleIds))
            {
                for (Long role : roleIds)
                {
                	UserRole userRole = new UserRole();
                    userRole.setUserId(userEntity.getId());
                    userRole.setRoleId(role);
                    result = userRoleService.save(userRole);
                }
            }
            else
            {
            	UserRole userRole = new UserRole();
                userRole.setUserId(userEntity.getId());
                userRole.setRoleId(roleId);
                result = userRoleService.save(userRole);
            }
        }
        return result;
    }

    @Override
    @CacheEvict(value = "users", key = "'id_'.concat(#user.id)")
    public boolean updateUser(UpdateUser user) {
        QueryWrapper<User> ew = new QueryWrapper<>();
        ew.ne("id", user.getId())
                .and(e -> e.eq("user_account", user.getUserAccount()).or().eq("user_account", user.getUserNickName())
                        .or().eq("user_account", user.getUserEmail()).or().eq("user_account", user.getUserMobile()).or()
                        .eq("user_nick_name", user.getUserAccount()).or().eq("user_nick_name", user.getUserNickName())
                        .or().eq("user_nick_name", user.getUserEmail()).or().eq("user_nick_name", user.getUserMobile())
                        .or().eq("user_email", user.getUserAccount()).or().eq("user_email", user.getUserNickName()).or()
                        .eq("user_email", user.getUserEmail()).or().eq("user_email", user.getUserMobile()).or()
                        .eq("user_mobile", user.getUserAccount()).or().eq("user_mobile", user.getUserNickName()).or()
                        .eq("user_mobile", user.getUserEmail()).or().eq("user_mobile", user.getUserMobile())).isNull("type");
        List<User> userList = this.list(ew);
        if (!CollectionUtils.isEmpty(userList)) {
            throw new BusinessException("账号已经存在");
        }
        List<String> areas = user.getAreas();
        if (!CollectionUtils.isEmpty(areas)) {
            user.setProvince(areas.get(Constant.Address.PROVINCE));
            user.setCity(areas.get(Constant.Address.CITY));
            user.setArea(areas.get(Constant.Address.AREA));
        }
        User userEntity = new User();
        System.out.println("方法"+user);
        BeanCopier.create(UpdateUser.class, User.class, false).copy(user, userEntity, null);
        System.out.println(userEntity);
        String pwd = userEntity.getUserPassword();
        User oldInfo = this.getById(userEntity.getId());
        System.out.println(oldInfo);
        if (!StringUtils.isEmpty(pwd)) {
            String cryptPwd = BCrypt.hashpw(oldInfo.getUserAccount() + pwd, BCrypt.gensalt());
            userEntity.setUserPassword(cryptPwd);
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", userEntity.getId());
        boolean result = this.update(userEntity, wrapper);

        //修改后更新缓存
        cacheChannel.evict("users", "account_" + oldInfo.getUserAccount());
        if (!StringUtils.isEmpty(oldInfo.getUserEmail()))
        {
            cacheChannel.evict("users", "account_" + oldInfo.getUserEmail());
        }
        if (!StringUtils.isEmpty(oldInfo.getUserMobile()))
        {
            cacheChannel.evict("users", "account_" + oldInfo.getUserMobile());
        }

        Long organizationId = user.getOrganizationId();
        QueryWrapper<OrganizationUser> organizationUserWrapper = new QueryWrapper<>();
        organizationUserWrapper.eq("user_id", userEntity.getId()).eq("organization_id", organizationId);
        OrganizationUser orgUserOld = organizationUserService.getOne(organizationUserWrapper);
        if (null == orgUserOld && null != organizationId)
        {
            QueryWrapper<OrganizationUser> organizationUserRemoveWrapper = new QueryWrapper<>();
            organizationUserRemoveWrapper.eq("user_id", userEntity.getId());
            OrganizationUser orgUserRemove = organizationUserService.getOne(organizationUserRemoveWrapper);
            QueryWrapper<DataPermission> dataPermissionRemoveWrapper = new QueryWrapper<>();
            dataPermissionRemoveWrapper.eq("user_id", userEntity.getId()).eq("organization_id", orgUserRemove.getOrganizationId());
            //删除旧机构的数据权限
            dataPermissionService.remove(dataPermissionRemoveWrapper);
            //删除旧机构的组织机构关系
            organizationUserService.remove(organizationUserRemoveWrapper);
            //保存用户和组织机构的关系
            OrganizationUser orgUser = new OrganizationUser();
            orgUser.setUserId(userEntity.getId());
            orgUser.setOrganizationId(organizationId);
            organizationUserService.save(orgUser);
            //默认增加用户所在机构数据权限值，但是否有操作权限还是会根据资源权限判断
            DataPermission dataPermission = new DataPermission();
            dataPermission.setUserId(userEntity.getId());
            dataPermission.setOrganizationId(organizationId);
            dataPermissionService.save(dataPermission);
        }

        List<Long> roleIds = user.getRoleIds();
        if (result && (null != user.getRoleId() || !CollectionUtils.isEmpty(roleIds))) {
            if(!CollectionUtils.isEmpty(roleIds))
            {
                //删除不存在的权限
                QueryWrapper<UserRole> wp = new QueryWrapper<>();
                wp.eq("user_id", userEntity.getId());
                List<UserRole> urList = userRoleService.list(wp);
                if (!CollectionUtils.isEmpty(urList)) {
                    for (UserRole role : urList)
                    {
                        //如果这个权限不存在，则删除
                        if (!roleIds.contains(role.getRoleId()))
                        {
                            QueryWrapper<UserRole> wpd = new QueryWrapper<>();
                            wpd.eq("user_id", userEntity.getId()).eq("role_id", role.getRoleId());
                            userRoleService.remove(wpd);
                        }
                    }
                }

                //新增库里不存在的权限
                for (Long role : roleIds)
                {
                    QueryWrapper<UserRole> oldWp = new QueryWrapper<>();
                    oldWp.eq("user_id", userEntity.getId()).eq("role_id", role);
                    UserRole oldUserRole = userRoleService.getOne(oldWp);
                    //查询出库中存在的角色列表，如果更新中的存在则不操作，如果不存在则新增
                    if(null == oldUserRole)
                    {
                        UserRole userRole = new UserRole();
                        userRole.setUserId(userEntity.getId());
                        userRole.setRoleId(role);
                        result = userRoleService.save(userRole);
                    }
                }
            }
            else if(null != user.getRoleId())
            {
                UserRole userRole = new UserRole();
                userRole.setUserId(userEntity.getId());
                userRole.setRoleId(user.getRoleId());
                QueryWrapper<UserRole> wp = new QueryWrapper<>();
                wp.eq("user_id", userEntity.getId()).eq("role_id", user.getRoleId());
                List<UserRole> urList = userRoleService.list(wp);
                //如果这个权限不存在，则删除其他权限，保存这个权限
                if (CollectionUtils.isEmpty(urList)) {
                    QueryWrapper<UserRole> wpd = new QueryWrapper<>();
                    wpd.eq("user_id", userEntity.getId());
                    userRoleService.remove(wpd);
                    result = userRoleService.save(userRole);
                }
            }
            cacheChannel.evict("roles", "user_id_" + userEntity.getId());
            cacheChannel.evict("resources", "user_id_" + userEntity.getId());
            cacheChannel.evict("resources", "all_user_id_" + userEntity.getId());
        }
        return result;
    }

    @Override
    @CacheEvict(value = "users", key = "'id_'.concat(#userId)")
    public boolean deleteUser(Long userId) {
        User oldInfo = this.getById(userId);
        boolean result = this.removeById(userId);
        if (result) {
            cacheChannel.evict("users", "account_" + oldInfo.getUserAccount());
            if (!StringUtils.isEmpty(oldInfo.getUserEmail()))
            {
                cacheChannel.evict("users", "account_" + oldInfo.getUserEmail());
            }
            if (!StringUtils.isEmpty(oldInfo.getUserMobile()))
            {
                cacheChannel.evict("users", "account_" + oldInfo.getUserMobile());
            }
            cacheChannel.evict("roles", "user_id_" + userId);
        }
        return result;
    }

    @Override
    public boolean batchDeleteUser(List<Integer> userIds) {
        List<User> userList = (List<User>) this.listByIds(userIds);
        for (User oldInfo : userList)
        {
            cacheChannel.evict("users", "account_" + oldInfo.getUserAccount());
            if (!StringUtils.isEmpty(oldInfo.getUserEmail()))
            {
                cacheChannel.evict("users", "account_" + oldInfo.getUserEmail());
            }
            if (!StringUtils.isEmpty(oldInfo.getUserMobile()))
            {
                cacheChannel.evict("users", "account_" + oldInfo.getUserMobile());
            }
            cacheChannel.evict("users", "id_" + oldInfo.getId());
            cacheChannel.evict("roles", "user_id_" + oldInfo.getId());
        }
        boolean result = this.removeByIds(userIds);
        return result;
    }

    @Override
    @Cacheable(value = "users", key = "'account_'.concat(#userAccount)")
    public User queryUserByAccount(String userAccount) {
        QueryWrapper<User> ew = new QueryWrapper<>();
        ew.and(e -> e.eq("user_account", userAccount).or().eq("user_email", userAccount).or().eq("user_mobile",
                userAccount));
        return this.getOne(ew);
    }

    
	@Override
	public boolean a() {
	
			User user1 = null;
	    	User user2 = new User();
	        this.save(user2);
	        int a = 1/0;
	        
		
		return false;
	}
	public static String sign(String userAccount, String secret, String expTimeType) {
        try {
            long expTime = 15768000000l;
            Date date = new Date(System.currentTimeMillis() + expTime);
            Algorithm algorithm = Algorithm.HMAC256(userAccount + secret);
            // 附带username信息
            return JWT.create().withClaim("useraccount", userAccount).withExpiresAt(date).sign(algorithm);
        } catch (Exception e) {
            return null;
        }
    }
	public static void main(String[] args) {
		String a ="18741040669";
		
		String hashpw = BCrypt.hashpw(a + "123456", BCrypt.gensalt());
		//String cryptPwd = BCrypt.hashpw(a + "123456", BCrypt.gensalt());
		System.out.println(hashpw);
		JwtComponent jwtComponent = new JwtComponent();
		String sign = sign(a, hashpw, Constant.ExpTimeType.APP);
		System.out.println(sign);
		String userAccount = jwtComponent.getUserAccount(sign);
		System.out.println(userAccount);
		Algorithm algorithm = Algorithm.HMAC256(userAccount + hashpw);
        JWTVerifier verifier = JWT.require(algorithm).withClaim("useraccount", userAccount).build();
        DecodedJWT verify = verifier.verify(sign);
		System.out.println(verify.getToken());
	}
    
    
}
