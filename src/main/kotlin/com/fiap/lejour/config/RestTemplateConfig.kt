package com.fiap.lejour.config

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean

import org.springframework.web.client.RestTemplate
import java.time.Duration


class RestTemplateConfig {

    @Bean
    fun restTemplate(builder: RestTemplateBuilder): RestTemplate? {
        return builder
                .setConnectTimeout(Duration.ofMillis(3000))
                .setReadTimeout(Duration.ofMillis(3000))
                .build()
    }

}