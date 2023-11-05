package com.kaci.blogspring0505.service;

import com.kaci.blogspring0505.entities.Article;
import com.kaci.blogspring0505.entities.Commentaire;
import com.kaci.blogspring0505.repository.IArticleRepository;
import com.kaci.blogspring0505.repository.ICommentaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModerateurServiceImpl implements IModerateurService {
    @Autowired
    private IArticleRepository iArticleRepository;
    @Autowired
    private ICommentaireRepository iCommentaireRepository;

    @Override
    public List<Article> listeArticlesNonModere() {
        return iArticleRepository.articlesNonModeres();
    }

    /*** Commentaires *************/
    // READ
    // Liste des Commentaires en attente de modération
    @Override
    public List<Commentaire> listeCommentairesNonModere() {
        return iCommentaireRepository.commentairesNonmoderes();
    }
}
