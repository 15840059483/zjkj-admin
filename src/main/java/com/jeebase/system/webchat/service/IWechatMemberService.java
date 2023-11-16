package com.jeebase.system.webchat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jeebase.system.webchat.dto.CreateWechatMember;
import com.jeebase.system.webchat.entity.WechatMember;

/**
 * <p>
 * 微信注册会员表 服务类
 * </p>
 *
 * @author jeebase
 * @since 2019-03-08
 */
public interface IWechatMemberService extends IService<WechatMember> {

    boolean saveNormalUser(CreateWechatMember createWechatMember);


    WechatMember getSoftDeleteWechatMember(WechatMember wechatMember);

    void recoverSoftDeleteWechatMember(WechatMember wechatMember);
}
