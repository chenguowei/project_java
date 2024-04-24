package org.example.springbootsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user")
                .password(encoder.encode("123456"))
                .roles("USER")
                .build());
        manager.createUser(User.withUsername("admin")
                .password(encoder.encode("123456"))
                .roles("USER", "ADMIN")
                .build());
        return manager;
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        http.authorizeHttpRequests(auth->auth.requestMatchers("/login").permitAll()
////                .anyRequest().authenticated());
//
//        //对所有请求按照以下约定进行拦截和放行
//        http.authorizeHttpRequests(
//                //requestMatchers 指定匹配路径
//                //permitAll 让security跳过之前通过requestMatchers匹配到的路径，
//                auth -> auth.requestMatchers("/login").permitAll()
//                        //anyRequest 指定除requestMatchers匹配路径之外的其他路径
//                        //authenticated 让anyRequest匹配到的所有路径都通过security校验
//                        .anyRequest().authenticated()
//        );
//
//        http.csrf(csrf->csrf.disable());
//
//        return http.build();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
