package com.kaci.blogspring0505.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Encodage des mots de passe
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean // création d'utilisateurs BD-v1
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    // création d'un filtre
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        // Route à prendre après authentification
        httpSecurity.formLogin(login -> login.defaultSuccessUrl("/redact/index")); // formulaire de connexion par défaut
                                                                                   // et démarrage

        // ajout des autorisations
        httpSecurity.authorizeHttpRequests().requestMatchers("/public/**").permitAll(); // 'public'
        httpSecurity.authorizeHttpRequests().requestMatchers("/redact/**").hasRole("REDACT"); // '/redact'autorisé pour
                                                                                              // les trois rôles
        httpSecurity.authorizeHttpRequests().requestMatchers("/redact/**").hasRole("MODER");
        httpSecurity.authorizeHttpRequests().requestMatchers("/redact/**").hasRole("ADMIN");
        httpSecurity.authorizeHttpRequests().requestMatchers("/moder/**").hasRole("MODER");// '/moder' autorisé pour
                                                                                           // MODER et ADMIN
        httpSecurity.authorizeHttpRequests().requestMatchers("/moder/**").hasRole("ADMIN");
        httpSecurity.authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN"); // '/moder' autorisé pour
                                                                                            // ADMIN

        // Autoriser les différents outils de développement tels que 'bootstrap', ...
        httpSecurity.authorizeHttpRequests().requestMatchers("/css/**", "/js/**", "/webjars/**").permitAll();

        // requêtes à sécuriser
        httpSecurity.authorizeHttpRequests().anyRequest().authenticated(); // toutes les requêtes doivent être
                                                                           // authentifiées

        // Message à afficher si la route est non autorisée
        httpSecurity.exceptionHandling().accessDeniedPage("/nonautorise");

        // Route à prendre après la déconnection
        httpSecurity.logout().logoutSuccessUrl("/public/index").permitAll();

        return httpSecurity.build();
    }
}
