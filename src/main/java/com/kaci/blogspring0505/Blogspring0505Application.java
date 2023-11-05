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
    //@Bean
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
        };}

        // Compte
        // @Bean
        CommandLineRunner start2(ITypeCompteRepository iTypeCompteRepository, ICompteRepository iCompteRepository,
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

                TypeCompte typeCompte1 = iTypeCompteRepository.findById(1L).orElse(null);

            Stream.of("Moh", "Kaci", "Sali", "Margoche")
                    .forEach(pseudo -> {
                        Compte compte = new Compte();
                        compte.setPseudo(pseudo);
                        compte.setEmail(pseudo + "@hamroun.fr");
                        compte.setMotDePasse("azAZ12!@");
                        compte.setBani(Math.random() > 0.1 ? false : true);
                        compte.setEfface((Math.random() > 0.1 ? false : true));
                        compte.setSignale(Math.random() > 0.1 ? false : true);
                        compte.setValide(Math.random() > 0.7 ? false : true);
                        compte.setTypeCompte(typeCompte1);

                        iCompteRepository.save(compte);

                        // Création de l'utilisateur correspondant à l'étudiant et attribution des rôles
                        /*
                         * // BD-v2
                         * iAccountService.addNewUser(etudiant.getNom(),"1234");
                         * iAccountService.addRoleToUser(etudiant.getNom(),"USER");
                         * if(Math.random()>0.6)
                         * iAccountService.addRoleToUser(etudiant.getNom(),"ADMIN");
                         */
                        // BD-v1
                        jdbcUserDetailsManager.createUser(User.withUsername(compte.getPseudo())
                                .password(passwordEncoder.encode("1234")).roles("REDACT").build());

                    });

            // Article
            Compte compte1 = iCompteRepository.findById(1L).orElse(null);
            Stream.of("La fac", "A développer", "Le social", "La com")
                    .forEach(titre -> {
                        Article article = new Article();
                        article.setTitre(titre);
                        article.setContenu("Contenu de " + "\'" + titre + "\'");
                        article.setDate(new Date());
                        article.setModere(Math.random() > 0.5 ? false : true);
                        article.set_public(Math.random() > 0.1 ? true : false);
                        article.setCompte(compte1);

                        iArticleRepository.save(article);

                    });
            // Commentaire
            Article article = iArticleRepository.findById(Math.random() > 0.8 ? 1L : 2L).orElse(null);
            Compte compte2 = iCompteRepository.findById(21L).orElse(null);
            Stream.of("Bien", "Très bien", "Passionnément", "Pas du tout")
                    .forEach(contenu -> {
                        Commentaire commentaire = new Commentaire();
                        commentaire.setContenu(contenu);
                        commentaire.setDate(new Date());
                        commentaire.set_public(Math.random() > 0.8 ? false : true);
                        commentaire.setModere(Math.random() > 0.8 ? false : true);
                        commentaire.setArticle(article);
                        commentaire.setCompte(compte2);

                        iCommentaireRepository.save(commentaire);

                    });
        };
    }

    // Utilisateurs BD-v1
    // @Bean
    CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager) {
        // PasswordEncoder passwordEncoder= passwordEncoder();
        return args -> {
            /*
             * UserDetails user=null; //pour vérifier
             * user= jdbcUserDetailsManager.loadUserByUsername("user1"); //vérifier si
             * 'user11' existe
             * if(user == null) //le créer s'il n'existe pas
             * jdbcUserDetailsManager.createUser(
             * User.withUsername("user1").password(passwordEncoder.encode("1234")).roles(
             * "USER").build()
             * );
             * user= jdbcUserDetailsManager.loadUserByUsername("user2"); //le créer s'il
             * n'existe pas
             * if(user == null)
             * jdbcUserDetailsManager.createUser(
             * User.withUsername("user2").password(passwordEncoder.encode("1234")).roles(
             * "USER").build()
             * );
             * 
             * user= jdbcUserDetailsManager.loadUserByUsername("admin"); //le créer s'il
             * n'existe pas
             * if(user == null)
             * jdbcUserDetailsManager.createUser(
             * User.withUsername("admin").password(passwordEncoder.encode("1234")).roles(
             * "USER","ADMIN").build()
             * );
             */
            jdbcUserDetailsManager.createUser(
                    User.withUsername("redact").password(passwordEncoder.encode("1234")).roles("REDACT").build());
            jdbcUserDetailsManager.createUser(
                    User.withUsername("moder").password(passwordEncoder.encode("1234")).roles("REDACT", "MODER")
                            .build());
            jdbcUserDetailsManager.createUser(
                    User.withUsername("admin").password(passwordEncoder.encode("1234"))
                            .roles("REDACT", "MODER", "ADMIN").build());
            // Modifier un user ?
            // AppUser appUser = new
            // AppUser("user1",jdbcUserDetailsManager.loadUserByUsername("user1").getPassword(),jdbcUserDetailsManager.loadUserByUsername("user1").getAuthorities().add("ROLE_ADMIN"));
            /*
             * jdbcUserDetailsManager.createUser(
             * User.withUsername("user1").password(passwordEncoder.encode("1234")).roles(
             * "USER","ADMIN").build()
             * );
             * UserDetails user= jdbcUserDetailsManager.loadUserByUsername("user1");
             * //chercher
             * jdbcUserDetailsManager.updateUser(user); //???
             */

        };
    }
}
