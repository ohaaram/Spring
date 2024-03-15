package kr.co.ch09.controller;

import kr.co.ch09.dto.User2DTO;
import kr.co.ch09.service.User2Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Slf4j
@RestController//responsebody를 적지 않아도 된다.
public class User2Controller {

    private final User2Service service;


    @GetMapping("/user2")
    public ResponseEntity<List<User2DTO>> list(){

        return service.selectUser2s();
    }


    @GetMapping("/user2/{uid}")
    public ResponseEntity<?> register(@PathVariable("uid")String uid){
        return service.selectUser2(uid);
    }

    @PostMapping("/user2")
    public ResponseEntity<?> register(@RequestBody @Validated User2DTO user2DTO){

        log.info("왜 에러지?" + user2DTO.toString());

       return service.insertUser2(user2DTO);

    }

    @PutMapping("/user2")
    public ResponseEntity<?> modify(@RequestBody User2DTO user2DTO){

        log.info("뭐여 깐뜨롤러-modify"+user2DTO.toString());

        return service.updateUser2(user2DTO);
    }

    @DeleteMapping("/user2/{uid}")
    public ResponseEntity<?> delete(@PathVariable("uid")String uid){

        return service.deleteUser2(uid);
    }
}
