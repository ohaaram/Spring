package kr.co.ch07t.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@AllArgsConstructor
public class MainController {

    @GetMapping(value = {"/","/index"})
    public String index(){
        return "/index";
    }

}
