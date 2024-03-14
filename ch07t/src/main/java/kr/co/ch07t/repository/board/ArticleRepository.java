package kr.co.ch07t.repository.board;

import kr.co.ch07t.entity.board.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ArticleRepository extends JpaRepository<Article,Integer> {



}
