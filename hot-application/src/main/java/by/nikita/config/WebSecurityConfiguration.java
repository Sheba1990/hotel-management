package by.nikita.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter implements InitializingBean {

    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    @Autowired
    private UserDetailsService userDetailsService;

    String[] staticResources = {
            "/css/**",
            "/img/**",
            "/static/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(staticResources).permitAll()
                .antMatchers("/orders/username").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
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
                .userDetailsService(userDetailsService)
                .and()
                .exceptionHandling()
                .accessDeniedPage("/access_denied")
                .and()
                .logout().permitAll()
                .logoutSuccessUrl("/")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"));

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        springTemplateEngine.addDialect(new SpringSecurityDialect());
    }
}