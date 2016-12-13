package com.db.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:application-default.xml")
public class PropertiesJavaConfig {
	
    public PropertiesJavaConfig() {
        super();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
    	PropertySourcesPlaceholderConfigurer prop = new PropertySourcesPlaceholderConfigurer();
    	prop.setIgnoreResourceNotFound(true);
    	prop.setIgnoreUnresolvablePlaceholders(true);
    	prop.setProperties(System.getProperties());
    	prop.setLocalOverride(true);
        return prop;
    }

}
