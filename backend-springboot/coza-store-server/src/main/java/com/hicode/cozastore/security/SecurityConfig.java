package com.hicode.cozastore.security;

import com.hicode.cozastore.filter.AuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests().antMatchers("/token/refresh/**","/login/**","/forwordpassword","/api/v1/**","/graphql","/user/myprofile/**","/graphiql/**").permitAll()//không cần cấp quyền
                .and()
                .authorizeRequests().antMatchers(HttpMethod.POST,"/api/v1/admin/**").hasAuthority("ROLE_ADMIN")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/v1/admin/**").hasAuthority("ROLE_ADMIN")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.PUT,"/api/v1/admin/**").hasAuthority("ROLE_ADMIN")
                .and()
                .authorizeRequests().anyRequest().authenticated()//tất cả mọi role
                .and()
                .addFilterBefore(new AuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
