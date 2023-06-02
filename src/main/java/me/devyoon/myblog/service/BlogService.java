package me.devyoon.myblog.service;

import lombok.RequiredArgsConstructor;
import me.devyoon.myblog.domain.Article;
import me.devyoon.myblog.dto.AddArticleRequest;
import me.devyoon.myblog.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor  // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service        // 빈으로 등록
public class BlogService {
    private final BlogRepository blogRepository;


    // 블로그 글 추가 메서드 입니다.
    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }


    // findAll()을 호출해 article 테이블에 저장되어 있는 모든 데이터를 조회한다.
    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    // JPA에서 제공하는 findById() 메서드를 사용해 ID를 받아 엔티티를 조회하고 없으면 IllegalArgumentException 예외 발생
    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }
}
