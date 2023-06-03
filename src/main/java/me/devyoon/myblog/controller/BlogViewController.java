package me.devyoon.myblog.controller;

import lombok.RequiredArgsConstructor;
import me.devyoon.myblog.dto.ArticleListViewResponse;
import me.devyoon.myblog.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
