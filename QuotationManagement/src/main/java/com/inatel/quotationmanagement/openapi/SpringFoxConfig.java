package com.inatel.quotationmanagement.openapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SpringFoxConfig implements WebMvcConfigurer {

    @Bean
    public Docket apiDocket(){
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.inatel.quotationmanagement.controllers"))
                .build().apiInfo(apiInfo())
                .tags(new Tag("Stock", "Stock endpoints"), new Tag("StockCache", "StockCache endpoints"))
                .useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.GET, globalGetResponseMessages())
                .globalResponses(HttpMethod.POST, globalResponseMessages())
                .globalResponses(HttpMethod.DELETE, globalDeleteMessages());
    }

    private List<Response> globalGetResponseMessages() {
        return Arrays.asList(
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Internal server error.")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.OK.value()))
                        .description("THe server was able to fetch the requested data.")
                        .build()
        );
    }

    private List<Response> globalDeleteMessages() {
        return Arrays.asList(
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Internal server error.")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.NO_CONTENT.value()))
                        .description("All cached stock information was deleted.").build()
        );
    }

    private List<Response> globalResponseMessages() {
        return Arrays.asList(
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Internal server error.")
                        .build(),
          new ResponseBuilder()
                  .code(String.valueOf(HttpStatus.CREATED.value()))
                  .description("Stock and Quotes registered.").build(),
                new ResponseBuilder().code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value())).description("Stock isn't registered with stock-manager.")
                        .build()
        );
    }

    public ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("Quotes Manager").description("API built as a technical evaluation regarding the Developer position at INATEL")
                .version("1.0.0")
                .contact(new Contact("Leonardo Camargo", "", "leonardocccamargo@hotmail.com")).build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classPath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
