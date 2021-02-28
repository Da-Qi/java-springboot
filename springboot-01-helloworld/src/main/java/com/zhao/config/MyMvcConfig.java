package com.zhao.config;

import com.zhao.component.LoginHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.ResourceUrlEncodingFilter;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/register.html").setViewName("register");
        registry.addViewController("/header.html").setViewName("header");
        registry.addViewController("/footer.html").setViewName("footer");
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/personal.html").setViewName("personal");
        //管理员
        registry.addViewController("/admin/index").setViewName("admin/1");
        //页面详情
        registry.addViewController("/limo_details.html").setViewName("limo_details");
        registry.addViewController("/route_details.html").setViewName("route_details");
        //房车
        registry.addViewController("/theme.html").setViewName("theme");
        registry.addViewController("/general.html").setViewName("general");
        //路线
        registry.addViewController("/beijing.html").setViewName("beijing");
        registry.addViewController("/global.html").setViewName("global");
        registry.addViewController("/hongkong.html").setViewName("hongkong");
        registry.addViewController("/macao.html").setViewName("macao");
        registry.addViewController("/northAmerican.html").setViewName("northAmerican");
        registry.addViewController("/team.html").setViewName("team");
        registry.addViewController("/yunnan.html").setViewName("yunnan");
        registry.addViewController("/recommend.html").setViewName("recommend");
        registry.addViewController("/favoriterank.html").setViewName("favoriterank");
        registry.addViewController("/forumIndex").setViewName("forumIndex");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Bean
    public ResourceUrlEncodingFilter resourceUrlEncodingFilter() {
        return new ResourceUrlEncodingFilter();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/personal.html");
    }
}
