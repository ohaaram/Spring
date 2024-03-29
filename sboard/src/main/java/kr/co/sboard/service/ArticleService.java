package kr.co.sboard.service;

import com.querydsl.core.Tuple;
import kr.co.sboard.dto.ArticleDTO;
import kr.co.sboard.dto.FileDTO;
import kr.co.sboard.dto.PageRequestDTO;
import kr.co.sboard.dto.PageResponseDTO;
import kr.co.sboard.entity.Article;
import kr.co.sboard.entity.File;
import kr.co.sboard.repository.ArticleRepository;
import kr.co.sboard.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final FileRepository fileRepository;
    private final  FileService fileService;

    private final ModelMapper modelMapper;


    public void insertArticle(ArticleDTO articleDTO) {

        List<FileDTO> files=null;

        if(articleDTO.getFiles()!=null) {

            //파일 첨부 처리
            files = fileService.fileUpload(articleDTO);

            //파일 첨부 갯수 초기화
            articleDTO.setFile(files.size());
        }

        Article article = modelMapper.map(articleDTO, Article.class);

        log.info("저장되는 article값을 보자:" + article.toString());

        //저장 후 저장한 엔티티 객체 반환(사실 JPA save() 메서드는 default로 저장한 Entity를 반환)
        Article savedArticle = articleRepository.save(article);

       if(files!=null) {
           for (FileDTO fileDTO : files) {
               fileDTO.setAno(savedArticle.getNo());

               File file = modelMapper.map(fileDTO, File.class);

               fileRepository.save(file);
           }
       }

    }



    public ArticleDTO findById(int no) {

        log.info("no값을 알려줭" + no);

        Optional<Article> result = articleRepository.findById(no);

        Article article = result.get();

        log.info("article볼껀데:"+article.toString());

        ArticleDTO articleDTO = modelMapper.map(article, ArticleDTO.class);

        log.info("매퍼로 변환하고의 상태 : "+articleDTO.toString());

        return articleDTO;

    }

    public PageResponseDTO selectArticles(PageRequestDTO pageRequestDTO) {

        log.info("selectArticles...1");

        Pageable pageable = pageRequestDTO.getPageable("no");//sort한 글 번호

        log.info("selectArticles...2");

        Page<Tuple> pageArticle = articleRepository.selectArticles(pageRequestDTO,pageable);


        log.info("selectArticles...3");

        List<ArticleDTO> dtoList = pageArticle.getContent().stream()
                .map(tuple ->
                        {
                            log.info("tuple : " + tuple);
                            Article article = tuple.get(0, Article.class);
                            String nick = tuple.get(1, String.class);
                            article.setNick(nick);

                            log.info("article : " + article);

                            return modelMapper.map(article, ArticleDTO.class);
                        }
                )
                .toList();

        log.info("selectArticles...4");

        int total = (int)pageArticle.getTotalElements();

        return PageResponseDTO.builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total(total)
                .build();
    }


    public PageResponseDTO searchArticles(PageRequestDTO pageRequestDTO) {


        Pageable pageable = pageRequestDTO.getPageable("no");//sort한 글 번호

        Page<Tuple> pageArticle = articleRepository.searchArticles(pageRequestDTO,pageable);


        List<ArticleDTO> dtoList = pageArticle.getContent().stream()
                .map(tuple ->
                        {
                            log.info("tuple : " + tuple);
                            Article article = tuple.get(0, Article.class);
                            String nick = tuple.get(1, String.class);
                            article.setNick(nick);

                            log.info("article : " + article);

                            return modelMapper.map(article, ArticleDTO.class);
                        }
                )
                .toList();

        int total = (int)pageArticle.getTotalElements();

        return PageResponseDTO.builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total(total)
                .build();
    }






    public void update(ArticleDTO articleDTO){

        log.info("article-service-update : "+articleDTO.toString());

        Article article = modelMapper.map(articleDTO, Article.class);//위랑 똑같음

        articleRepository.save(article);

    }

    public void hitUp(ArticleDTO articleDTO){

        articleDTO.setHit(articleDTO.getHit()+1);

        Article article = modelMapper.map(articleDTO, Article.class);

        log.info("조회수에 관한 hitUp 메서드 : "  +article.toString());

        articleRepository.save(article);

    }

}
