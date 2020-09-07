package by.nikita.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                // .antMatchers("/users", "/orders", "/rooms").access("hasRole('ADMIN')")
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .csrf()
                .and()
                .rememberMe()
                .rememberMeParameter("rememberme")
                .userDetailsService(service)
                .and()
                .exceptionHandling()
                .accessDeniedPage("/access_denied")
                .and()
                .logout().permitAll()
                .logoutSuccessUrl("/")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"));

    }
}