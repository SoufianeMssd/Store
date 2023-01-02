package com.book.store.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Greeting {
    public Greeting() {
    }

    @GetMapping({"/helloo"})
    public String greeting() {
        return "Hello Everyone";
    }
}