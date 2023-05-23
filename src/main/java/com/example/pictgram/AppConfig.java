package com.example.pictgram;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*一つのBeanを他のBeanにフィールド値をコピーすることで、アプリケーション層とドメイン層で、データの受け渡しをする*/
@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}