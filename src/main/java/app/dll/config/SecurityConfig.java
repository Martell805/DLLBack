package app.dll.config;

import app.dll.service.DBUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final DBUserDetailsService dbUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(dbUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        return new InMemoryUserDetailsManager();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsManager())
                .passwordEncoder(passwordEncoder())
                .and().build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .httpBasic().and()
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "api/user").permitAll()
                        .requestMatchers("api/user/login").permitAll()
                        .requestMatchers("api/user").hasAuthority("MODER")
                        .requestMatchers("api/user/all").hasAuthority("MODER")

                        .requestMatchers(HttpMethod.GET, "api/author").permitAll()
                        .requestMatchers("api/author/all").permitAll()
                        .requestMatchers(HttpMethod.POST, "api/author").authenticated()

                        .requestMatchers(HttpMethod.GET, "api/book").permitAll()
                        .requestMatchers(HttpMethod.POST, "api/book").authenticated()
                        .requestMatchers("api/book/all").permitAll()
                        .requestMatchers("api/book/all/moderate").hasAuthority("MODER")
                        .requestMatchers("api/book/approve").hasAuthority("MODER")

                        .requestMatchers("**").permitAll()
                )
                .csrf().disable()
                .authenticationManager(authenticationManager(httpSecurity))
                .build();
    }
}
