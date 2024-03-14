package kr.co.ch07t.repository;

import kr.co.ch07t.entity.user.User1;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


@Slf4j
@SpringBootTest
class User1RepositoryTest {

    @Autowired
    private User1Repository repository;


    @DisplayName("user1 등록")
    public void insertUser1(){

        User1 user1 = User1.builder()
                    .uid("j101")
                    .name("김유신")
                .birth("1990-03-12")
                .hp("010-1234-1002")
                .age(21)
                .build();

        //when-entity 저장
        repository.save(user1);
    }

    @DisplayName("user1 조회")
    public void selectUser1(){
        
        //given - 조회 아이디
        String uid="j101";

        //when - 조회하기
        Optional<User1> result = repository.findById(uid);
        User1 user1 = result.get();
        log.info(user1.toString());
    }

    //사용자 정의 쿼리 메서드 테스트
    @Test
    public void findUser1ByUid(){
        User1 user1  = repository.findUser1ByUid("j102");
        log.warn(user1.toString());
    }
    @Test
    public void findUser1ByName(){
        List<User1> user1s  = repository.findUser1ByName("김유신");
        log.warn(user1s.toString());

    }
    @Test
    public void findUser1ByNameNot(){
        List<User1> user1s  = repository.findUser1ByNameNot("김유신");
        log.warn(user1s.toString());
    }

    @Test
    public void findUser1ByUidAndName(){
        User1 user1  = repository.findUser1ByUidAndName("P101","김유신");
        log.warn(user1.toString());
    }
    @Test
    public void findUser1ByUidOrName(){
        List<User1> user1s  = repository.findUser1ByUidOrName("j102","김유신");
        log.warn(user1s.toString());

    }
    @Test
    public void findUser1ByAgeGreaterThan(){
        List<User1> user1s  = repository.findUser1ByAgeGreaterThan(23);
        log.warn(user1s.toString());

    }
    @Test
    public void findUser1ByAgeGreaterThanEqual(){
        List<User1> user1s  = repository.findUser1ByAgeGreaterThanEqual(23);
        log.warn(user1s.toString());
    }
    @Test
    public void findUser1ByAgeLessThan(){
        List<User1> user1s  = repository.findUser1ByAgeLessThan(23);
        log.warn(user1s.toString());
    }
    @Test
    public void findUser1ByAgeLessThanEqual(){
        List<User1> user1s  = repository.findUser1ByAgeLessThanEqual(23);
        log.warn(user1s.toString());
    }
    @Test
    public void findUser1ByAgeBetween(){
        List<User1> user1s  = repository.findUser1ByAgeBetween(23,40);
        log.warn(user1s.toString());
    }
    @Test
    public void findUser1ByNameLike(){
        List<User1> user1s  = repository.findUser1ByNameLike("김유");
        log.warn(user1s.toString());
    }
    @Test
    public void findUser1ByNameContains(){
        List<User1> user1s  = repository.findUser1ByNameContains("동");
        log.warn(user1s.toString());
    }
    @Test
    public void findUser1ByNameStartsWith(){
        List<User1> user1s  = repository.findUser1ByNameStartsWith("홍");
        log.warn(user1s.toString());
    }
    @Test
    public void findUser1ByNameEndsWith(){
        List<User1> user1s  = repository.findUser1ByNameEndsWith("동");
        log.warn(user1s.toString());
    }

    @Test
    public void findUser1ByOrderByName(){
        List<User1> user1s  = repository.findUser1ByOrderByName();
        log.warn(user1s.toString());
    }
    @Test
    public void findUser1ByOrderByAgeAsc(){
        List<User1> user1s  = repository.findUser1ByOrderByAgeAsc();
        log.warn(user1s.toString());
    }
    @Test
    public void findUser1ByOrderByAgeDesc(){
        List<User1> user1s  = repository.findUser1ByOrderByAgeDesc();
        log.warn(user1s.toString());
    }
    @Test
    public void findUser1ByAgeGreaterThanOrderByAgeDesc(){
        List<User1> user1s  = repository.findUser1ByAgeGreaterThanOrderByAgeDesc(30);
        log.warn(user1s.toString());
    }

    @Test
    public void countUser1ByUid(){
        int user1  = repository.countUser1ByUid("j102");
        log.warn(String.valueOf(user1));
    }
    @Test
    public void countUser1ByName(){
        int user1  = repository.countUser1ByName("홍동동");
        log.warn(String.valueOf(user1));
    }

    //JPQL 정의
    @Test
    @Query("select u1 from User1 as u1 where u1.age<30")
    public void selectUser1UnderAge30(){
        List<User1> user1s  = repository.selectUser1UnderAge30();
        log.warn(user1s.toString());
    }

    @Test
    public void selectUser1ByName(){
        List<User1> user1s  = repository.selectUser1ByName("홍동동");
        log.warn(user1s.toString());
    }

    @Test
    public void selectUser1ByNameParam(){
        List<User1> user1s  = repository.selectUser1ByNameParam("홍동동");
        log.warn(user1s.toString());

    }

    @Test
    public void selectUser1ByUid(){
        List<Object[]> user1s = repository.selectUser1ByUid("j102");
        log.warn(user1s.toString());

    }
    
}