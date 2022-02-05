package com.CadastroCliente.framework.interceptor

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@Configuration
class InterceptorConfig : WebMvcConfigurerAdapter() {


    @Value("\${tokenDecodificado}") // em base64 MTIzNjU0Nzg5
    val TokenFixo: String? = null

    @Bean
    fun requestHandler():AuthInterceptor {
        val Token = TokenFixo!!
        return AuthInterceptor(Token)
    }

    @Override
    override fun addInterceptors(registry: InterceptorRegistry) {

        requestHandler()?.let {
            registry.addInterceptor(it).excludePathPatterns(

            )
        }

    }
}