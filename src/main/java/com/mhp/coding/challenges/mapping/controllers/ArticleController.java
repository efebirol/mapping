package com.mhp.coding.challenges.mapping.controllers;

import com.mhp.coding.challenges.mapping.mappers.ArticleMapper;
import com.mhp.coding.challenges.mapping.models.dto.ArticleDto;
import com.mhp.coding.challenges.mapping.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    ArticleMapper mapper;

    @Autowired
    public ArticleController(ArticleService articleService) {

        System.out.println("-- Articlecontroller.java - ArticleController() Konstruktor");
        this.articleService = articleService;
    }

    @GetMapping()
    public List<ArticleDto> list() {
        System.out.println("-- Articlecontroller.java - list() Methode");
        return articleService.list();
    }

    @GetMapping("/{id}")
    public ArticleDto details(@PathVariable Long id) {

        System.out.println("-- Articlecontroller.java - details() Methode");
        ArticleDto articleDto = articleService.articleForId(id);

        if (articleDto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Article not found");
        }
        return articleDto;
    }

    @PostMapping()
    public ArticleDto create(@RequestBody ArticleDto articleDto) {
        return articleService.create(articleDto);
    }
}
