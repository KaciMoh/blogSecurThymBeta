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
        httpSecurity.formLogin(login -> login.defaultSuccessUrl("/redact/index")); // formulaire de connexion par défaut et démarrage
        //httpSecurity.formLogin(login -> login.loginProcessingUrl("/connection")); //formulaire de connexion personnalisé
        //httpSecurity.formLogin(login -> login.successForwardUrl("/connection")); // route vers '/login'

        // démarrage avec "/index" formulaire de connexion personnalisé obligatoire
        //httpSecurity.formLogin().loginPage("/login").defaultSuccessUrl("/public/index").permitAll();

        // ajout des autorisations // méthode 1
        httpSecurity.authorizeHttpRequests().requestMatchers("/public/**").permitAll();
        /*httpSecurity.authorizeHttpRequests().requestMatchers("/redact/**").hasRole("REDACT");
        httpSecurity.authorizeHttpRequests().requestMatchers("/moder/**").hasRole("MODER");
        httpSecurity.authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN");*/

        // Autoriser les différents outils de développement tels que 'bootstrap'
        httpSecurity.authorizeHttpRequests().requestMatchers("/css/**", "/js/**", "/webjars/**").permitAll();

        // requêtes à sécuriser
        httpSecurity.authorizeHttpRequests().anyRequest().authenticated(); // toutes les requêtes doivent être
                                                                           // authentifiées
        httpSecurity.exceptionHandling().accessDeniedPage("/nonautorise"); // message si route non autorisée
        // httpSecurity.userDetailsService(userDetailService);//bd-v2
        httpSecurity.logout().logoutSuccessUrl("/public/index").permitAll();
        httpSecurity.logout().logoutSuccessUrl("/index").permitAll();

        return httpSecurity.build();
    }
}
