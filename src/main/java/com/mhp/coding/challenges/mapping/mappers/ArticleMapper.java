package com.mhp.coding.challenges.mapping.mappers;

import com.mhp.coding.challenges.mapping.models.db.Article;
import com.mhp.coding.challenges.mapping.models.db.blocks.ArticleBlock;
import com.mhp.coding.challenges.mapping.models.dto.ArticleDto;
import com.mhp.coding.challenges.mapping.models.dto.blocks.ArticleBlockDto;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.*;

@Component
public class ArticleMapper {



    public ArticleDto map(Article article){
        //TODO
        ArticleDto myArticleDto = new ArticleDto();
        myArticleDto.setAuthor(article.getAuthor());
        myArticleDto.setDescription(article.getDescription());
        myArticleDto.setId(article.getId());
        myArticleDto.setTitle(article.getTitle());

        Set<ArticleBlockDto> articleDtoSet = new HashSet<ArticleBlockDto>();
        myArticleDto.setBlocks(articleDtoSet);
        for (ArticleBlock block: article.getBlocks()) {
            ArticleBlockDto abd= new ArticleBlockDto();

            abd.setSortIndex(block.getSortIndex());


            articleDtoSet.add(abd);
        }



        return myArticleDto;
    }

    public Article map(ArticleDto articleDto) {
        // Nicht Teil dieser Challenge.
        return new Article();
    }

    //ToDo: Schreibe ein Sort für das Object
    public ArticleBlockDto sortBlock(ArticleBlockDto block){
        ArticleBlockDto tempblock = new ArticleBlockDto();

        for (ArticleBlockDto blockitem: block.get) {
            block
        }

        return tempblock;
    }
}
