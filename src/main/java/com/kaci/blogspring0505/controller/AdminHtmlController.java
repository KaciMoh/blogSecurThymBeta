package com.kaci.blogspring0505.controller;

import com.kaci.blogspring0505.entities.Article;
import com.kaci.blogspring0505.entities.Compte;
import com.kaci.blogspring0505.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class AdminHtmlController {
    @Autowired
    private IAdminService iAdminService;

    /*** Compte ****************/
    //READ
    //Accueil modérateur
    @GetMapping("/admin/index")
    public String indexAdmin(){
        return "indexAdmin";
    }

    // Liste des comptes non validés
    @GetMapping("/admin/listecomptesnonvalide") // index des articles
    public String listeCompteNonValide(Model model) {
        List<Compte> comptes = iAdminService.listeComptesNonValides();
        model.addAttribute("liste", comptes);
        return "comptes/listeComptesNonValide";
    }

    // UPDATE
    // Valider un compte ************************************************
    @PutMapping("/admin/validecompte")
    public String valideCompte(@RequestParam("idCompte") Long idCompte){
        iAdminService.validerCompte(idCompte);
        //Compte compte = iAdminService.chercheCompte(idCompte);
        //compte.setValide(true);
        return "redirect:/redact/index";
    }

    // 1. modifier un compte
    @GetMapping("/admin/editCompte") // édite le nouveau contenu d'un compte
    public String editCompte(Model model, @RequestParam("idCompte") Long idCompte) {
        try {
            Compte compte = iAdminService.chercheCompte(idCompte);
            model.addAttribute("compte", compte);
            return "comptes/editCompte"; // formulaire de modification du compte
            //System.out.println("OK_OK");
            //return "OK_OK"; // formulaire de modification du compte
        } catch (RuntimeException e) { // cas erreur
           return "redirect:/admin/listecomptesnonvalide"; // retour à l'accueil
            //System.out.println("ERREUR");

        }
    }
    // 2. Enregistrer les modifications
    @PostMapping("/admin/saveModifCompte") // sauvegarde le compte avec les modifs
    public String saveModifCompte(Compte compte, Long id) { //
        //iRedacteurService.creeArticle(article);
        compte.setValide(true); //compte validé
        iAdminService.modifieCompte(compte,id);
        return "redirect:/admin/listecomptesnonvalide";
    }



}
