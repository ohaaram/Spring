package kr.co.ch09.controller;


import kr.co.ch09.dto.User1DTO;
import kr.co.ch09.service.User1Serivce;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@AllArgsConstructor
public class User1Controller {

    private final User1Serivce serivce;

    @ResponseBody
    @GetMapping("/user1")
    public List<User1DTO> list(){

        List<User1DTO> user1s = serivce.selectUser1s();

        return user1s;
    }


    @ResponseBody
    @GetMapping("/user1/{uid}")//?uid="a101"&name="김춘추"이렇게 넘겼던 거랑 똑같은 것
    public User1DTO user1(@PathVariable("uid") String uid){//위의 getmapping으로 받아오는 uid값을 String uid에 저장

       User1DTO user1DTO =  serivce.selectUser1(uid);
        return user1DTO;
    }


    @ResponseBody
    @PostMapping("/user1")
    public ResponseEntity<User1DTO> register(@RequestBody User1DTO user1DTO){

        serivce.insertUser1(user1DTO);
        /*

         ResponseEntity
         - API를 요청한 사용자에게 응답데이터를 구성해서 부가적인 정보 제공하기 위한 클래스
         - 일반적으로 상태코드(header), 본문내용(body)을 구성해서 제공

         @RestController
         - API 요청을 처리하는 메서드의 결과를 응답객체 본문에 출력시키는 클래스 어노테이션

        @RequestBody(json데이터를 수신할때 꼭 넣어줘야함-생략안됨)
         - 요청 본문 내용에 포함된 데이터를 Java 객체로 변환
         - 클라이언트에서 전송되는 JSON 데이터를 수신

         @ResponseBody
         - 메서드의 반환값을 응답객체 내용 본문으로 출력
         - JSON 출력 어노테이션

         @PathVariable
         - 주소 파라미터 값을 수신


         */

        return ResponseEntity.ok().body(user1DTO);//성공코드 200번으로 user1DTO를 그대로 보냄

    }

    @ResponseBody
    @PutMapping("/user1")
    public ResponseEntity<User1DTO> modify(@RequestBody User1DTO user1DTO){

        log.info(user1DTO.toString());

        User1DTO user1 = serivce.updateUser1(user1DTO);

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)//.ok()와 같음
                .body(user1);

    }
    @ResponseBody
    @DeleteMapping("/user1/{uid}")
    public void delete(@PathVariable("uid")String uid){

        serivce.deleteUser1(uid);
    }
}
