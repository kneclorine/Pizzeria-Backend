package com.example.demo.core.configurationBeans;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MapperConfiguration {
    
   @Bean
   @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
   public ModelMapper modelMapper() {
      ModelMapper modelMapper = new ModelMapper();
      return modelMapper;
   }
}
