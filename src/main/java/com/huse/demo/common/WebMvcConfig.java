package com.huse.demo.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 声明这是一个配置类，Spring在启动时会自动加载并执行其中的配置
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 允许针对系统内所有的API接口（/**）进行跨域访问配置
        registry.addMapping("/**")
                // 明确允许前端在请求头中携带的自定义Header参数（如token、Content-Type等）
                .allowedHeaders("Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers", "token")
                // 允许所有种类的HTTP请求方法（GET, POST, PUT, DELETE等）
                .allowedMethods("*")
                // 允许来自任何源（域名/端口）的客户端进行访问
                .allowedOrigins("*")
                // 允许客户端请求携带凭证（如Cookie、认证Header等）
                .allowCredentials(true);
    }
}