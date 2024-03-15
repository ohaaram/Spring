package kr.co.ch09.controller;



import kr.co.ch09.dto.User4DTO;
import kr.co.ch09.service.User4Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@AllArgsConstructor
public class User4Controller {

    private final User4Service service;

    @GetMapping("/user4")
    public ResponseEntity<?> list(){

        return service.selectUser4s();
    }

    @GetMapping("/user4/{uid}")
    public ResponseEntity<?> register(@PathVariable("uid")String uid){

        log.info("register user4Controller(GetMapping)입니다.");

        return service.selectUser4(uid);

    }

    @PostMapping("/user4")
    public ResponseEntity<?>register(@RequestBody @Validated User4DTO user4DTO){

        log.info("register user4Controller(postMapping) user4DTO"+user4DTO.toString());
        return service.insertUser4(user4DTO);
    }

    @PutMapping("/user4")
    public ResponseEntity<?> modify(@RequestBody User4DTO user4DTO){

        log.info("modify user4Controller(putmapping)");

        return service.updateUser4(user4DTO);
    }

    @DeleteMapping("/user4/{uid}")
    public ResponseEntity<?> delete(@PathVariable("uid")String uid){

        log.info("delete user4Controller(deleteMapping)");
        return service.deleteUser4(uid);
    }

}
