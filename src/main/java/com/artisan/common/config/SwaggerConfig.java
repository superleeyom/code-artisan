package com.artisan.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger2配置
 * @author leeyom
 * @date 2017年10月19日 10:41
 */
// @Configuration 这里需要注意，如果项目架构是SSM，那就不要加这个注解，如果是 spring boot 架构类型的项目，就必须加上这个注解，让 spring 加载该配置。
// spring boot 项目不需要添加此注解，SSM 项目需要加上此注解，否则将会报错。
@EnableWebMvc
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket buildDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo())
                .select().apis(RequestHandlerSelectors.basePackage("com.artisan.handler"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                .title("API接口文档")
                .build();
    }
}
