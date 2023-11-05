package com.kaci.blogspring0505.repository;

import com.kaci.blogspring0505.entities.Compte;
import com.kaci.blogspring0505.entities.TypeCompte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITypeCompteRepository extends JpaRepository<TypeCompte, Long> {

    //READ
    //Recherche un  type de compte par ID
        //TypeCompte findTypeCompteByIdTypeCompte(Long idTypeCompte);
    @Query("SELECT a FROM TypeCompte a WHERE a.idTypeCompte= :x")
    TypeCompte chercheTypeCompteId(@Param("x") Long idTypeCompte);

    //Recherche un type de compte par label
    TypeCompte findTypeCompteByLabel(String label);
    @Query("SELECT a FROM TypeCompte a WHERE a.label= :x")
    TypeCompte chercheTypeComptelabel(@Param("x") String label);

}


