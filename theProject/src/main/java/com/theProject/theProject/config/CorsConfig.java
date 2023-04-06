//package com.theProject.theProject.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//@EnableWebMvc
//@Component
//public class CorsConfig implements WebMvcConfigurer{
//
//    //and also in application properties - allowed.origin=http://localhost:3000
//    //then this
////    @Value("${allowed.origin}")
////    private String allowedOrigin;
//
//    //http://localhost:9191/api/v1/employee
//
//    //////////////////////////////////
//    ////Should this be here twice????????????????
//    /////////////////////////////////////////
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry
//                        .addMapping("/**")
//                        .allowedMethods(CorsConfiguration.ALL)
//                        .allowedHeaders(CorsConfiguration.ALL)
//                        .allowedOriginPatterns(CorsConfiguration.ALL);
//            }
//        };
//    }
//
//}
//
