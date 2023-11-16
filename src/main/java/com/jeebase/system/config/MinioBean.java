package com.jeebase.system.config;


import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioBean {

    @Autowired
    private MInioConfig minioConfig;

    /**
     * 获取 MinioClient
     *
     * @return
     * @throws InvalidPortException
     * @throws InvalidEndpointException
     */
    @Bean
    public MinioClient minioClient() throws InvalidPortException, InvalidEndpointException {
        System.out.println(minioConfig.getEndpoint()+ minioConfig.getAccesskey()+ minioConfig.getSecretKey());
        return new MinioClient(minioConfig.getEndpoint(), minioConfig.getAccesskey(), minioConfig.getSecretKey());
    }
}
