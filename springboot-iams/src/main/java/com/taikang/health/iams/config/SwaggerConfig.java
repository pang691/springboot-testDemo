package com.taikang.health.iams.config;

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

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Token认证应用服务API接口")
                .description(initContextInfo())
                .contact(new Contact("健康险研发部医疗控费开发团队", null, null))
                .version("1.0")
                .build();
    }

    private String initContextInfo() {
        StringBuffer sb = new StringBuffer();
        sb.append("REST API 设计在细节上有很多自己独特的需要注意的技巧，并且对开发人员在构架设计能力上比传统 API 有着更高的要求。").append("<br/>")
                .append("以下是本项目的API文档");
        return sb.toString();
    }

    @Bean
    public Docket restfulApi() {
        //Predicate<RequestHandler> predicate = new PredicateImpl();
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com"))
                .paths(PathSelectors.any())
                .build();

    }
/*
    static class PredicateImpl implements Predicate<RequestHandler> {
        @Override
        public boolean apply(RequestHandler input) {
            if (input != null) {
                //只有添加了ApiOperation注解的method才在API中显示
                if (input.isAnnotatedWith(ApiOperation.class)) {
                    return true;
                }
            }
            return false;
        }
    }
*/
   /*( private List<ResponseMessage> customerResponseMessage() {
        return Lists.newArrayList(
                new ResponseMessageBuilder()//500
                        .code(500)
                        .message("系统繁忙！")
                        .responseModel(new ModelRef("Error"))
                        .build(),
                new ResponseMessageBuilder()//401
                        .code(401)
                        .message("参数错误！")
                        .build(),
                new ResponseMessageBuilder()//200
                        .code(200)
                        .message("执行成功！")
                        .build());
    }*/
}