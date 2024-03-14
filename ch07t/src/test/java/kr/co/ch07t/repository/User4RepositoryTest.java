package kr.co.ch07t.repository;

import kr.co.ch07t.dto.User4DTO;
import kr.co.ch07t.entity.user.User4;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@SpringBootTest
class User4RepositoryTest {

    @Autowired
    private User4Repository repository;



    public void insertUser4(){
        User4 user4 = User4.builder()
                .uid("a106")
                .name("김춘영")
                .gender("F")
                .age("21")
                .hp("010-5555-9989")
                .addr("서울")
                .build();

        repository.save(user4);
    }


    public void selectUser4(){

        String uid="a105";
        Optional<User4> result = repository.findById(uid);
        User4 user4 = result.get();

        log.info(user4.toString());

    }


    public void selectUser4s(){

        List<User4> user4s = repository.findAll();

        List<User4DTO> user4DTOS = new ArrayList<>();

        for(User4 user4:user4s){
            user4DTOS.add(user4.toDTO());
        }
    }


    public void updateUser4(){

        User4 user4 = User4.builder()
                .uid("a105")
                .name("김영춘")
                .gender("M")
                .age("54")
                .hp("010-4444-1234")
                .addr("해운대구")
                .build();

        repository.save(user4);
    }
    @Test
    public void deleteUser4(){
        String uid="a106";

        repository.deleteById(uid);
    }

}