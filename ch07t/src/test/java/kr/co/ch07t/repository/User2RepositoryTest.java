package kr.co.ch07t.repository;

import kr.co.ch07t.dto.User2DTO;
import kr.co.ch07t.entity.user.User2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@SpringBootTest
class User2RepositoryTest {

    @Autowired
    private User2Repository repository;

    @Test
    @DisplayName("user2 등록")
    public void insertUser2(){

        User2 user2 = User2.builder()
                .uid("A108")
                .name("김수리")
                .birth("1998-12-12")
                .addr("김해시")
                .build();

        //when-entity 저장

        repository.save(user2);
    }
    @Test
    public void selectUser2(){

        //given - 조회 아이디
        String uid="A108";

        //when - 조회하기
        Optional<User2> result = repository.findById(uid);
        User2 user2 = result.get();

        log.info(user2.toString());



        /*
        *  Optional<User2> result = repository.findById(uid);
        User2 user2 = result.get();

        return user2.toDTO();
        *
        *
        * */
    }
    @Test
    public void selectUser2s(){


        //전체 조회
        List<User2> user2s = repository.findAll();

        //entity 리스트를 DTO 리스트로 변환
        List<User2DTO> user2DTOS = new ArrayList<>();

        for(User2 user2:user2s){
           user2DTOS.add(user2.toDTO());
        }

    }
    @Test
    public void updateUser2(){
        User2 user2 = User2.builder()
                .uid("A108")
                .name("김술이")
                .birth("1998-12-13")
                .addr("경주시")
                .build();

        repository.save(user2);

    }
    @Test
    public void deleteUser2(){
        String uid="A107";

        repository.deleteById(uid);

    }

}