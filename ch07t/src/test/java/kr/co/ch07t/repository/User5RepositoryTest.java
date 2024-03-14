package kr.co.ch07t.repository;

import kr.co.ch07t.dto.User5DTO;
import kr.co.ch07t.entity.user.User5;
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
class User5RepositoryTest {

    @Autowired
    private User5Repository repository;

    @Test
    public void insertUser5(){
        User5 user5 =User5.builder()
                .name("고춘희")
                .gender("M")
                .age("25")
                .addr("서동")
                .build();

        repository.save(user5);
    }

    @Test
    public void selectUser5(){
        int seq=34;
        Optional<User5> result = repository.findById(seq);
        User5 user5 =result.get();

        log.info(user5.toString());
    }

    @Test
    public void selectUser5s(){

        List<User5> user5s = repository.findAll();

        List<User5DTO> user5DTOS = new ArrayList<>();

        for(User5 user5:user5s){
            user5DTOS.add(user5.toDTO());
        }
    }
    @Test
    public void updateUser5(){

        User5 user5 = User5.builder()
                .seq(24)
                .name("고영희")
                .gender("F")
                .age("20")
                .addr("경상도")
                .build();

        repository.save(user5);
    }
    @Test
    public void deleteUser5(){
        int seq=19;

        repository.deleteById(seq);
    }

}