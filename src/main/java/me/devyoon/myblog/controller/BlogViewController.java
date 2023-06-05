package me.devyoon.myblog.controller;

import lombok.RequiredArgsConstructor;
import me.devyoon.myblog.domain.Article;
import me.devyoon.myblog.dto.ArticleListViewResponse;
import me.devyoon.myblog.dto.ArticleViewResponse;
import me.devyoon.myblog.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleListViewResponse> articles = blogService.findAll().stream()
                .map(ArticleListViewResponse::new)
                .collect(Collectors.toList());
        model.addAttribute("articles", articles);  // 블로그 글 리스트 저장

        return "articleList"; // articleList.html라는 뷰 조회
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";
    }

    @GetMapping("/new-article")
    // id 키를 가진 쿼리 파라미터의 값을
    public String newArticle(@RequestParam(required = false) Long id, Model model) {
        if( id == null ) { // id가 없으면 생성
            model.addAttribute("article", new ArticleViewResponse());

        } else { // id가 있으면 수정
            Article article = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse());
        }

        return "newArticle";
    }
}
