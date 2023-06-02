package me.devyoon.myblog.controller;

import lombok.RequiredArgsConstructor;
import me.devyoon.myblog.domain.Article;
import me.devyoon.myblog.dto.AddArticleRequest;
import me.devyoon.myblog.dto.ArticleResponse;
import me.devyoon.myblog.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class BlogApiController {

    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok()
                .body(articles);
    }


    @GetMapping("/api/articles/{id}") // URL에서 {id}에 해당하는 값이 id로 들어옴
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id) {
        Article article = blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }


}
