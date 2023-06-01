package me.devyoon.myblog.repository;

import me.devyoon.myblog.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BlogRepository extends JpaRepository<Article, Long> {
}
