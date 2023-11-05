package com.kaci.blogspring0505.entities;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor

public class TypeCompte implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTypeCompte;
    @Column(unique = true, nullable = false)
    private String label;

    public TypeCompte(String label){
       this.label=label;
    }
}
