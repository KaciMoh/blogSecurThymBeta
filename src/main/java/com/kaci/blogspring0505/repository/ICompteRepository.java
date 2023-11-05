package com.kaci.blogspring0505.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kaci.blogspring0505.entities.Compte;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICompteRepository extends JpaRepository<Compte, Long> {

    //READ
    //Cherche un compte avec IdCompte
        //Compte findCompteByIdCompte(Long idCompte);
    @Query("SELECT a FROM Compte a WHERE a.idCompte= :x")
    Compte chercheCompteId(@Param("x") Long idCompte);

    //Recherche un compte avec le PSEUDO
        //Compte findCompteByPseudo(String pseudo);
    @Query("SELECT a FROM Compte a WHERE a.pseudo= :x")
    Compte chercheComptePseudo(@Param("x") String pseudo);

    //Cherche le compte  d'un article avec idArticle
    @Query("SELECT a FROM Compte a INNER JOIN Article b ON a.idCompte=b.compte.idCompte WHERE b.idArticle= :x")
    Compte chercheCompteArticle(@Param("x") Long idArticle);

    //Cherche le compte d'un commentaire avec idCommentaire
    @Query("SELECT a FROM Compte a INNER JOIN Commentaire b ON a.idCompte=b.compte.idCompte WHERE b.idCommentaire= :x")
    Compte chercheCompteCommentaire(@Param("x") Long idCommentaire);

    @Query("SELECT a FROM Compte a WHERE a.valide=false")
    List<Compte> listeComptesNonValides();

}


