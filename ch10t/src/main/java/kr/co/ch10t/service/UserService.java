package kr.co.ch10t.service;


import kr.co.ch10t.dto.UserDTO;
import kr.co.ch10t.entity.User;
import kr.co.ch10t.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    private final UserService service;

    public ResponseEntity<List<UserDTO>> selectUser2s() {
        List<UserDTO> userDTOs = repository.findAll()
                .stream()
                .map(entity -> UserDTO.builder()
                        .uid(entity.getUid())
                        .pass(entity.getPass())
                        .name(entity.getName())
                        .hp(entity.getHp())
                        .age(entity.getAge())
                        .regDate(entity.getRegDate())
                        .role(entity.getRole())
                        .build())
                .toList();
        return ResponseEntity
                .ok()
                .body(userDTOs);
    }
}
