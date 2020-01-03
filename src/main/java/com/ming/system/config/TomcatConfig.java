package com.ming.system.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

import javax.servlet.MultipartConfigElement;

@Configuration
public class TomcatConfig {

    @Value("${spring.servlet.multipart.max-file-size}")
    private Integer maxFileSize;
    @Value("${spring.servlet.multipart.max-request-size}")
    private Integer maxRequestSize;
    /**
     * 1.0
     * @return
     */
//    @Bean
//    public MultipartConfigElement multipartConfigElement() {
//        MultipartConfigFactory factory = new MultipartConfigFactory();
//        //  单个数据大小
//        factory.setMaxFileSize(MaxFileSize); // KB,MB
//        /// 总上传数据大小
//        factory.setMaxRequestSize(MaxRequestSize);
//        return factory.createMultipartConfig();
//    }

    /**
     * 2.0
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大10M,DataUnit提供5中类型B,KB,MB,GB,TB
        factory.setMaxFileSize(DataSize.of(maxFileSize, DataUnit.MEGABYTES));
        /// 设置总上传数据总大小10M
        factory.setMaxRequestSize(DataSize.of(maxRequestSize, DataUnit.MEGABYTES));
        return factory.createMultipartConfig();
    }
}