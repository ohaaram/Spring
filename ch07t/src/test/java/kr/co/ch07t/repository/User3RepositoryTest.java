package kr.co.ch07t.repository;

import kr.co.ch07t.dto.User3DTO;
import kr.co.ch07t.entity.user.User3;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Slf4j
class User3RepositoryTest {

    @Autowired
    private User3Repository repository;


    public void insertUser3(){
        User3 user3 = User3.builder()
                .uid("a102")
                .name("김리")
                .birth("1990-10-15")
                .hp("010-5558-1234")
                .addr("경주시 경주군")
                .build();


        repository.save(user3);
    }


    public void selectUser3(){

        String uid="a102";

        Optional<User3> result = repository.findById(uid);
        User3 user3 = result.get();

        log.info(user3.toString());
    }


    public void selectUser3s(){
        List<User3> user3s = repository.findAll();

        List<User3DTO> user3DTOS = new ArrayList<>();

        for(User3 user3 :user3s){
            user3DTOS.add(user3.toDTO());
        }
    }


    public void updateUser3(){

        User3 user3 = User3.builder()
                .uid("a102")
                .name("김술")
                .birth("1960-09-12")
                .hp("010-1234-5777")
                .addr("해운대 경주시")
                .build();

        repository.save(user3);
    }


    @Test
    public void deleteUser3(){
        String uid="a102";

        repository.deleteById(uid);
    }



}