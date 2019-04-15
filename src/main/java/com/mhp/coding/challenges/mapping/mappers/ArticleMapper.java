package com.mhp.coding.challenges.mapping.mappers;

import com.mhp.coding.challenges.mapping.models.db.Article;
import com.mhp.coding.challenges.mapping.models.db.blocks.ArticleBlock;
import com.mhp.coding.challenges.mapping.models.dto.ArticleDto;
import com.mhp.coding.challenges.mapping.models.dto.blocks.ArticleBlockDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.*;

@Component
public class ArticleMapper {


    public ArticleDto map(Article article) {
        ModelMapper modelMapper = new ModelMapper();
        ArticleDto myArticleDto = modelMapper.map(article, ArticleDto.class);

        Set<ArticleBlockDto> articleDtoSet = new HashSet<ArticleBlockDto>();
        for (ArticleBlock block : article.getBlocks()) {
            ArticleBlockDto abd = new ArticleBlockDto();

            abd.setSortIndex(block.getSortIndex());

            articleDtoSet.add(abd);
        }
        sortBlock(articleDtoSet);
        myArticleDto.setBlocks(articleDtoSet);

        return myArticleDto;
    }

    //fuer die List<Article>
    public List<ArticleDto> map(List<Article> articles) {
        ModelMapper modelMapper = new ModelMapper();
        List<ArticleDto> listOfArticlesDto = new ArrayList<ArticleDto>();

        for (Article article : articles) {
            ArticleDto myArticleDto = modelMapper.map(article, ArticleDto.class);

            //sortiere Block
            Set<ArticleBlockDto> articleDtoSet = new HashSet<ArticleBlockDto>();
            for (ArticleBlockDto block : myArticleDto.getBlocks()) {
                ArticleBlockDto abd = new ArticleBlockDto();

                abd.setSortIndex(block.getSortIndex());

                articleDtoSet.add(abd);
            }
            sortBlock(articleDtoSet);
            myArticleDto.setBlocks(articleDtoSet);

            //füge es zur Gesamt List
            listOfArticlesDto.add(myArticleDto);
        }


        return listOfArticlesDto;
    }

    public ArticleDto mapFixedSet(Article article) {
        ArticleDto myArticleDto = new ArticleDto();
        myArticleDto.setAuthor(article.getAuthor());
        myArticleDto.setDescription(article.getDescription());
        myArticleDto.setId(article.getId());
        myArticleDto.setTitle(article.getTitle());


        Set<ArticleBlockDto> articleDtoSet = new HashSet<ArticleBlockDto>();
        for (ArticleBlock block : article.getBlocks()) {
            ArticleBlockDto abd = new ArticleBlockDto();

            abd.setSortIndex(block.getSortIndex());

            articleDtoSet.add(abd);
        }

        sortBlock(articleDtoSet);
        myArticleDto.setBlocks(articleDtoSet);


        return myArticleDto;
    }

    public Article map(ArticleDto articleDto) {
        // Nicht Teil dieser Challenge.
        return new Article();
    }

    private void sortBlock(Set<ArticleBlockDto> blocks) {
        List<ArticleBlockDto> listBlocks = new ArrayList<>(blocks);

        printSortArray(listBlocks);


        for (int i = listBlocks.size() - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (listBlocks.get(j).getSortIndex() > listBlocks.get(j + 1).getSortIndex()) {
                    switchNumbers(listBlocks, j, j + 1);
                }
            }
        }
    }

    private void sortBlock(List<ArticleBlockDto> listBlocks) {

        printSortArray(listBlocks);


        for (int i = listBlocks.size() - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (listBlocks.get(j).getSortIndex() > listBlocks.get(j + 1).getSortIndex()) {
                    switchNumbers(listBlocks, j, j + 1);
                }
            }
        }
    }


    private void printSortArray(List<ArticleBlockDto> meineListe) {
        for (ArticleBlockDto element : meineListe) {
            System.out.print(element.getSortIndex() + " ");
        }
    }

    private void switchNumbers(List<ArticleBlockDto> numbers, int numberOneIndex, int numberTwoIndex) {
        int temp;

        temp = numbers.get(numberOneIndex).getSortIndex();
        numbers.get(numberOneIndex).setSortIndex(numbers.get(numberTwoIndex).getSortIndex());
        numbers.get(numberTwoIndex).setSortIndex(temp);

    }
}
