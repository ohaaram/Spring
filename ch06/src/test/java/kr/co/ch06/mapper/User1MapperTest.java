package kr.co.ch06.mapper;

import kr.co.ch06.dto.User1DTO;
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
class User1MapperTest {

    @Autowired
    private User1Mapper mapper;



    @DisplayName("user1 등록")
    void insertUser1() {
        log.info("insertUser1");
        //테스트 코드 패턴 : given - when - then

        //given : 테스트를 실행할 준비 단계
        User1DTO user1DTO=User1DTO.builder()

                        .uid("j102")
                        .name("홍길동")
                        .birth("1990-03-11")
                        .hp("010-1234-1111")
                        .age(22).build();


        //when : 테스트를 진행하는 단계
        mapper.insertUser1(user1DTO);


        //then: 테스트 결과를 검증하는 단계(Assertion 단정문 이용해 가독성 있게 결과 확인)
        User1DTO resultUser1 = mapper.selectUser1(user1DTO.getUid());
        assertEquals(user1DTO.getUid(),resultUser1.getUid());//(등록데이터,조회데이터)

    }


    @DisplayName("user1 조회")
    void selectUser1() {
        log.info("selectUser1");

        //given
        String uid="j102";

        //when
        User1DTO user1DTO = mapper.selectUser1(uid);
        log.info(user1DTO.toString());

        //then
        assertEquals(uid,user1DTO.getUid());
    }



    @DisplayName("user1 목록")
    void selectUser1s() {
        log.info("selectUser1s");


        //given
        List<User1DTO> users = null;

        //when
        users = mapper.selectUser1s();
        log.info(users.toString());

        for(User1DTO user:users){
            log.info(user.toString());
        }

        //then
        assertFalse(users.isEmpty());

    }

    @Test
    @DisplayName("user1 수정")
    void updateUser1() {
        log.info("updateUser1");


        //given
        User1DTO user1DTO=User1DTO.builder()

                .uid("j102")
                .name("홍길복")
                .birth("1990-03-11")
                .hp("010-1234-1222")
                .age(24).build();

        //when
        mapper.updateUser1(user1DTO);

        //then
        User1DTO resultUser1 = mapper.selectUser1(user1DTO.getUid());
        assertEquals(user1DTO.getName(),resultUser1.getName());//(수정할 이름, 수정된 이름)

    }

    @Test
    @DisplayName("user1 삭제")
    void deleteUser1() {
        log.info("deleteUser1");

        //given
        String uid = "j101";

        //when
        mapper.deleteUser1(uid);

        //then
        User1DTO resultUser1 = mapper.selectUser1(uid);
        assertNull(resultUser1);//삭제가 잘되었는지 검증

    }
}