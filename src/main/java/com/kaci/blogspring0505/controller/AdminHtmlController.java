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

    //
    // Valider un compte
    @GetMapping("/admin/validecompte")
    public String valideCompte(@RequestParam("idCompte") Long idCompte) {
        iAdminService.validerCompte(idCompte);
        return "redirect:/admin/listecomptesnonvalide";
    }

    // 2. Enregistrer les modifications
    @PostMapping("/admin/saveModifCompte") // sauvegarde le compte avec les modifs
    public String saveModifCompte(Compte compte, Long id) { //
        // iRedacteurService.creeArticle(article);
        compte.setValide(true); // compte validé
        iAdminService.modifieCompte(compte, id);
        return "redirect:/admin/listecomptesnonvalide";
    }

}
