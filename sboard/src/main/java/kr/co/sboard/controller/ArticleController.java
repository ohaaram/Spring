package kr.co.sboard.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.sboard.config.AppInfo;
import kr.co.sboard.dto.ArticleDTO;
import kr.co.sboard.dto.FileDTO;
import kr.co.sboard.dto.PageRequestDTO;
import kr.co.sboard.dto.PageResponseDTO;
import kr.co.sboard.entity.Config;
import kr.co.sboard.repository.ConfigRepository;
import kr.co.sboard.service.ArticleService;
import kr.co.sboard.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ArticleController {

    private final ArticleService articleService;
    private final ConfigRepository configRepository;

    /*
        @ModelAttribute("cate")
         - modelAttribute("cate", cate)와 동일
    */

    @GetMapping("/article/list")
    public String list(Model model,String cate,PageRequestDTO pageRequestDTO){

        PageResponseDTO pageResponseDTO=null;


        if(pageRequestDTO.getKeyword()==null) {
            //일반 글 목록 조회
            pageResponseDTO = articleService.selectArticles(pageRequestDTO);
        }else {
            //검색 글 목록 조회
            pageResponseDTO = articleService.searchArticles(pageRequestDTO);
        }

        log.info("pageResponseDTO : " + pageResponseDTO);

        model.addAttribute(pageResponseDTO);

        return "/article/list";
    }

    @GetMapping("/article/write")
    public String write(Model model,@ModelAttribute("cate") String cate, PageRequestDTO pageRequestDTO){//여기 확인해보기
        log.info("GetMapping write : " +cate);

        PageResponseDTO pageResponseDTO = PageResponseDTO.builder()
                .pageRequestDTO(pageRequestDTO)
                .build();
        log.info("GetMapping pageResponseDTO : " +pageResponseDTO.getCate());
        model.addAttribute(pageResponseDTO);


        return "/article/write";
    }

    @PostMapping("/article/write")
    public String write(HttpServletRequest req, ArticleDTO articleDTO){
        /*
            글작성을 테스트할 때는 로그인해야하기 때문에
            SecurityConfig 인가 설정 수정할 것
        */
        String regip = req.getRemoteAddr();
        articleDTO.setRegip(regip);

        log.info("너를 함 찍어보자꾸나 : " + articleDTO.toString());

        articleService.insertArticle(articleDTO);

        return "redirect:/article/list?cate="+articleDTO.getCate();
    }

    @GetMapping("/article/view")
    public String view(Model model,String cate, int no){

        ArticleDTO articleDTO = articleService.findById(no);

        articleService.hitUp(articleDTO);//조회수를 올려줌

        model.addAttribute(articleDTO);

        return "/article/view";
    }

    @GetMapping("/article/modify/{no}")
    public String modify(Model model,String cate,@PathVariable("no")int no){

        ArticleDTO articleDTO = articleService.findById(no);

        log.info("article-controller-modify(Get) : "+articleDTO.toString());


       model.addAttribute("articleDTO",articleDTO);

       log.info("여기까지는 실행이 되는것인가?");
       log.info("제목 : " +articleDTO.getTitle());
       log.info("내용 : " +articleDTO.getContent());
       log.info("첨부파일 갯수 : "+articleDTO.getFile());//일단 1개...count를 올려야하고
       log.info("첨부파일 :"+articleDTO.getFiles());//이거는 왜 계속 null이지?

        return "/article/modify";
    }

    @PostMapping("/article/update/{no}")
    public String modify(HttpServletRequest req,@PathVariable("no")int no,ArticleDTO articleDTO){

        log.info("여긴 아예 안들어오니?");

        String regip = req.getRemoteAddr();
        articleDTO.setRegip(regip);
        articleDTO.setRdate(LocalDateTime.now());

        //Temp에 있는 파일 내용을 일단 articleDTO에 저장해보자


        log.info("articleService - controller-update  제목 : "+articleDTO.getTitle());
        log.info("articleService - controller-update  내용 : "+articleDTO.getContent());

        articleService.insertArticle(articleDTO);

        return "redirect:/article/list";
    }
}