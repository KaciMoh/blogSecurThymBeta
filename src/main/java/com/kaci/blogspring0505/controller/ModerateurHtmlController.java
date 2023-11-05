package com.kaci.blogspring0505.controller;

import com.kaci.blogspring0505.entities.Article;
import com.kaci.blogspring0505.entities.Commentaire;
import com.kaci.blogspring0505.service.IModerateurService;
import com.kaci.blogspring0505.service.IRedacteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ModerateurHtmlController {
    //@Qualifier("moderateurServiceImpl")
    @Autowired
    private IModerateurService iModerateurService;
    @Autowired
    private IRedacteurService iRedacteurService;

    /*** Articles *************/
    //READ

    // Liste des articles en attente de modération
    @GetMapping("/moder/listearticlesnonmodere")
    public String listeArticlesNonModere (Model model){
        List<Article> articles = iModerateurService.listeArticlesNonModere();
        model.addAttribute("listeArticles",articles);
        String titre = "Articles non encore modérés";
        model.addAttribute("titre", titre);
        return "articles/listeArticles";
    }

    /*** Commentaire *************/
    //READ
    // Liste des Commentaires en attente de modération
    @GetMapping("/moder/listecommentairesnonmodere")
    public String listeCommentairesNonModere (Model model) {
        List<Commentaire> commentaires = iModerateurService.listeCommentairesNonModere();
        model.addAttribute("listeCommentaires",commentaires);
        String titre = "Commentaires non encore modérés";
        model.addAttribute("titre", titre);
        return "commentaires/listeCommentaires";
    }
}
