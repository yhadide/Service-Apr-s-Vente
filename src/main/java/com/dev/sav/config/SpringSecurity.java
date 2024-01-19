package com.dev.sav.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/register/**").permitAll()
                                .requestMatchers("/clients").hasRole("CLIENT")  // Restrict access to client-specific pages to CLIENT role
                                .requestMatchers("/index").permitAll()
                                .requestMatchers("/utilisateurs").hasRole("ADMIN")
                                .requestMatchers("/client/registerclient").permitAll()  // Allow access to client registration form
                                .requestMatchers("/client/registerclient/save").permitAll()  // Allow client registration form submission
                                .requestMatchers("/client/listclients").hasRole("ADMIN")  // Restrict access to clients to ADMIN role
                ).formLogin(form ->
                        form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .successHandler((request, response, authentication) -> {
                                    authentication.getAuthorities().forEach(authority -> {
                                        System.out.println("User has authority: " + authority.getAuthority());
                                    });
                                    if (authentication.getAuthorities().stream()
                                            .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"))) {
                                        response.sendRedirect("/utilisateurs");
                                    } else if (authentication.getAuthorities().stream()
                                            .anyMatch(authority -> authority.getAuthority().equals("ROLE_CLIENT"))) {
                                        response.sendRedirect("/clients");
                                    } else {
                                        throw new IllegalStateException("Unexpected role");
                                    }
                                })
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
