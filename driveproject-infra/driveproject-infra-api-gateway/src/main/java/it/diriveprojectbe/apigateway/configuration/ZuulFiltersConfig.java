package it.diriveprojectbe.apigateway.configuration;

import it.diriveprojectbe.apigateway.filter.UserHeaderFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ZuulFiltersConfig {
    @Bean
    public UserHeaderFilter userHeaderFilter() {
        return new UserHeaderFilter();
    }
}

