package kr.co.sboard.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.sboard.dto.ArticleDTO;
import kr.co.sboard.dto.PageRequestDTO;
import kr.co.sboard.dto.PageResponseDTO;
import kr.co.sboard.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ArticleController {

    private final ArticleService articleService;


    @GetMapping("/article/list")
    public String list(Model model, PageRequestDTO pageRequestDTO){

        PageResponseDTO pageResponseDTO = articleService.findByParentAndCate(pageRequestDTO);

        log.info("pageResponseDTO: " + pageResponseDTO);

        model.addAttribute(pageResponseDTO);

        return "/article/list";
    }

    @GetMapping("/article/write")
    public String write(@ModelAttribute("cate")String cate){

        log.info("여기는 잘 들어오나요?articleController get-Write");

        return "/article/write";
    }

    @PostMapping("/article/write")
    public String write(HttpServletRequest req, ArticleDTO articleDTO){

        String regip  = req.getRemoteAddr();

        articleDTO.setRegip(regip);

        log.info("articleDTO 값이 잘 들어오나(post-Write)?"+articleDTO);

        articleService.insertArticle(articleDTO);

        return "redirect:/article/list";
    }



    @GetMapping("/article/view")
    public String view(@ModelAttribute("no")int no,Model model){

        log.info("no값을 들고 와보자:"+no);

        ArticleDTO articleDTO = articleService.selectArticle(no);

        model.addAttribute(articleDTO);

        return "/article/view";
    }
}