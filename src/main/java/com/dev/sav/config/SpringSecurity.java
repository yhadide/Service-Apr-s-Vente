package com.dev.sav.config;


import com.dev.sav.security.CustomTechnicienDetails;
import com.dev.sav.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
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
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/register/**", "/client/registerclient", "/client/registerclient/save","/techniciens/registertechnicien","/techniciens/registertechnicien/add").permitAll()
                                .requestMatchers("/index").permitAll()
                                .requestMatchers("/error").permitAll()
                                .requestMatchers("/dossiers/update/**").permitAll()
                                .requestMatchers("/client/clients-json").permitAll()
                                .requestMatchers("/techniciens/techniciens-json", "/techniciens/toggle-status" ).permitAll()
                                .requestMatchers("/techniciens/technicienslist").permitAll()
                                .requestMatchers("/techniciens/**").hasRole("TECHNICIEN")
                                .requestMatchers("/client/**").hasRole("CLIENT")
                                .requestMatchers("/appels/**", "/articles/**").permitAll()
                                .requestMatchers("/dossiers/**").hasRole("ADMIN")
//                                .requestMatchers("/techniciens").hasRole("ADMIN")
                                .requestMatchers("/utilisateurs").hasRole("ADMIN")
                                .requestMatchers("/js/**", "/styles/**").permitAll()
                ).formLogin(form ->
                        form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .successHandler((request, response, authentication) -> {
                                    if (authentication.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"))) {
                                        System.out.println("User has ROLE_ADMIN");
                                        response.sendRedirect("/utilisateurs");
                                    } else if (authentication.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_CLIENT"))) {
                                        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
                                        int clientId = userDetails.getClient().getNoClient();
                                        System.out.println("User has ROLE_CLIENT with ID: " + clientId);
                                        response.sendRedirect("/client/" + clientId);
                                    } else if (authentication.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_TECHNICIEN"))) {
                                        CustomTechnicienDetails technicienDetails = (CustomTechnicienDetails) authentication.getPrincipal();
                                        int technicienId = technicienDetails.getTechnicien().getIdTechnicien();
                                        System.out.println("User has ROLE_TECHNICIEN with ID: " + technicienId);
                                        response.sendRedirect("/techniciens/" + technicienId);
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
