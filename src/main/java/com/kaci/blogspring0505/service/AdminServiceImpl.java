package com.kaci.blogspring0505.service;

import com.kaci.blogspring0505.entities.Article;
import com.kaci.blogspring0505.entities.Compte;
import com.kaci.blogspring0505.entities.TypeCompte;
import com.kaci.blogspring0505.repository.ICompteRepository;
import com.kaci.blogspring0505.repository.ITypeCompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements IAdminService{
   /* public AdminServiceImpl(IArticleRepository iArticleRepository, ICommentaireRepository iCommentaireRepository, ICompteRepository iCompteRepository) {
        super(iArticleRepository, iCommentaireRepository, iCompteRepository);
    }*/
    @Autowired
    private ICompteRepository iCompteRepository;
    @Autowired
    private ITypeCompteRepository iTypeCompteRepository;
    /**
     * Compte
     *******************/
    //READ
    @Override
    public List<Compte> listeComptesNonValides() {return iCompteRepository.listeComptesNonValides();
    }

 // fonctions de recherche --------
    @Override
    public Compte chercheCompte(Long id) {
      Optional<Compte> compte = Optional.ofNullable(iCompteRepository.chercheCompteId(id));
      if (compte.isPresent()) {
        return compte.get();
      }
      throw new RuntimeException("Compte introuvable");
 }
    // UPDATE
    @Override
    public Compte modifieCompte(Compte compte, Long id) {
        System.out.println("IdCompte : "+id);
        Compte compteSilExiste = iCompteRepository.chercheCompteId(id);
        compteSilExiste.setPseudo(compte.getPseudo());
        //compteSilExiste.setValide(true);
        return iCompteRepository.save(compteSilExiste);
        //return  null;
    }
    //MODIFIER
    //Valider un compte
    @Override
    public void validerCompte(Long idCompte) {
        //iCompteRepository.validerCompte(idCompte);
        Compte compte=iCompteRepository.chercheCompteId(idCompte);
        try {
            System.out.println("OKK: ");
            compte.setValide(true);
            iCompteRepository.save(compte);
        }catch(Exception e) {System.out.println("ERREUR: "+ e);}

    }

    /**
  * TypeCompte
  *******************/
    //READ
    //Recherche un  type de compte par ID
    @Override
    public TypeCompte chercheTypeCompteId(Long idTypeCompte) {
        return iTypeCompteRepository.chercheTypeCompteId(idTypeCompte);
    }

    //Recherche un  type de compte par Label
    @Override
    public TypeCompte chercheTypeCompteLabel(String label) {
        return iTypeCompteRepository.chercheTypeComptelabel(label);
    }

}
