package com.nirima.noodle.gqlnoodle.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final Logger log = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.headers().frameOptions().disable()
                .and()
                .cors()
                .and()
                .csrf().disable();
//                .authorizeRequests(a -> a
//                        .antMatchers("/", "/error", "/webjars/**", "/app/**").permitAll()
//                        .antMatchers("/api/**").authenticated()
//                        .anyRequest().permitAll()
//                )
//
//                .exceptionHandling(e -> e
//                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
//                )
//                .oauth2ResourceServer()
//                .jwt()
//        ;

    }
}
