package com.dailyNews.news_brief_with_ai.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewsApiResponse {

    private int totalResults;
    private List<Article> articles;


}
