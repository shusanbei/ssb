package edu.hue.jk.config;

import edu.hue.jk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private String[] missAuthorizeURL = {
            "/admin/left", "/admin/top", "/admin/bottom",   // 框架页
            "/admin/login/", "/admin/login/index",     // 跳转到登录页
            "/admin/login/login"                       // 执行登录操作
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();

        http
                .authorizeRequests()
                .antMatchers(missAuthorizeURL).permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/admin/")
                .loginProcessingUrl("/admin/login")
                .successForwardUrl("/admin/login").permitAll()
                .and().logout()
                .logoutUrl("/admin/logout")
                .logoutSuccessUrl("/admin/").permitAll()
                .and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/jquery/**", "/images/**", "/productImg/**");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}