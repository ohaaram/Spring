package kr.co.ch10t.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(){

        Info info = new Info()
                .title("ch10 API 목록")
                .version("1.0")
                .description("ch10 실습");

        return new OpenAPI().components(new Components())
                .info(info);
    }
}
