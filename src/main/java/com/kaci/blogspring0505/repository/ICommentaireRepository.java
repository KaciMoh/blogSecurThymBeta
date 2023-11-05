package com.kaci.blogspring0505.repository;

import java.util.List;

import com.kaci.blogspring0505.entities.Compte;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.kaci.blogspring0505.entities.Article;
import com.kaci.blogspring0505.entities.Commentaire;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentaireRepository extends JpaRepository<Commentaire, Long> {
    // READ
    // liste des commentaires d'un article
    // List<Commentaire> findCommentairesByArticle(Article article); //OK
    @Query("SELECT a FROM Commentaire a INNER JOIN Article b ON a.article.idArticle=b.idArticle WHERE b.idArticle= :x")
    List<Commentaire> commentairesArticle(@Param("x") Long idArticle);

    // Affiche UN commentaire
    // Commentaire findCommentaireByIdCommentaire(Long idCommentaire);
    @Query("SELECT a FROM Commentaire a WHERE a.idCommentaire= :x")
    Commentaire chercheCommentaireId(@Param("x") Long idCommentaire);

    // Liste Commentaires publiques d'un article
    // List<Commentaire> findCommentairesBy_publicIsTrueAndArticle(Article article);
    @Query("SELECT a FROM Commentaire a INNER JOIN Article b ON a.article.idArticle=b.idArticle WHERE a._public=true  AND b.idArticle= :x")
    List<Commentaire> commentairesPublicsArticle(@Param("x") Long idArticle);

    // liste des commentaires non encore modérés
    // List<Commentaire> findCommentairesByModereIsFalse();
    @Query("SELECT a FROM Commentaire a WHERE a.modere=false")
    List<Commentaire> commentairesNonmoderes();

    // DELETE
    // 1- supprimer un commentaire avec ID
    // Méthode 2 : @Query avec les 2 annotations @Transactional et @Modifying
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Commentaire a WHERE a.idCommentaire= :x")
    void supprimeCommentaireId(@Param("x") Long idCommentaire);

    // 2- supprimer tous les commentaires d'un article (nécessaire pour supprimer un
    // article)
    // Méthode 1 : JPA avec l'annotation @Transactional
    /*
     * @Transactional
     * List<Commentaire> deleteByArticle(Article article);
     */
    // Méthode 2 : @Query avec les 2 annotations @Transactional et @Modifying
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Commentaire a WHERE a.article= :x")
    void supprimeCommentairesArticle(@Param("x") Article article);

}
