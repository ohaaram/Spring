package kr.co.ch07t.repository.board;

import jakarta.transaction.Transactional;
import kr.co.ch07t.entity.board.Article;
import kr.co.ch07t.entity.board.Comment;
import kr.co.ch07t.entity.board.File;
import kr.co.ch07t.entity.board.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
@Slf4j
class BoardRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private UserRepository userRepository;


    @Test
    public void insertUser(){

        //작업1 - 사용자 등록
        User user = User.builder()
                .uid("a103")
                .name("김준추")
                .hp("010-1234-5648")
                .build();

        userRepository.save(user);
    }


    public void insertArticle(){
        //작업2 - 글 등록

        User user = User.builder()
                .uid("a101")
                .build();

        Article article = Article.builder()
                .title("제목3 입니다.")
                .content("내용3 입니다.")
                .user(user)
                .build();

        articleRepository.save(article);
    }


    public void insertComment(){
        //작업3 - 댓글 등록
        User user = User.builder()
                .uid("a101")
                .build();

        Article article = Article.builder()
                .no(1)
                .build();


        Comment comment = Comment.builder()
                .content("댓글1 입니다.")
                .user(user)
                .article(article)
                .build();

        commentRepository.save(comment);

    }

    public void insertFile(){

        Article article = Article.builder()
                .no(1)
                .build();


        File file = File.builder()
                .oName("원래 파일명.txt")
                .sName("ABDCAS122.txt")
                .article(article)
                .build();

        fileRepository.save(file);
    }
    /*
     연관관계로 설정된 엔티티를 조회할 때 하나 이상의 Select가 실행되기 때문에
     @Transactional 선언으로 한번의 실행으로 처리해야 no session 에러 방지
    */

    @Transactional
    public void selectArticles(){

        List<Article> articles = articleRepository.findAll();

        for(Article article:articles){

            log.info(article.toString());
        }
    }
}