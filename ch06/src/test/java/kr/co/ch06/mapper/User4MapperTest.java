package kr.co.ch06.mapper;

import kr.co.ch06.dto.User4DTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class User4MapperTest {

    @Autowired
    private User4Mapper mapper;


    @DisplayName("user4 등록")
    void insertUser4() {
        log.info("insertUser4....");

        User4DTO user4DTO = User4DTO.builder()
                .uid("a105")
                .name("오아람")
                .gender("F")
                .age("26")
                .hp("010-1234-2122")
                .addr("센텀시").build();

        mapper.insertUser4(user4DTO);

        User4DTO resultUser4 = mapper.selectUser4(user4DTO.getUid());
        assertEquals(user4DTO.getUid(),resultUser4.getUid());
    }


    @DisplayName("user4 조회")
    void selectUser4() {
        log.info("selectUser4...");

        String uid="a105";

        User4DTO user4DTO = mapper.selectUser4(uid);
        log.info(user4DTO.toString());
        assertEquals(uid,user4DTO.getUid());

    }


    @DisplayName("user4 목록")
    void selectUser4s() {

        log.info("selectUser4s...");

        List<User4DTO> users = null;

        users = mapper.selectUser4s();

        for(User4DTO user:users)
            log.info(user.toString());

        assertFalse(users.isEmpty());
    }


    @DisplayName("user4 수정")
    void updateUser4() {
        log.info("updateUser4...");

        User4DTO user4DTO = User4DTO.builder()
                .uid("a105")
                .name("오아람람")
                .gender("M")
                .age("33")
                .hp("010-1234-6666")
                .addr("경주시").build();

        mapper.updateUser4(user4DTO);

        User4DTO resultUser4 = mapper.selectUser4(user4DTO.getUid());
        assertEquals(user4DTO.getName(),resultUser4.getName());
    }

    @Test
    @DisplayName("user4 삭제")
    void deleteUser4() {
        log.info("deleteUser4....");

        String uid="a105";

        mapper.deleteUser4(uid);

        User4DTO resultUser4 = mapper.selectUser4(uid);
        assertNull(resultUser4);
    }
}