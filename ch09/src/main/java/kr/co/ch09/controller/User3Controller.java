package kr.co.ch09.controller;


import kr.co.ch09.dto.User3DTO;
import kr.co.ch09.service.User3Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class User3Controller {

    private final User3Service service;

    @GetMapping("/user3")
    public ResponseEntity<?> list(){

        return service.selectUser3s();
    }

    @GetMapping("/user3/{uid}")
    public ResponseEntity<?> register(@PathVariable("uid")String uid){
        return service.selectUser3(uid);
    }

    @PostMapping("/user3")
    public ResponseEntity<?> register(@RequestBody @Validated User3DTO user3DTO){

        log.info("여기 뭔 문제?"+user3DTO.toString());

        return service.insertUser3(user3DTO);
    }

    @PutMapping("/user3")
    public ResponseEntity<?> modify(@RequestBody User3DTO user3DTO){

        return service.updateUser3(user3DTO);
    }

    @DeleteMapping("/user3/{uid}")
    public ResponseEntity<?> delete(@PathVariable("uid")String uid){
        return service.deleteUser3(uid);
    }


}
