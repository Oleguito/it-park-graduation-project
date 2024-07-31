package ru.itpark.authservice.infrastructure.config.app.cors;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {



//    @Bean
//    CorsFilter corsFilter() {
//        CorsConfigurationSource corsConfigurationSource = new CorsConfigurationSource() {
//            @Override
//            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
//                CorsConfiguration corsConfiguration = new CorsConfiguration();
//                corsConfiguration.applyPermitDefaultValues();
//                return corsConfiguration;
//            }
//        };
//        return new CorsFilter(corsConfigurationSource);
//    }

//    @Bean
//    CorsConfiguration corsConfiguration() {
//        return new CorsConfiguration().applyPermitDefaultValues();
//    }

//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry
//                        .addMapping("/**")
//                        .allowedMethods("*")
//                        .allowedHeaders("*")
//                        .allowedOrigins("*");
//            }
//        };
//    }
}
