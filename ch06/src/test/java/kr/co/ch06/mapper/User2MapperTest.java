package kr.co.ch06.mapper;

import kr.co.ch06.dto.User2DTO;
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
class User2MapperTest {

    @Autowired
    private User2Mapper mapper;


    
    @DisplayName("user2 등록")
    void insertUser2() {
        log.info("insertUser2");

        //given : 테스트를 실행할 준비 단계
        User2DTO user2DTO = User2DTO.builder()
                .uid("j101")
                .name("오아람")
                .birth("1992-04-13")
                .addr("경주시").build();

        //when : 테스트를 진행하는 단계
        mapper.insertUser2(user2DTO);
        
        //then : 테스트 결과를 검증하는 단계
        User2DTO resultUser2 = mapper.selectUser2(user2DTO.getUid());
        Assertions.assertEquals(user2DTO.getUid(),resultUser2.getUid());
    }

    
    @DisplayName("user2 조회")
    void selectUser2() {
        log.info("selectUser2...");
        
        //given : 테스트를 실행할 준비 단계
        String uid="j101";

        //when : 테스트를 진행하는 단계
        User2DTO user2DTO = mapper.selectUser2(uid);
        log.info(user2DTO.toString());

        
        //then : 테스트 결과를 검증하는 단계
        assertEquals(uid,user2DTO.getUid());
        
    }

    @Test
    @DisplayName("user2 목록")
    void selectUser2s() {
        log.info("selectUser2s");

        //given: 테스트를 실행할 준비 단계
        List<User2DTO> users = null;

        //when: 테스트를 진행하는 단계
        users = mapper.selectUser2s();

        for(User2DTO user:users)
          log.info(user.toString());

        //then: 테스트 결과를 검증한는 단계(Assertion 단정문 이용해 가독성 있게 결과 확인)
        assertFalse(users.isEmpty());
    }

    @Test
    @DisplayName("user2 수정")
    void updateUser2() {

        log.info("updateUser2....");


        //given
        User2DTO user2DTO = User2DTO.builder()
                .uid("j101")
                .name("오아람람")
                .birth("1990-04-13")
                .addr("해운대").build();

        //when
        mapper.updateUser2(user2DTO);

        //then
        User2DTO resultUser2 = mapper.selectUser2(user2DTO.getUid());
        assertEquals(user2DTO.getName(),resultUser2.getName());
    }

    @Test
    @DisplayName("user2 삭제")
    void deleteUser2() {
        log.info("deleteUser2.....");

        //given
        String uid="j101";

        //when
        mapper.deleteUser2(uid);

        //then
        User2DTO resultUser2 = mapper.selectUser2(uid);
        assertNull(resultUser2);
    }
}