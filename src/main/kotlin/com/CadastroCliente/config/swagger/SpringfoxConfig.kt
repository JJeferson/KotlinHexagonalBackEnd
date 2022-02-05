package com.CadastroCliente.config.swagger

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger.web.UiConfiguration
import springfox.documentation.swagger2.annotations.EnableSwagger2


@Configuration
@EnableSwagger2
class SpringfoxConfig {


    @Bean
    fun documentation(): Docket? {
        return Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .useDefaultResponseMessages(false)
            .select()
            .paths(PathSelectors.regex("/api/.*"))
            .build()
    }

    @Bean
    fun uiConfig(): UiConfiguration? {
        return UiConfiguration(null as String?)
    }

    fun apiInfo(): ApiInfo? {
        return ApiInfoBuilder()
            .title("API Kotlin Clientes")
            .version("1.0")
            .build()
    }

}