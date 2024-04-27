package com.be.authenticate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/swagger-ui/**", "/v3/api-docs/**");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(PUBLIC_LIST)
                .permitAll()
                .antMatchers(ROLE_ADMIN_LIST)
                .hasAnyRole("ADMIN")
                .antMatchers(ROLE_USER_LIST)
                .hasAnyRole("USER")
                .antMatchers(AUTHENTICATED_LIST)
                .authenticated()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .logout().clearAuthentication(true).invalidateHttpSession(true)
                .and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "HEAD", "OPTIONS"));
        configuration.setAllowCredentials(false);
        configuration.setAllowedHeaders(Arrays.asList("Authorization","Cache-Control","Content-Type"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    String[] PUBLIC_LIST = {
//            "/api/test/post","/api/test/get/*","/api/test/put/*","/api/test/del/*",
            "/api/nguoi-dung/login",  "/api/nguoi-dung/create"
    };

    String[] AUTHENTICATED_LIST = {
//            "/api/nguoi-dung/get/*"
    };

    String[] ROLE_USER_LIST = {
//            role user
    };

    String[] ROLE_ADMIN_LIST = {
            "/api/nguoi-dung//change-password","/api/nguoi-dung/get/*",
           "/api/nguoi-dung/put/*","/api/nguoi-dung/del/*",
            "/api/files","/api/files/*","/api/upload",
            "/api/decor-type/post","/api/decor-type/get/*","/api/decor-type/put/*","/api/decor-type/del/*",
            "/api/decor/post","/api/decor/get/*","/api/decor/put/*","/api/decor/del/*",
            "/api/favorite/post","/api/favorite/get/*","/api/favorite/put/*","/api/favorite/del/*",
            "/api/cart/post","/api/cart/get/*","/api/cart/put/*","/api/cart/del/*",
            "/api/bill/post","/api/bill/get/*","/api/bill/put/*","/api/bill/del/*",
            "/api/bill-detail/post","/api/bill-detail/get/*","/api/bill-detail/put/*","/api/bill-detail/del/*",
            "/api/history-bill/post","/api/history-bill/get/*","/api/history-bill/put/*","/api/history-bill/del/*",
            "/api/history-find/post","/api/history-find/get/*","/api/history-find/put/*","/api/history-find/del/*",
    };

}
