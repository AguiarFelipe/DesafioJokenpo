package br.com.desafio.jokenpo.config;

import br.com.desafio.jokenpo.utils.SwaggerTags;
import com.google.common.collect.Sets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static io.swagger.models.Scheme.HTTP;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

    @Bean
    public Docket productApi() {
        final List<Tag> tags = SwaggerTags.tags();
        return new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.desafio.jokenpo"))
                .paths(PathSelectors.any()).build()
                .consumes(Sets.newHashSet(APPLICATION_JSON_VALUE))
                .produces(Sets.newHashSet(APPLICATION_JSON_VALUE))
                .protocols(Sets.newHashSet(HTTP.name()))
                .apiInfo(metaInfo())
                .tags(tags.get(0), tags.subList(1, tags.size()).toArray(Tag[]::new));

    }

    private ApiInfo metaInfo() {
        return new ApiInfoBuilder()
                .title("Challenge - Jokenpo")
                .description("Microsservices to manage a jokenpo game")
                .version("1.0.0.0")
                .contact(new Contact("Felipe Dutra de Aguiar", "https://www.linkedin.com/in/dutraaguiar/", "f.dutraaguiar@gmail.com"))
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.com/licenses/LICENSE-2.0")
                .build();
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}