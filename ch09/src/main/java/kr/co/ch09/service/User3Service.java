package kr.co.ch09.service;


import kr.co.ch09.dto.User3DTO;
import kr.co.ch09.entity.User3;
import kr.co.ch09.repository.User3Repository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class User3Service {
    private final User3Repository reposiotry;

    public ResponseEntity<?> insertUser3(User3DTO user3DTO){

        if(reposiotry.existsById(user3DTO.getUid())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(user3DTO.getUid()+"already exist");

        }else{
            User3 user3 = user3DTO.toEntity();
            reposiotry.save(user3);

            return ResponseEntity.status(HttpStatus.OK).body(user3DTO);
        }

    }
    public ResponseEntity<?> selectUser3(String uid){

        try{
            User3 user3 = reposiotry.findById(uid).orElseThrow();

            return ResponseEntity.status(HttpStatus.OK).body(user3.toDTO());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
        }

    }
    public ResponseEntity<?> selectUser3s(){

        List<User3DTO> user3DTOS = reposiotry.findAll()
                .stream()
                .map(entity->User3DTO.builder()
                        .uid(entity.getUid())
                        .name(entity.getName())
                        .birth(entity.getName())
                        .hp(entity.getHp())
                        .addr(entity.getAddr())
                        .build())
                .toList();

        return ResponseEntity.ok().body(user3DTOS);

    }
    public ResponseEntity<?> updateUser3(User3DTO user3DTO){

        if(reposiotry.existsById(user3DTO.getUid())){
            reposiotry.save(user3DTO.toEntity());

            return ResponseEntity.status(HttpStatus.OK).body(user3DTO);

        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("user not found");
        }

    }
    public ResponseEntity<?> deleteUser3(String uid){

        Optional<User3> optUser3 = reposiotry.findById(uid);

        if(optUser3.isPresent()){
            reposiotry.deleteById(uid);
            return ResponseEntity.ok().body(optUser3.get());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
        }

    }



}
