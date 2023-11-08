package com.kaci.blogspring0505.controller;

import com.kaci.blogspring0505.entities.Article;
import com.kaci.blogspring0505.entities.Compte;
import com.kaci.blogspring0505.service.IAdminService;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor
public class AdminHtmlController {
    // @Autowired
    private IAdminService iAdminService;

    /*** Compte ****************/
    // READ
    // Accueil modérateur
    @GetMapping("/admin/listecomptes")
    public String listeComptes(Model model) {
        List<Compte> comptes = iAdminService.listeComptes();
        model.addAttribute("liste", comptes);
        return "comptes/listeComptes";
    }

    // Liste des comptes non validés
    @GetMapping("/admin/listecomptesnonvalide") //
    public String listeCompteNonValide(Model model) {
        List<Compte> comptes = iAdminService.listeComptesNonValides();
        model.addAttribute("liste", comptes);
        return "comptes/listeComptesNonValide";
    }

    //
    // Valider un compte
    @GetMapping("/admin/validecompte")
    public String valideCompte(@RequestParam("idCompte") Long idCompte) {
        iAdminService.validerCompte(idCompte);
        return "comptes/messages/compteValide";
    }
}
