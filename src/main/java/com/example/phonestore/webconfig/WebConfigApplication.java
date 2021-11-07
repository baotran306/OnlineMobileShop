package com.example.phonestore.webconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigApplication implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/admin/phone/*")
                .addPathPatterns("/admin/phone")
                .addPathPatterns("/admin/brand")
                .addPathPatterns("/admin/color")
                .addPathPatterns("/admin/updatePhone")
                .addPathPatterns("/admin/uploadPhone")
                .addPathPatterns("/admin/phone/save")
                .addPathPatterns("/admin/staff/update/save")
                .addPathPatterns("/admin/staff/upload-staff")
                .addPathPatterns("/admin/staff/delete")
                .addPathPatterns("/admin/order")
                .addPathPatterns("/admin/order/search")
                .addPathPatterns("/admin/customer");
        registry.addInterceptor(new StaffInterceptor())
                .addPathPatterns("/admin/staff")
                .addPathPatterns("/admin/staff/update/save")
                .addPathPatterns("/admin/staff/update")
                .addPathPatterns("/admin/staff/delete")
                .addPathPatterns("/admin/staff/reset");
    }
}
