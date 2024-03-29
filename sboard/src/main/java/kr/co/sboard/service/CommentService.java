package kr.co.sboard.service;

import kr.co.sboard.dto.ArticleDTO;
import kr.co.sboard.entity.Article;
import kr.co.sboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {

    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper;

    public ResponseEntity<?> insertComment(ArticleDTO articleDTO){

        Article article = modelMapper.map(articleDTO,Article.class);
        Article savedArticle = articleRepository.save(article);


        return ResponseEntity.ok().body(savedArticle);
    }

    public ResponseEntity<List<ArticleDTO>> selectComments(int no){

        // ArticleRepository > findByParent() 쿼리 메서드 정의
        List<Article> articleList = articleRepository.findByParent(no);

        List<ArticleDTO> articleDTOS = articleList.stream()
                .map(entity -> modelMapper.map(entity, ArticleDTO.class))
                .toList();


        log.info(articleDTOS.toString());

        return ResponseEntity.ok().body(articleDTOS);
    }

    public void deleteComment(int no){

        articleRepository.deleteById(no);
    }

    public ResponseEntity<?> modifyComment(ArticleDTO articleDTO){
        Article article = modelMapper.map(articleDTO,Article.class);

        log.info("comment - service - modify - artilce값 : "+article);

        Article savedArticle = articleRepository.save(article);

        log.info("comment - service - modify - artilce값 : "+savedArticle);

        return ResponseEntity.ok().body(savedArticle);

    }

}
