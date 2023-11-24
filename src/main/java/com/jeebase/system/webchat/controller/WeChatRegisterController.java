package com.jeebase.system.webchat.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jeebase.common.annotation.auth.NoAuthentication;
import com.jeebase.common.annotation.log.AroundLog;
import com.jeebase.common.base.BusinessException;
import com.jeebase.common.base.Constant;
import com.jeebase.common.base.Result;
import com.jeebase.common.base.component.JwtComponent;
import com.jeebase.system.common.service.ISmsService;
import com.jeebase.system.security.entity.User;
import com.jeebase.system.security.service.IResourceService;
import com.jeebase.system.security.service.IUserRoleService;
import com.jeebase.system.security.service.IUserService;
import com.jeebase.system.webchat.dto.CreateWechatMember;
import com.jeebase.system.webchat.dto.WeChatSms;
import com.jeebase.system.webchat.service.IWechatMemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.CacheObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/wechat/register")
@Api(value = "WeChatRegisterController|微信服务号注册相关的前端控制器")
public class WeChatRegisterController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IWechatMemberService wechatMemberService;

    @Autowired
    private IUserService userService;

    @Autowired
    private JwtComponent jwtComponent;

    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private IResourceService resourceService;

    @Autowired
    ISmsService iSmsService;

    @Value("${system.smsTimes}")
    private int smsTimes;

    @Value("${system.defaultRoleId}")
    private int defaultRoleId;

    @Value("${system.defaultOrgId}")
    private int defaultOrgId;

    @Autowired
    private CacheChannel cacheChannel;

    /**
     * 发送短信验证码
     */
    @PostMapping("/sms/send")
    @NoAuthentication
    @ApiOperation(value = "注册用户时，发送短信验证码")
    public Result<?> sendSmsReg(@Valid @RequestBody WeChatSms smsDto) {
        String phoneNumber = smsDto.getMobile();
        QueryWrapper<User> ew = new QueryWrapper<>();
        ew.eq("user_account", phoneNumber).or().eq("user_email", phoneNumber)
                .or().eq("user_mobile", phoneNumber);
        List<User> userList = userService.list(ew);
        if (!CollectionUtils.isEmpty(userList)) {
            throw new BusinessException("手机号码已经注册");
        }
        CacheObject smsTimesCache = cacheChannel.get("smsTimes", phoneNumber + "_sms_times");
        Integer vcodeNumbers = (Integer) smsTimesCache.getValue();
        if (null != vcodeNumbers) {
            int num = vcodeNumbers.intValue();
            if (num >= smsTimes) {
                return new Result<>().error("验证码发送超过最大次数");
            }
            cacheChannel.set("smsTimes", phoneNumber + "_sms_times", num + 1);
        } else {
            cacheChannel.set("smsTimes", phoneNumber + "_sms_times", 1);
        }
        String smsCode = String.valueOf(new Random().nextInt(899999) + 100000);
        System.out.println("注册短信：" + smsCode);
        iSmsService.sendVcodeSms(phoneNumber, smsCode);
        cacheChannel.set("smsCode", phoneNumber + "_sms_reg", smsCode);
        return new Result<>().success("验证码发送成功");
    }

    /**
     * 注册普通用户.
     */
    @PostMapping("/normal")
    @NoAuthentication
    @ApiOperation(value = "注册普通用户")
    @AroundLog(name = "注册普通用户")
    public Result<?> create( CreateWechatMember createWechatMember) {
    	System.out.println(createWechatMember);
    	createWechatMember.setUserPassword("kl9999");
//        String phoneNumber = createWechatMember.getMobile();
//        String userSmsCode = createWechatMember.getSmsCode();
//        CacheObject smsCodeCache = cacheChannel.get("smsCode", phoneNumber + "_sms_reg");
//        String smsCode = (String) smsCodeCache.getValue();
//        if (StringUtils.isEmpty(smsCode)) {
//            return new Result<>().error("短信验证码已失效，请重新获取");
//        }
//        if (!smsCode.equals(userSmsCode)) {
//            return new Result<>().error("短信验证码错误，请重新输入");
//        }
        logger.info("注册普通用户参数：" + JSONObject.toJSON(createWechatMember));
        createWechatMember.setRoleId((long) defaultRoleId);
        createWechatMember.setOrganizationId((long) defaultOrgId);
        boolean result = wechatMemberService.saveNormalUser(createWechatMember);
        if (result) {
        	QueryWrapper<User> ew = new QueryWrapper<>();
            ew.eq("user_account", createWechatMember.getMobile());
            User user = userService.getOne(ew);
            System.out.println("第一次密码"+user.getUserPassword());
        	String token = jwtComponent.sign(createWechatMember.getMobile(), user.getUserPassword(), Constant.ExpTimeType.APP);
            return new Result<>().put(token).success("注册成功");
        } else {
            return new Result<>().error("注册失败，请重试");
        }
    }
    public static void main(String[] args) {
    	Algorithm algorithm = Algorithm.HMAC256("17824932052" + "$2a$10$jLO65RPB3aILhmfnYSU9a.Q78aPby0Dy6XglKMNqUW8UrHdv8Ci4i");
        JWTVerifier verifier = JWT.require(algorithm).withClaim("useraccount", "17824932052").build();
        verifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MjI2OTE5MDUsInVzZXJhY2NvdW50IjoiMTc4MjQ5MzIwNTIifQ.L5EaD65z3mt_PFHRuXkitx0LcW4givRlQus94WtH2U4");
	}
}
