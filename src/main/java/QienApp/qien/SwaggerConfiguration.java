package QienApp.qien;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*	Swagger:
	http://localhost:8082/v2/api-docs		(JSON-format)
	http://localhost:8082/swagger-ui.html	(Leesbaar HTML-bestand)
*/

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()    
          .paths(PathSelectors.ant("/api/**")) 
          .apis(RequestHandlerSelectors.any())                      //  basePackage("QienApp.qien") 
          .build()
          .apiInfo(apiInfo());                                           
    }
    private ApiInfo apiInfo() {
        return new ApiInfo(
        		"Qien Urendeclaratie-applicatie",
        		"Voorbeeld API-info",
        		"1.0",
        		"Free to use",
        		new springfox.documentation.service.Contact("Groep A", "http://www.qien.nl", "e-mail"),
        		"API License", "http://www.qien.nl", Collections.emptyList()
        		);
    }
}