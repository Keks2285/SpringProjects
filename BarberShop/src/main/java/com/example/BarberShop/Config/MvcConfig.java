package com.example.BarberShop.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("HomePage");
        registry.addViewController("/info").setViewName("info");
        registry.addViewController("/login").setViewName("Auth/Authorization");
    }
}
