package com.eemall.demo.config;

import com.eemall.demo.security.GoAccessDeniedHandler;
import com.eemall.demo.security.GoAuthenticationEntryPoint;
import com.eemall.demo.security.GoAuthenticationFailureHandler;
import com.eemall.demo.security.GoAuthenticationSuccessHandler;
import com.eemall.demo.security.GoLogoutSuccessHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Authentication Token Configuration
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling()
                .accessDeniedHandler(new GoAccessDeniedHandler())
                .authenticationEntryPoint(new GoAuthenticationEntryPoint())
                .and().authorizeRequests()
                .antMatchers("/register", "/csrf").permitAll()
                .and().formLogin()
                .loginProcessingUrl("/login").permitAll()
                .successHandler(new GoAuthenticationSuccessHandler())
                .failureHandler(new GoAuthenticationFailureHandler())
                .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(new GoLogoutSuccessHandler())
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and().requiresChannel()
                .antMatchers("/pomer").requiresSecure()
                .anyRequest().requiresInsecure()
                .and().rememberMe()
                .tokenValiditySeconds(3600)
                .key("token_key");
    }
}

