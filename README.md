
# 📰 News Brief with AI

This project is a **Spring Boot application** that fetches the latest news from the [News API](https://newsapi.org/) and generates **concise summaries** using an **AI model served via Ollama** (e.g., Mistral).

## ✨ Features

* 🔗 Integrates with **News API** to fetch top headlines.
* 🤖 Uses **Ollama AI models** to generate summaries.
* 📑 Provides summaries in two formats:

  * **JSON** response with plain text summary
  * **HTML** response for quick rendering in a UI
* 🛠 Built with **Spring Boot** for clean, scalable, and maintainable code.

## 📡 Endpoints

* `GET /api/v1/news-brief/general-brief` → Get a summarized version of top headlines in JSON.
* `GET /api/v1/news-brief/general-brief/render` → Get a rendered HTML summary (ready to embed in a webpage).

## 🏗 How It Works

1. **NewsApiClient** → Fetches the latest headlines from News API.
2. **OllamaClient** → Sends articles to Ollama with a custom prompt.
3. **NewsBriefService** → Coordinates fetching + summarization.
4. **NewsBriefController** → Exposes REST endpoints for JSON or HTML summaries.

## ⚙️ Tech Stack

* **Java 21** + **Spring Boot**
* **Lombok** for clean DTOs
* **RestTemplate** for API calls
* **Ollama (Mistral model)** for AI summaries

## 🚀 Getting Started

1. Clone the repo

   ```bash
   git clone https://github.com/yourusername/news-brief-with-ai.git
   ```
2. Configure your `application.properties` with:

   ```properties
   news.api.key=YOUR_NEWS_API_KEY
   news.api.base.url=https://newsapi.org/v2
   news.api.default.country=us
   news.api.url.content.top.headlines=/top-headlines

   ollama.base.url=http://localhost:11434/api/generate
   ollama.mistral.model=mistral
   ```
3. Run the application:

   ```bash
   ./mvnw spring-boot:run
   ```
4. Open your browser:

   * JSON summary → [http://localhost:8080/api/v1/news-brief/general-brief](http://localhost:8080/api/v1/news-brief/general-brief)
   * HTML summary → [http://localhost:8080/api/v1/news-brief/general-brief/render](http://localhost:8080/api/v1/news-brief/general-brief/render)

---

