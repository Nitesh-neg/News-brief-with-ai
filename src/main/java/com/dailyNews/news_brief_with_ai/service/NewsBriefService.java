package com.dailyNews.news_brief_with_ai.service;

import com.dailyNews.news_brief_with_ai.client.NewsApiClient;
import com.dailyNews.news_brief_with_ai.client.OllamaClient;
import com.dailyNews.news_brief_with_ai.dto.NewsApiResponse;
import com.dailyNews.news_brief_with_ai.dto.NewsSummaryResponse;
import com.dailyNews.news_brief_with_ai.dto.OllamaResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //@Service tells Spring "This is a business service class - manage it and make it available for injection everywhere."
@Slf4j
public class NewsBriefService {

    private final OllamaClient ollamaClient;
    private final NewsApiClient newsApiClient;

    @Autowired
    public NewsBriefService(NewsApiClient newsApiClient, OllamaClient ollamaClient) {
        this.ollamaClient=ollamaClient;
        this.newsApiClient=newsApiClient;
    }


    public NewsSummaryResponse generateGeneralNewsBrief(boolean isRender){

        final NewsApiResponse newsApiResponse = newsApiClient.getTopHeadlines();
        log.info("Requesting summary for {} articles",newsApiResponse.getArticles().size());

        final OllamaResponse ollamaResponse = ollamaClient.generateSummary(newsApiResponse.getArticles(),isRender);

        return NewsSummaryResponse.builder()
                .createdAt(java.time.LocalDateTime.now())
                .summary(ollamaResponse.getResponse())
                .build();


    }

}
