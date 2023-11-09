package com.kaci.blogspring0505.service;

import com.kaci.blogspring0505.entities.Article;
import com.kaci.blogspring0505.entities.Commentaire;

import java.util.List;

public interface IModerateurService {
    /*** Articles *************/
    // READ
    // Liste des articles en attente de modération
    List<Article> listeArticlesNonModere();

    // Modérer un article
    public void modereArticle(Long idArticle);

    /*** Commentaires *************/

    // READ
    // Liste des Commentaires en attente de modération
    List<Commentaire> listeCommentairesNonModere();
}
