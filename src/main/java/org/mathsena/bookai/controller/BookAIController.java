package org.mathsena.bookai.controller;

import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bookai")
@CrossOrigin(origins = "*")
public class BookAIController {

  @Autowired private OpenAiChatClient openAiChatClient;

  @GetMapping("/info")
  public String bookChat(
      @RequestParam(
              value = "message",
              defaultValue = "Me indique os 10 melhores livros da história")
          String message) {
    return openAiChatClient.call(message);
  }

    @GetMapping("/recommend")
    public String recommendBooks(
            @RequestParam(value = "genre", required = false) String genre,
            @RequestParam(value = "author", required = false) String author,
            @RequestParam(value = "favoriteBooks", required = false) String favoriteBooks) {
        String query = String.format("Recomende livros baseados em gênero: %s, autor: %s, livros favoritos: %s", genre, author, favoriteBooks);
        return openAiChatClient.call(query);
    }

}
