package kr.co.ch06.mapper;

import kr.co.ch06.dto.User4DTO;
import kr.co.ch06.dto.User5DTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Slf4j
class User5MapperTest {

    @Autowired
    private User5Mapper mapper;



    @Test
    @DisplayName("user5 등록")
    void insertUser5() {
        log.info("insertUser5....");


        User5DTO user5DTO = User5DTO.builder()
                .name("오아람")
                .gender("M")
                .age("34")
                .addr("해운대").build();


        mapper.insertUser5(user5DTO);


        //assertEquals(res,1);

        User5DTO resultUser5 = mapper.selectUser5(user5DTO.getSeq());
        assertEquals(user5DTO.getName(),resultUser5.getName());
    }

    @DisplayName("user5 조회")
    void selectUser5() {
        log.info("selectUser5.....");

        String seq = "12";

        User5DTO user5DTO = mapper.selectUser5(seq);
        log.info(user5DTO.toString());

        assertEquals(seq,user5DTO.getSeq());

    }



    @DisplayName("user5 목록")
    void selectUser5s() {

        log.info("selectUser5s....");

        List<User5DTO> users = null;

        users = mapper.selectUser5s();

        for(User5DTO user:users)
            log.info(user.toString());

        assertFalse(users.isEmpty());

    }


    @DisplayName("user5 수정")
    void updateUser5() {
        log.info("updateUser5....");

        User5DTO user5DTO = User5DTO.builder()
                .seq("12")
                .name("오아람람")
                .gender("F")
                .age("25")
                .addr("센텀").build();

        mapper.updateUser5(user5DTO);

        User5DTO resultUser5 = mapper.selectUser5(user5DTO.getSeq());
        assertEquals(user5DTO.getName(),resultUser5.getName());
    }


    @DisplayName("user5 삭제")
    void deleteUser5() {

        log.info("deleteUser5....");

        String seq="14";

        mapper.deleteUser5(seq);

        User5DTO resultUser5 = mapper.selectUser5(seq);
        assertNull(resultUser5);
    }
}