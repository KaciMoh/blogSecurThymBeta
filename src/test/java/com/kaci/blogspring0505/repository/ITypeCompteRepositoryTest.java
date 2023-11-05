package com.kaci.blogspring0505.repository;

import com.kaci.blogspring0505.entities.TypeCompte;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@AllArgsConstructor
class ITypeCompteRepositoryTest {
    private ITypeCompteRepository underTest;

    @Test
    void chercheTypeCompteId() {
        //given
        //Long id= 2L;
        String label= "Admin";
        TypeCompte typeCompte = new TypeCompte(
                label
        );
        underTest.save(typeCompte);

        //when
        //TypeCompte expected = underTest.chercheTypeCompteId(2L);
        String expected = underTest.chercheTypeComptelabel(label).getLabel();
        //then
        //assertThat(expected).isExactlyInstanceOf(TypeCompte.class); // methode assertj
        assertThat(expected).isEqualTo(label); // methode assertj
    }
}