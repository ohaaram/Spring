package kr.co.ch04.controller;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @RequestMapping(value ="/hello" , method = RequestMethod.GET)
    public String hello(){
        System.out.println("hello....");
        return "hello";

    }

    @RequestMapping(value = "/welcome")
    public String welcome(){
        System.out.println("welcome....");
        return "welcome";
    }

    @RequestMapping(value = "/greeting")
    public String greeting(){
        System.out.println("greeting...");
        return "greeting";
    }


}
