package com.eb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity //to activate spring security
@EnableGlobalMethodSecurity(prePostEnabled = true) //security will be enabled in method base
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter
{
    /*
        1. authentication manager
        2. authentication provide
        3. encode password
     */
    @Autowired
    private UserDetailsService userDetailsService;


    //override method to configure http request
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.csrf().disable(). //disable cross site request forgery
                authorizeHttpRequests(). //authorize http requests
                antMatchers("/", "index.html", "/css/*", "/js/*"). //these end points are permitted no aouthentication needed for this endpoint
                permitAll().
                anyRequest(). //all other requests
                authenticated(). //should be authenticated
                and().
                httpBasic(); //use basic authentication
    }

    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder(10); //for the strength as we can provide number between 4 and 31
        //(starting from weakest to strongest, but strongest strength will take some time and energy, around 10 is eough)
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        //introduced userDetailsService and password encoder to authenticationProvider
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
}
