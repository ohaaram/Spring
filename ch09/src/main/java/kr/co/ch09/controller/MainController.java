package kr.co.ch09.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MainController {

    @GetMapping("/")
    public String index(){

        log.info("여기는 index MainController 로그임");

        return "/index";
    }

    @GetMapping("/user4/list")
    public String user4List(){

        log.info("여기는 list MainContorller 로그임.");

        return "/user4/list";
    }

    @GetMapping("/user4/register")
    public String user4Register(){

        log.info("register Main Controller 로그");

        return "/user4/register";
    }

    @GetMapping("/user4/modify")
    public String user4Modify(){

        log.info("modify main Controller 로그");

        return "/user4/modify";
    }

}