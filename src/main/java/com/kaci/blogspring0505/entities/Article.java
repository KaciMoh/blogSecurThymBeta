package com.kaci.blogspring0505.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;
import lombok.Builder;
import org.hibernate.validator.internal.util.privilegedactions.GetDeclaredField;

import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.AssertTrue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Article implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArticle;
    private boolean _public;
    private boolean modere;
    private String titre;
    private String contenu;
    @Temporal(TemporalType.DATE)
    @Column(name = "date-creation")
    private Date date;
    @Temporal(TemporalType.DATE)
    @Column(name = "date-modif")
    private Date dateModif;

    @ManyToOne
    // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //optionnel: évite
    // d'afficher systématiquement le compte
    private Compte compte;

}
