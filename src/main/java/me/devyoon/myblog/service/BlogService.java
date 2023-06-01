package me.devyoon.myblog.service;

import lombok.RequiredArgsConstructor;
import me.devyoon.myblog.domain.Article;
import me.devyoon.myblog.dto.AddArticleRequest;
import me.devyoon.myblog.repository.BlogRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor  // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service        // 빈으로 등록
public class BlogService {
    private final BlogRepository blogRepository;

    // 블로그 글 추가 메서드 입니다.
    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }
}
