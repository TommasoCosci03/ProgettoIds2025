package it.unicam.cs.ids25.model.Autenticazione;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * La classe WebSecurityConfig implementa la configurazione della sicurezza del sistema.
 * È responsabile dell'autenticazione degli utenti e della gestione delle autorizzazioni.
 */
    @Configuration
    @EnableWebSecurity
    public class WebSecurityConfig {
        private final CustomUserDetailsService customUserDetailsService;

        public WebSecurityConfig(CustomUserDetailsService customUserDetailsService) {
            this.customUserDetailsService = customUserDetailsService;
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers(HttpMethod.POST,"/api/aziende").permitAll()
                            .requestMatchers("/api/prodotti/**").authenticated()
                            .requestMatchers("/h2-console/**").permitAll()
                            .requestMatchers(HttpMethod.POST,"/api/acquirenti/creaAcquirente").permitAll()
                            .requestMatchers("/api/acquirenti/**").authenticated()
                            .requestMatchers("/api/ordine/**").authenticated()
                            .requestMatchers(HttpMethod.GET,"/api/marketplace/**").permitAll()
                            .requestMatchers("/api/gestore/**").authenticated()
                            .requestMatchers("/api/curatore/**").authenticated()
                            .requestMatchers(HttpMethod.POST,"/api/animatore/creaAnimatore").permitAll()
                            .requestMatchers("/api/animatore/**").authenticated()
                            .anyRequest().authenticated()
                    )
                    .csrf(AbstractHttpConfigurer::disable)
                    .headers(headers -> headers.frameOptions().disable())
                    .httpBasic(Customizer.withDefaults());

            return http.build();
        }

        @Bean
        public PasswordEncoder encoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public UserDetailsService userDetailsService(CustomUserDetailsService service) {
            return service;
        }

        @Bean
        public DaoAuthenticationProvider authenticationProvider() {
            DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
            provider.setUserDetailsService(customUserDetailsService);
            provider.setPasswordEncoder(encoder());
            return provider;
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
            return config.getAuthenticationManager();
        }
    }

