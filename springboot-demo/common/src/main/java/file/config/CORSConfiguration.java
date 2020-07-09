package file.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Description TODO
 * @Author susaifei
 * @Date 2018/7/27 12:56
 * @Version 1.0
 **/
@Configuration
public class CORSConfiguration {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedHeaders("*")
                        .allowCredentials(false)
                        .exposedHeaders("Accept-Ranges","Content-Range","Content-Encoding","Content-Length")
                        .allowedOrigins("*")
                        .maxAge(3600);

            }
        };
    }
}