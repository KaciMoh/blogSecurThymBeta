package com.kaci.blogspring0505.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NonAutorise {
    // Message pour les routes non autorisées
    @GetMapping("/nonautorise")
    @PreAuthorize("permitAll()")
    public String home() {
        return "nonautorise";
    }

}
