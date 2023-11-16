package com.jeebase.system.common.component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @ClassName: MyMetaObjectHandler
 * @Description: 自定义填充公共字段
 * @author fyy
 * @date
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Object creator = getFieldValByName("creator", metaObject);
        if (Objects.isNull(creator) && !Objects.isNull(SecurityUtils.getSubject())  ) {
            String principal = (String) SecurityUtils.getSubject().getPrincipal();
            if (!StringUtils.isEmpty(principal)) {
                JSONObject userObj = JSON.parseObject(principal);
                setFieldValByName("creator", userObj.getLong("id"), metaObject);
            }
        }
        Object createTime = getFieldValByName("createTime", metaObject);
        if (Objects.isNull(createTime)) {
            setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        }
        Object delFlag = getFieldValByName("delFlag", metaObject);
        if (Objects.isNull(delFlag)) {
            setFieldValByName("delFlag","0", metaObject);
        }
        Object REFUND_FEE = getFieldValByName("REFUND_FEE", metaObject);
        if(Objects.isNull(REFUND_FEE)) {
            setFieldValByName("REFUND_FEE","0.00", metaObject);
        }
        Object REFUND_STATE = getFieldValByName("REFUND_STATE", metaObject);
        if(Objects.isNull(REFUND_STATE)) {
            setFieldValByName("REFUND_STATE","3022", metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object operator = getFieldValByName("operator", metaObject);
        if (null == operator && null != SecurityUtils.getSubject()) {
            String principal = (String) SecurityUtils.getSubject().getPrincipal();
            if (!StringUtils.isEmpty(principal)) {
                JSONObject userObj = JSON.parseObject(principal);
                setFieldValByName("operator", userObj.getLong("id"), metaObject);
            }
        }
        Object updateTime = getFieldValByName("updateTime", metaObject);
        if (null == updateTime) {
            setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
        Object paymentTime = getFieldValByName("paymentTime", metaObject);
        if (null == paymentTime) {
            LocalDateTime now = LocalDateTime.now();
            String format = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            setFieldValByName("paymentTime", format, metaObject);
        }
    }
}
