package com.dailyNews.news_brief_with_ai.client;

import com.dailyNews.news_brief_with_ai.dto.Article;
import com.dailyNews.news_brief_with_ai.dto.OllamaRequest;
import com.dailyNews.news_brief_with_ai.dto.OllamaResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class OllamaClient {

      @Value("${ollama.base.url}")
      private String ollamUrl;

      @Value("${ollama.mistral.model}")
      private String aiModel;

      public OllamaResponse generateSummary(final List<Article> articles,final boolean isRender){

          final RestTemplate restTemplate = new RestTemplate();;

          final String prompt = getPrompt(articles, isRender );

          final OllamaRequest requestPayload = OllamaRequest.builder()
                  .model(aiModel)
                  .prompt(prompt)
                  .stream(false) // for getting the complete response at once
                  .build();

          final HttpEntity<OllamaRequest> entity = getHttpEntity(requestPayload);

          final ResponseEntity<OllamaResponse> responseEntity = restTemplate.
                  postForEntity(ollamUrl,entity,OllamaResponse.class);

          log.info("Response from Ollama: {}",responseEntity.getBody());
            return responseEntity.getBody();
      }


      // Creating a http request for RestTemplate
      private static HttpEntity<OllamaRequest> getHttpEntity(OllamaRequest requestPayload) {
          HttpHeaders headers = new HttpHeaders();
          headers.setContentType(MediaType.APPLICATION_JSON);
          HttpEntity<OllamaRequest> entity = new HttpEntity<>(requestPayload, headers);
          return entity;
      }



    private String getPrompt(final List<Article> articles, final boolean isRender) {

          final StringBuilder promptBuilder = new StringBuilder();
        if(isRender){
            promptBuilder.append("IMPORTANT: Return ONLY pure HTML, no additional text, no code blocks, no explanations.\n\n");
            promptBuilder.append("<div class='news-summary'>\n");
            promptBuilder.append("  <h2>Article Title 1</h2>\n");
            promptBuilder.append("  <p>Article description 1</p>\n");
            promptBuilder.append("  <h2>Article Title 2</h2>\n");
            promptBuilder.append("  <p>Article description 2</p>\n");
            promptBuilder.append("</div>\n\n");
            promptBuilder.append("Now process the provided articles using EXACTLY the above format. No other text.\n\n");
        }
          for (Article article : articles) {
              promptBuilder
                      .append("Title: ")
                      .append(article.getTitle()).append("\n")
                      .append("Description: ")
                      .append(article.getDescription())
                      .append("End of the Article");
          }
          final String prompt = promptBuilder.toString();
          return prompt;
      }

}
