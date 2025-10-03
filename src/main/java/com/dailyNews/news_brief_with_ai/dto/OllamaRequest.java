package com.dailyNews.news_brief_with_ai.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class OllamaRequest {
    private String prompt;
    private String model;
    private boolean stream;

}
