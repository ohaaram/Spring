package kr.co.ch10t.controller;


import kr.co.ch10t.dto.UserDTO;
import kr.co.ch10t.entity.User;
import kr.co.ch10t.jwt.JwtProvider;
import kr.co.ch10t.security.MyUserDetails;
import kr.co.ch10t.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final UserService service;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {

        try {
            //sercurity 인증 처리
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(userDTO.getUid(), userDTO.getPass());

            //사용자 DB 조회
            Authentication authentication = authenticationManager.authenticate(authToken);

            //인증된 사용자 가져오기
            MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();//인증된 사용자 객체
            User user = myUserDetails.getUser();

            //토큰 발급(액세스, 리프레쉬)
            String access = jwtProvider.createToken(user, 1);//1일
            String refresh = jwtProvider.createToken(user, 7);//7일

            //리프레쉬 토큰 DB 저장

            //액세스 코튼 클라이언트 전송
            Map<String, Object> map = new HashMap<>();
            map.put("grantType", "Bearer");
            map.put("access", access);//id,role이 저장되어 있음

            return ResponseEntity.ok().body(map);
        } catch (Exception e) {
            log.info("login...." + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
        }
    }

    @GetMapping("/user/{uid}")
    public void user(@PathVariable("uid") String uid) {


        log.info("user.....");

    }

    @GetMapping("/user")
    public ResponseEntity<List<UserDTO>> list(){

        return service.selectUser2s();
    }

}
