package com.lixiaoyue.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SpringDoc OpenAPI 3 配置类（替代旧 SwaggerConfig）
 * 兼容 Spring Boot 2.7.x + JDK8，无冲突
 */
@Configuration
public class SwaggerConfig {

    /**
     * 全局配置接口文档信息（标题、版本、联系人等）
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // 1. 文档基本信息
                .info(new Info()
                        .title("lixiaoyue 后端项目 - API 文档") // 文档标题
                        .description("基于 Spring Boot 2.7.x + SpringDoc OpenAPI 3 的接口文档，包含用户管理、通用工具等核心模块") // 文档描述
                        .version("1.0.0") // 项目版本
                        // 联系人信息（可选）
                        .contact(new Contact()
                                .name("Patrick")
                                .email("wy_zhoujianli@163.com")
                                .url("https://example.com"))
                        // 许可证信息（可选）
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")))
                // 2. 全局安全配置（如需要 Token 认证，可添加 JWT 配置，可选）
                .addSecurityItem(new SecurityRequirement().addList("BearerAuth"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("BearerAuth", new SecurityScheme()
                                .name("Authorization")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER)
                                .description("请输入 Token：Bearer {token}")));
    }
}