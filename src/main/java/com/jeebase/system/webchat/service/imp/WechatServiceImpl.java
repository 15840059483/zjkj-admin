package com.jeebase.system.webchat.service.imp;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jeebase.system.webchat.entity.Wechat;
import com.jeebase.system.webchat.mapper.WechatMapper;
import com.jeebase.system.webchat.service.IWechatService;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jeebase
 * @since 2019-03-08
 */
@Service
public class WechatServiceImpl extends ServiceImpl<WechatMapper, Wechat> implements IWechatService {

}
