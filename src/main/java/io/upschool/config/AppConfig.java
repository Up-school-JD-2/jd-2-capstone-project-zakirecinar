package io.upschool.config;

import io.upschool.util.CreditCardMaskingUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public CreditCardMaskingUtil maskingUtil() {
        return new CreditCardMaskingUtil();
    }
}