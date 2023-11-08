package com.kaci.blogspring0505;

import java.util.Date;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.kaci.blogspring0505.entities.Article;
import com.kaci.blogspring0505.entities.Commentaire;
import com.kaci.blogspring0505.entities.Compte;
import com.kaci.blogspring0505.entities.TypeCompte;
import com.kaci.blogspring0505.repository.IArticleRepository;
import com.kaci.blogspring0505.repository.ICommentaireRepository;
import com.kaci.blogspring0505.repository.ICompteRepository;
import com.kaci.blogspring0505.repository.ITypeCompteRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@SpringBootApplication
public class Blogspring0505Application {
    @Autowired // à remplacer par le constructeur All
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(Blogspring0505Application.class, args);
        System.out.println("Accueil : http://localhost:8080/index ");
    }

    // Initialisation des données /******* Exécution au démarrage*/
    // TypeCompte
    // @Bean
    CommandLineRunner start(ITypeCompteRepository iTypeCompteRepository, ICompteRepository iCompteRepository,
            IArticleRepository iArticleRepository,
            ICommentaireRepository iCommentaireRepository,
            JdbcUserDetailsManager jdbcUserDetailsManager) {
        // TypeCompte
        return args -> {
            Stream.of("ROLE_REDACT", "ROLE_MODER", "ROLE_ADMIN")
                    .forEach(label -> {
                        TypeCompte typeCompte = new TypeCompte();
                        typeCompte.setLabel(label);
                        iTypeCompteRepository.save(typeCompte);

                    });
        };
    }

    // Compte
    // @Bean
    CommandLineRunner start2(ITypeCompteRepository iTypeCompteRepository, ICompteRepository iCompteRepository,
            IArticleRepository iArticleRepository,
            ICommentaireRepository iCommentaireRepository,
            JdbcUserDetailsManager jdbcUserDetailsManager) {
        // Compte
        TypeCompte typeCompte1 = iTypeCompteRepository.findById(1L).orElse(null);
        return args -> {
            Stream.of("kaci", "muhend", "saly", "margot")
                    .forEach(pseudo -> {
                        Compte compte = new Compte();
                        compte.setPseudo(pseudo);
                        compte.setEmail(pseudo + "@hamr.fr");
                        compte.setMotDePasse(passwordEncoder.encode("abAB123!"));
                        compte.setBani(Math.random() > 0.1 ? false : true);
                        compte.setEfface((Math.random() > 0.1 ? false : true));
                        compte.setSignale(Math.random() > 0.1 ? false : true);
                        compte.setValide(Math.random() > 0.5 ? false : true);
                        compte.setTypeCompte(typeCompte1);
                        iCompteRepository.save(compte);
                        // BDD
                        jdbcUserDetailsManager.createUser(User.withUsername(compte.getPseudo())
                                .password(passwordEncoder.encode(compte.getMotDePasse())).roles("ROLE_REDACT").build());

                    });

            // Article
            Compte c1 = iCompteRepository.findById(1L).orElse(null);
            Compte c2 = iCompteRepository.findById(2L).orElse(null);
            Compte c3 = iCompteRepository.findById(3L).orElse(null);
            Compte c4 = iCompteRepository.findById(4L).orElse(null);
            Stream.of("La fac", "A développer", "Le social", "La com")
                    .forEach(titre -> {
                        Article article = new Article();
                        article.setTitre(titre);
                        article.setContenu("Contenu de " + "\'" + titre + "\'");
                        article.setDate(new Date());
                        article.setDateModif(new Date());
                        article.setModere(Math.random() > 0.5 ? false : true);
                        article.set_public(Math.random() > 0.1 ? true : false);
                        article.setCompte(
                                Math.random() > 0.4 ? c1
                                        : (Math.random() > 0.6 ? c2 : (Math.random() > 0.5 ? c3 : c4)));
                        iArticleRepository.save(article);

                    });
            // Commentaire
            Article article = iArticleRepository.findById(Math.random() > 0.8 ? 1L : 2L).orElse(null);
            Compte compte2 = iCompteRepository.findById(1L).orElse(null);
            Stream.of("Bien", "Très bien", "Passionnément", "Pas du tout", "Pas interessant")
                    .forEach(contenu -> {
                        Commentaire commentaire = new Commentaire();
                        commentaire.setContenu(contenu);
                        commentaire.setDate(new Date());
                        commentaire.set_public(Math.random() > 0.8 ? false : true);
                        commentaire.setModere(Math.random() > 0.8 ? false : true);
                        commentaire.setArticle(article);
                        commentaire.setCompte(Math.random() > 0.4 ? c1
                                : (Math.random() > 0.6 ? c2 : (Math.random() > 0.5 ? c3 : c4)));

                        iCommentaireRepository.save(commentaire);

                    });
        };

    }

}
