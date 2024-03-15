package kr.co.ch10t.controller;


import kr.co.ch10t.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MainController {

    private final UserService service;

    @GetMapping("/")
    public String index(){
        return "/index";
    }

    @GetMapping("/user/login")
    public String login(){
        return "/user/login";
    }

    @PreAuthorize("hasAuthority('USER')or hasAuthority('MANAGER')or hasAuthority('ADMIN')")
    @GetMapping("/user/list")
    public String list(){

           return "/user/list";
    }
}
