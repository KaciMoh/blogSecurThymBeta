package com.kaci.blogspring0505.service;

import java.util.List;

import com.kaci.blogspring0505.entities.Article;
import com.kaci.blogspring0505.entities.Commentaire;
import com.kaci.blogspring0505.entities.Compte;

// création méthode rédacteur
public interface IRedacteurService {
    // CREATE
    Article creeArticle(Article article);

    // READ
    List<Article> listeArticle();

    Article afficheArticle(Long idArticle);

    // UPDATE
    Article modifieArticle(Article article, Long idArticle);

    // DELETE
    String supprimeArticle(Article article, Long idArticle);

    /* Commentaire */
    // CREATE
    // Crée un commentaire d'un article
    Commentaire creeCommentaire(Commentaire commentaire);

    // READ
    // Affiche les commentaires d'un article
    // List<Commentaire> listeCommentairesArticle(Article article);
    List<Commentaire> commentairesArticle(Long idArticle);

    // Affiche UN commentaire
    Commentaire afficheCommentaire(Long idCommentaire);

    // DELETE
    // Supprime un commentaire
    String supprimeCommentaire(Long idCommentaire);

    // fonctions de recherche --------
    // Article
    public Article chercheArticle(Long id);

    /* Compte */

    // READ
    // Cherche le compte d'un article
    public Compte chercheCompteArticle(Article article);

    // cherche le pseudo du compte d'un article
    public Compte chercheCompteArticle(Long idArticle);

    // Cherche le compte d'un commentaire
    // public Compte chercheCompteCommentaire(Commentaire commentaire);
    public Compte chercheCompteCommentaire(Long idCommentaire);

    // Recherche un compte avec le PSEUDO
    Compte chercheComptePseudo(String pseudo);
}
