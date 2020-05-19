package com.model.arithmetic3.config;

import com.model.arithmetic3.Inteceptor.AuthSignCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

/**
 * @Author： yaxuSong
 * @Description：
 * @Date： 2020/1/16 上午10:04
 * @MOdified by:
 **/
@Configuration
public class WebMvcConfigurer implements org.springframework.web.servlet.config.annotation.WebMvcConfigurer {

    @Autowired
    private AuthSignCheckInterceptor authSignCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] excludes = new String[]{"/login", "/register","/user/register",
                "/assets/**", "/images/**", "/vendors/**", "/static/**",
                "/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg",
                "/**/*.jpeg", "/**/*.gif", "/**/fonts/*", "/**/*.svg"};
        registry.addInterceptor(authSignCheckInterceptor).addPathPatterns("/**").excludePathPatterns(excludes);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/","classpath:/static/");
    }
}
