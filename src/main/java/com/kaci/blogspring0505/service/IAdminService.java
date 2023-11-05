package com.kaci.blogspring0505.service;

import com.kaci.blogspring0505.entities.Article;
import com.kaci.blogspring0505.entities.Compte;
import com.kaci.blogspring0505.entities.TypeCompte;

import java.util.List;

public interface IAdminService {
    /* Compte */
    //READ
    // Liste des comptes valides
    public List<Compte> listeComptesNonValides();

    // fonctions de recherche --------
    // Article
    public Compte chercheCompte(Long id);

    // UPDATE
    Compte modifieCompte(Compte compte, Long id);
    //Valider un compte


    public void validerCompte(Long idCompte);
    /* TypeCompte */
    //READ
    //Recherche un  type de compte par ID
    public TypeCompte chercheTypeCompteId(Long idTypeCompte);

    //Recherche un type de compte par Label
    public TypeCompte chercheTypeCompteLabel(String label);

}
