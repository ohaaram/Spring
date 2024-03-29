package kr.co.sboard.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.co.sboard.config.AppInfo;
import kr.co.sboard.dto.ArticleDTO;
import kr.co.sboard.dto.TermsDTO;
import kr.co.sboard.dto.UserDTO;
import kr.co.sboard.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final AppInfo appInfo;
    private UserService userService1;

    @GetMapping("/user/login")
    public String login(@ModelAttribute("success") String success) {

        return "/user/login";
    }

    @GetMapping("/user/terms")
    public String terms(Model model) {

        TermsDTO termsDTO = userService.selectTerms();
        model.addAttribute(termsDTO);

        return "/user/terms";
    }

    @GetMapping("/user/register")
    public String register() {
        return "/user/register";
    }

    @PostMapping("/user/register")
    public String register(HttpServletRequest req, UserDTO userDTO) {

        String regip = req.getRemoteAddr();
        userDTO.setRegip(regip);

        log.info(userDTO.toString());

        userService.insertUser(userDTO);

        return "redirect:/user/register?success=200";
    }

    @ResponseBody
    @GetMapping("/user/{type}/{value}")
    public ResponseEntity<?> checkUser(HttpSession session,
                                       @PathVariable("type") String type,
                                       @PathVariable("value") String value) {

        int count = userService.selectCountUser(type, value);
        log.info("count : " + count);

        if (type.equals("email") && count<=0) {
            log.info("email : " + value);
            userService.sendEmailCode(session, value);
        }

        // Json 생성
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", count);

        return ResponseEntity.ok().body(resultMap);
    }

    // 이메일 인증 코드 검사
    @ResponseBody
    @GetMapping("/email/{code}")
    public ResponseEntity<?> checkEmail(HttpSession session, @PathVariable("code") String code) {

        String sessionCode = (String) session.getAttribute("code");

        if (sessionCode.equals(code)) {
            // Json 생성
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("result", true);

            return ResponseEntity.ok().body(resultMap);
        } else {
            // Json 생성
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("result", false);

            return ResponseEntity.ok().body(resultMap);
        }
    }


    @GetMapping("/user/findId")
    public String findId() {

        return "/user/findId";
    }




    @PostMapping("/user/findId")
    public ResponseEntity<?> findId(HttpSession session, @RequestBody UserDTO userDTO) {

        log.info("여기까지 잘 들어오는지 체크!");

        log.info("userDTO : " + userDTO);

        int result = 0;

        try {
            UserDTO user = userService.selectUser(userDTO);
            log.info("이거 내 디비에 이름과 이메일을 이용해서 나온 결과값 : " + user.toString());
            session.setAttribute("sessUser", user);
            result = 1;

        }catch (Exception e){
            result = 0;
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", result);

        return ResponseEntity.ok().body(resultMap);
    }

    @GetMapping("/user/findIdResult")
    public String findIdResult(HttpSession session, Model model) {
        //입력한 값은 session에 name으로 저장이 되어 있음. 화면을 띄우기전에 UserDTO를 세션에 저장해서 띄우기
        //DB에 있는 값과 세션에 저장되어 있는 값을 비교하고 맞으면 세션에 저장하기

        log.info("여기는 들어오나?");

        UserDTO userDTO = (UserDTO) session.getAttribute("sessUser");
        model.addAttribute(userDTO);


        return "/user/findIdResult";
    }

    @ResponseBody
    @GetMapping("/sendEmail/{type}/{value}")
    public ResponseEntity<Map<String, Object>> sendEmail(HttpSession session,
                                                         @PathVariable("type") String type,
                                                         @PathVariable("value") String value) {

        int count = userService.selectCountUser(type, value);
        log.info("count : " + count);


        session.setAttribute("email", value);//email을 세션에 저장

        if (count > 0) {//db에 있는 email이라면 이메일 인증을 보내기
            // 이메일 인증이면
            if (type.equals("email")) {
                log.info("email : " + value);
                userService.sendEmailCode(session, value);
            }
        }
        // Json 생성
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", count);

        return ResponseEntity.ok().body(resultMap);


    }


    @GetMapping("/user/findPassword")
    public String findPassword(){

        return "/user/findPassword";

    }


    @PostMapping("/user/findPassword")
    public ResponseEntity<?> findPassword(HttpSession session,@RequestBody UserDTO userDTO){

        log.info("여기는 findPassword의 postmapping");

        log.info("userDTO : " + userDTO);

        int result = 0;

        try {
            UserDTO user = userService.findPass(userDTO);
            log.info("이거 내 디비에 이름과 이메일을 이용해서 나온 결과값 : " + user.toString());
            session.setAttribute("sessUser", user);
            result = 1;

        }catch (Exception e){
            result = 0;
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", result);

        return ResponseEntity.ok().body(resultMap);
    }

    @GetMapping("/user/findPasswordChange")
    public String findPasswordChange(HttpSession session,Model model){

        UserDTO userDTO = (UserDTO) session.getAttribute("sessUser");
        model.addAttribute(userDTO);

        return "/user/findPasswordChange";
    }

    @PostMapping("/user/findPasswordChange")
    public ResponseEntity<?> findPasswordChange(@RequestBody UserDTO userDTO){

        String id = userDTO.getUid();
        String pass = userDTO.getPass();

        log.info("내가 입력한 id값 : "+id);
        log.info("내가 입력한 pass : "+ pass);

        userDTO = userService.findById(id);

        log.info("findPasswordChange : "+userDTO);

        userDTO.setPass(pass);

        log.info("findPasswordChange -비밀번호 변경후 : "+userDTO);

        userService.updatePass(userDTO);

        int result = 1;

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", result);

        log.info("여기로는 오는건가?");

        return ResponseEntity.ok().body(resultMap);

    }

    @GetMapping("/my/setting")
    public String setting(@ModelAttribute("uid")String uid,Model model){

        //log.info("주소에서 내 아이디를 받아와보자 : "+uid); 잘 받아와지는군...

         UserDTO userDTO = userService.findById(uid);//내 정보를 담아와보자

        model.addAttribute("userDTO",userDTO);

        return "/my/setting";

    }

    @PostMapping("/my/setting")
    public String setting(UserDTO userDTO){

        log.info("userDTO : "+userDTO);

        userService.updateInfo(userDTO);

        return "redirect:/my/setting?uid="+userDTO.getUid();
    }

    @GetMapping("/my/setting/deleteUser")
    public String deleteUser(){




        return "/user/login";
    }

}