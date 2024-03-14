package kr.co.ch04.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"kr.co.ch04"})

public class AppConfig implements WebMvcConfigurer {

    @java.lang.Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        //ViewResulver 설정 - jsp 경로 위치 설정
        registry.jsp("/WEB-INF/views/",".jsp");

    }

    @java.lang.Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //ResourceHandler 설정 - 정적 리소스의 경로 위치 설정
        registry.addResourceHandler("/**").addResourceLocations("/resources/");

    }
}
