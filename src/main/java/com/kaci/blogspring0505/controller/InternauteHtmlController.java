package com.kaci.blogspring0505.controller;

import com.kaci.blogspring0505.entities.Article;
import com.kaci.blogspring0505.entities.Commentaire;
import com.kaci.blogspring0505.entities.Compte;
import com.kaci.blogspring0505.entities.TypeCompte;
import com.kaci.blogspring0505.service.IAdminService;
import com.kaci.blogspring0505.service.IInternauteService;
import com.kaci.blogspring0505.service.IRedacteurService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class InternauteHtmlController {
    @Autowired
    private IAdminService iAdminService;
    @Autowired
    private IInternauteService iInternauteService;
    @Autowired
    private IRedacteurService iRedacteurService;
    @Autowired
    private JdbcUserDetailsManager jdbcUserDetailsManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /* Article */
    //READ
    //Accueil site : liste des articles publics
    @GetMapping("/public/index") //
    @PreAuthorize("permitAll()")
    public String listeArticlesPublic(Model model){//
        List<Article> articles = iInternauteService.listeArticlesPublic();
        model.addAttribute("listeArticles",articles);
        String titrePage = "Nos publications publiques";
        model.addAttribute("titrePage", titrePage);
        return "articles/listeArticles";
    }

    //Affiche article avec ses commentaires
    @GetMapping("/public/listecommentairesarticle") // affiche un article avec compte et commentaires
    public String afficheArticleCommentaires(@RequestParam(name = "idArticle") Long id, Model model) {
        Article article = iRedacteurService.chercheArticle(id);
        Compte compte = iRedacteurService.chercheCompteArticle(article);
        //List<Commentaire> commentaires = iInternauteService.listeCommentairesPublic(article);
        List<Commentaire> commentaires = iInternauteService.commentairesPublicsArticle(id);
        model.addAttribute("article", article); // attributeName= nom attribut le doc HTML
        model.addAttribute("compte",compte);
        model.addAttribute("listecommentaires", commentaires);
        return "articles/listeArticleCommentaires";
    }

    /** Commentaire  ******************/
    //READ
    //Affiche un commentaire
    @GetMapping("/public/affichecommentaire") // avec son id
    public String afficheCommentaire(@RequestParam(name = "idCommentaire") Long id, Model model) {
        Commentaire commentaire = iRedacteurService.afficheCommentaire(id);
        model.addAttribute("commentaire", commentaire);
        Compte compte = iRedacteurService.chercheCompteCommentaire(id);
        model.addAttribute("compte",compte);
        // model.addAttribute("listecommentaires", commentaires);
        return "commentaires/afficheCommentaire";
    }

    /** Compte **********/
    //CREATE
    @GetMapping("/public/formcompte") // construit un compte
    public String creeCompte(Compte compte, Model model){//
            model.addAttribute("compte", compte);
            return "comptes/formCompte";
    }
    @PostMapping("/public/savecompte") // sauvegarde dans la BD
    public String creeCompte(@Valid Compte compte, BindingResult bindingResult, Errors errors, RedirectAttributes ra){ //
        //if (bindingResult.hasErrors()){
        if(errors != null && errors.getErrorCount()>0){
            System.out.println("*****************************************************************************************");
            System.out.println("Erreurs nb: "+errors.getErrorCount());
            System.out.println("Erreurs: "+errors.getAllErrors());
            return "comptes/formCompte";
        }else {
        compte.setBani(false);
            compte.setEfface(false);
            compte.setSignale(false);
            compte.setEfface(false);
            compte.setSupressionDonnee(false);
            compte.setValide(false);

            //Cryptage du mot de passe ***
            compte.setMotDePasse(passwordEncoder.encode(compte.getMotDePasse()));

            // Type Compte = "redacteur"
            TypeCompte typeCompte = iAdminService.chercheTypeCompteId(1L);
            compte.setTypeCompte(typeCompte);

            //Sauvegarde compte
            iInternauteService.creeCompte(compte);
            // Création de l'utilisateur correspondant à ce compte

            //Création dans la table 'users'
            //jdbcUserDetailsManager.createUser(User.withUsername(compte.getPseudo()).password(passwordEncoder.encode("1234")).roles("REDACT").build());
            jdbcUserDetailsManager.createUser(User.withUsername(compte.getPseudo()).password(compte.getMotDePasse()).roles("REDACT").build());

            ra.addFlashAttribute("Votre compte est créé");
            return "redirect:/public/index"; //
        }
    }

}
