package com.viralsville.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure( HttpSecurity http ) throws Exception {
        http.httpBasic().and().authorizeRequests().antMatchers( HttpMethod.GET, "/admin" ).hasRole( "ADMIN" ).antMatchers( HttpMethod.POST, "/content/**" ).hasRole( "ADMIN" ).and().csrf().disable();
    }

    @Autowired
    public void configureGlobal( AuthenticationManagerBuilder auth ) throws Exception {
        auth.inMemoryAuthentication().withUser( "admin" ).password( "password" ).roles( "ADMIN" );
    }
}
