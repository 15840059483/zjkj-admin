package com.jeebase.system.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

import java.util.Arrays;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

/**
 * @author fyy
 */
@Configuration
public class FastjsonConfiguration {

    @Bean
    public HttpMessageConverters fastjsonConverter() {
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        // 自定义格式化输出
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat, SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullNumberAsZero);
        FastJsonHttpMessageConverter fastjson = new FastJsonHttpMessageConverter();
        fastjson.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON_UTF8, MediaType.parseMediaType("application/x-www-form-urlencoded")));
        fastjson.setFastJsonConfig(fastJsonConfig);
        return new HttpMessageConverters(fastjson);
    }
}
