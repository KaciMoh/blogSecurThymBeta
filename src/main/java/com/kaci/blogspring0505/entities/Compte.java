package com.kaci.blogspring0505.entities;

import java.io.Serializable;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Compte implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idCompte;

        @Column(unique = true)
        @Pattern(regexp = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Format email: " +
                        "___@___.__ l''@ et le . sont obligatoires.")
        private String email;

        @Column(unique = true)
        @Size(min = 3, max = 25, message = "Le pseudo doit comporter entre 3 et 25 caractères")
        private String pseudo;

        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,}$", message = "Le mot de passe " +
                        "doit comporter 8 caractères minimum, dont au moins : " +
                        "une minuscule, une majuscule, un nombre et un des caractères ! @ # $ %")
        private String motDePasse;

        private boolean efface;
        private boolean bani;
        private boolean valide;
        private boolean supressionDonnee;
        private boolean signale;
        @ManyToOne
        private TypeCompte typeCompte;

}
