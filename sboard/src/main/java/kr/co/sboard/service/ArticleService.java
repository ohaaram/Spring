package kr.co.sboard.service;

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
    //RootConfig Bean 생성 등록
    private final ModelMapper modelMapper;


    public void insertArticle(ArticleDTO articleDTO) {

        //파일 첨부 처리
        List<FileDTO> files = fileService.fileUpload(articleDTO);

        //파일 첨부 갯수 초기화(?이거 왜 하지?)
        articleDTO.setFile(files.size());

        log.info("이거는 뭐 들어가지?:" + articleDTO);

        Article article = modelMapper.map(articleDTO, Article.class);//위랑 똑같음
        //대신에 DTO와 Entity에 to~~안만들어도 됨.

        log.info("저장되는 article값을 보자:" + article.toString());

        //저장 후 저장한 엔티티 객체 반환(사실 JPA save() 메서드는 default로 저장한 Entity를 반환)
        Article savedArticle = articleRepository.save(article);

        for(FileDTO fileDTO:files){
            fileDTO.setAno(savedArticle.getNo());

           File file = modelMapper.map(fileDTO, File.class);

            fileRepository.save(file);
        }

    }



    public ArticleDTO selectArticle(int no) {

        log.info("no값을 알려줭" + no);

        Optional<Article> result = articleRepository.findById(no);
        Article article = result.get();

        return modelMapper.map(article, ArticleDTO.class);

    }

    public PageResponseDTO findByParentAndCate(PageRequestDTO pageRequestDTO) {


        Pageable pageable = pageRequestDTO.getPageable("no");//sort한 글 번호

        Page<Article> pageArticle = articleRepository.findByParentAndCate(0,pageRequestDTO.getCate(),pageable);


        List<ArticleDTO> dtoList = pageArticle.getContent().stream()
                .map(entity -> modelMapper.map(entity, ArticleDTO.class))
                .toList();

        int total = (int)pageArticle.getTotalElements();

        return PageResponseDTO.builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total(total)
                .build();



    }

    public void updateArticle() {

    }

    public void deleteArticle() {

    }
}
