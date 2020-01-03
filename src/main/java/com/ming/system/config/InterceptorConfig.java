package com.ming.system.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/*
* 拦截器配置类
* */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Value("${upload.image.realpath}")
    private String uploadImagePath;
    @Value("${upload.file.realpath}")
    private String uploadFilePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/static/upload/image/**").addResourceLocations("file:"+uploadImagePath);
        registry.addResourceHandler("/static/upload/file/**").addResourceLocations("file:"+uploadFilePath);
        super.addResourceHandlers(registry);
    }


    //所有的WebMvcConfigurerAdapter组件都会一起起作用
//    @Bean
//    public WebMvcConfigurer webMvcConfigurerAdapter(){
//        return new WebMvcConfigurer(){
//            //注册拦截器
//            @Override
//            public void addInterceptors(InterceptorRegistry registry) {
//                //静态资源； *.css,*.js
//                //SpringBoot已经做好了静态资源映射
//                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
//                .excludePathPatterns("/login.html");
//            }
//        };
//    }

}

