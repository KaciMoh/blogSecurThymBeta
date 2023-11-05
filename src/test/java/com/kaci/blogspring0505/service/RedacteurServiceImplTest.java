package com.kaci.blogspring0505.service;

import com.kaci.blogspring0505.repository.IArticleRepository;
import com.kaci.blogspring0505.repository.ICommentaireRepository;
import com.kaci.blogspring0505.repository.ICompteRepository;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
@AllArgsConstructor
class RedacteurServiceImplTest {
    @Mock
    private IArticleRepository iArticleRepository;
    private  AutoCloseable autoCloseable;
    private ICommentaireRepository iCommentaireRepository;
    private ICompteRepository iCompteRepository;
    private  IRedacteurService underTest;

    {
        new RedacteurServiceImpl(iArticleRepository, iCommentaireRepository, iCompteRepository);
    }

    @Test
    void listeArticle() {
        //when
        underTest.listeArticle();
        //then
        verify(iArticleRepository).findAll();
    }
}