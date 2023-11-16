package com.jeebase.system.webchat.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jeebase.common.base.BusinessException;
import com.jeebase.system.common.util.TimeUtil;
import com.jeebase.system.security.dto.CreateUser;
import com.jeebase.system.security.service.IUserService;
import com.jeebase.system.webchat.dto.CreateWechatMember;
import com.jeebase.system.webchat.entity.Wechat;
import com.jeebase.system.webchat.entity.WechatMember;
import com.jeebase.system.webchat.mapper.WechatMemberMapper;
import com.jeebase.system.webchat.service.IWechatMemberService;
import com.jeebase.system.webchat.service.IWechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * <p>
 * 微信注册会员表 服务实现类
 * </p>
 *
 * @author jeebase
 * @since 2019-03-08
 */
@Service
public class WechatMemberServiceImpl extends ServiceImpl<WechatMemberMapper, WechatMember> implements IWechatMemberService {

    @Autowired
    WechatMemberMapper wechatMemberMapper;

    @Autowired
    IWechatService wechatService;

    @Autowired
    IUserService userService;

    @Override
    @Transactional
    public boolean saveNormalUser(CreateWechatMember createWechatMember){
        CreateUser createUser = new CreateUser();
        WechatMember wechatMember = new WechatMember();
        Long userId = null;
        boolean exist = false;
        BeanCopier.create(CreateUser.class, WechatMember.class, false).copy(createUser,
                wechatMember, null);
        BeanCopier.create(CreateWechatMember.class, WechatMember.class, false).copy(createWechatMember,
                wechatMember, null);

        String openId = createWechatMember.getWechatPlatformOpenId();
        String openId2 = createWechatMember.getWechatOpenId();
        String aliUserId = createWechatMember.getAliUserId();
        String aliOenId = createWechatMember.getAliUserId();
        Long WechatId = null;
        System.out.println("openid shikou"+openId);
        //如果微信openid不为空
        if (!StringUtils.isEmpty(openId)||!StringUtils.isEmpty(openId2)||!StringUtils.isEmpty(aliUserId)||!StringUtils.isEmpty(aliOenId))
        {
            QueryWrapper<WechatMember> wrapper = new QueryWrapper<>();
            wrapper
                    .eq(!StringUtils.isEmpty(openId),"wechat_platform_open_id",openId)
                    .or()
                    .eq(!StringUtils.isEmpty(openId2),"wechat_open_id", openId2).
                    or().
                    eq(!StringUtils.isEmpty(aliUserId),"ALI_USER_ID",aliUserId).
                    or().
                    eq(!StringUtils.isEmpty(aliUserId),"ALI_OPEN_ID",aliUserId);
            WechatMember user = wechatMemberMapper.selectOne(wrapper);

            QueryWrapper<Wechat> wechatWrapper = new QueryWrapper<>();
            wechatWrapper
                    .eq(!StringUtils.isEmpty(openId),"openid",openId)
                    .or()
                    .eq(!StringUtils.isEmpty(aliUserId),"ALI_USER_ID",aliUserId).
                    or().
                    eq(!StringUtils.isEmpty(aliUserId),"ALI_OPEN_ID",aliUserId);

            Wechat wechat = wechatService.getOne(wechatWrapper);
            //WechatId = wechat.getId();

            if (null != user)
            {
                exist = true;
                createUser.setUserNickName(user.getNickname());
                createUser.setHeadImgUrl(user.getAvatarUrl());
                userId = user.getId();
                createUser.setProvince(user.getProvince());
                createUser.setCity(user.getCity());
                createUser.setArea(user.getArea());
            }
            else
            {
                if (null != wechat)
                {
                    exist = false;
                    createUser.setUserNickName(wechat.getNickname());
                    createUser.setHeadImgUrl(wechat.getHeadimgurl());
                    wechatMember.setWechatPlatformOpenId(openId);
                    wechatMember.setNickname(wechat.getNickname());
                    wechatMember.setAvatarUrl(wechat.getHeadimgurl());
                    createUser.setProvince(wechat.getProvince());
                    createUser.setCity(wechat.getCity());
                }
            }

        }
        if(!StringUtils.isEmpty(createWechatMember.getBirthday())) {
            LocalDateTime ldt;
            try {
                long time = new SimpleDateFormat("yyyyMMdd").parse(createWechatMember.getBirthday()).getTime();
                Instant instant = Instant.ofEpochMilli(time);
                ZoneId zone = ZoneId.systemDefault();
                ldt = LocalDateTime.ofInstant(instant, zone);
                createUser.setBirthday(ldt);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                throw new BusinessException("生日格式有误");
            }

        }
        createUser.setUserAccount(createWechatMember.getMobile());
        createUser.setUserMobile(createWechatMember.getMobile());
        createUser.setUserPassword(createWechatMember.getUserPassword());
        createUser.setOrganizationId(createWechatMember.getOrganizationId());
        createUser.setRoleId(createWechatMember.getRoleId());
        createUser.setUserIdCard(createWechatMember.getUserIdCard());
        createUser.setUserName(createWechatMember.getRealname());
        createUser.setUserSex(createWechatMember.getGender()+"");

        boolean result = userService.createUser(createUser,StringUtils.isEmpty(aliUserId)?null:"AL");

        //如果是微信注册的，则有openId，直接更新，如果是后台创建的，那么没有openId，则新建用户
        if ((StringUtils.isEmpty(openId) &&StringUtils.isEmpty(aliUserId))|| !exist)
        {
            wechatMember.setUserId(createUser.getId());
            wechatMember.setId(null);
            System.out.println("要添加的信息"+wechatMember);
            result = this.save(wechatMember);

        }
        else
        {
            wechatMember.setId(userId);
            wechatMember.setParentId(WechatId);
            wechatMember.setUserId(createUser.getId());
            result = this.updateById(wechatMember);

            if(result) {
            }
        }
        return result;
    }

    @Override
    public WechatMember getSoftDeleteWechatMember(WechatMember wechatMember) {
        return wechatMemberMapper.getSoftDeleteWechatMember(wechatMember);
    }

    @Override
    public void recoverSoftDeleteWechatMember(WechatMember wechatMember) {
        wechatMemberMapper.recoverSoftDeleteWechatMember(wechatMember);
    }
}
