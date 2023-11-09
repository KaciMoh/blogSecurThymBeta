package com.kaci.blogspring0505.service;

import com.kaci.blogspring0505.entities.Article;
import com.kaci.blogspring0505.entities.Commentaire;
import com.kaci.blogspring0505.entities.Compte;
import com.kaci.blogspring0505.entities.TypeCompte;

import java.util.List;

public interface IInternauteService {
    /* Article */
    // READ
    // Accueil site : Liste Articles publiques
    List<Article> listeArticlesPublic();

    /* Commentaires */
    // READ
    // Liste Commentaires publiques d'un article
    // List<Commentaire> listeCommentairesPublic(Article article);
    List<Commentaire> commentairesPublicsArticle(Long idArticle);

    /* Compte */
    // CREATE
    Compte creeCompte(Compte compte);

}
