package com.lql.Swagger2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by lql on 2018/9/25 10:11
 **/
@Configuration //标记配置类
@EnableSwagger2 //开启在线接口文档
public class Swagger2Config {
//    @Bean
//    public Docket controllerApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(new ApiInfoBuilder()
//                        .title("标题：XML解析接口文档")
//                        .description("描述：用于管理XML解析组件下的所有XML配置文件接口")
//                        .contact(new Contact("lql", null, null))
//                        .version("版本号:1.0")
//                        .build())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.lql"))
//                .paths(PathSelectors.any())
//                .build();
//    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.saytime.web"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("springboot利用swagger构建api文档")
                .description("简单优雅的restfun风格，http://blog.csdn.net/saytime")
                .termsOfServiceUrl("http://blog.csdn.net/saytime")
                .version("1.0")
                .build();
    }
}
