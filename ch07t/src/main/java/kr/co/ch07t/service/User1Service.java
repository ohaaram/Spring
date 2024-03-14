package kr.co.ch07t.service;


import kr.co.ch07t.dto.User1DTO;
import kr.co.ch07t.entity.user.User1;
import kr.co.ch07t.repository.User1Repository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class User1Service {

    /*
    save : insert into
    findById : select * from ~ where~
    findAll :  select * from
    *
    * */

    //생성자 주입
    private final User1Repository repository;

    public void insertUser1(User1DTO user1DTO){
        // DTO를 Entity로 변환
        User1 user1 = user1DTO.toEntity();

        // Entity 저장(데이터베이스 Insert)
        repository.save(user1);
    }


    public User1DTO selectUser1(String uid){

        /*
        * optional
        * - null값 검증처리를 손쉽게 활용하는 구조체
        * - Spring JPA 조회 결과 타입
        *
        * */
        Optional<User1> result = repository.findById(uid);
        User1 user1 = result.get();

        return user1.toDTO();

    }
    public List<User1DTO> selectUser1s(){
        //전체 조회
        List<User1> user1s = repository.findAll();

        //entity 리스트를 DTO 리스트로 변환
        /*
        List<User1DTO> user1DTOS = new ArrayList<>();

        for(User1 user1:user1s){
           //User1DTO user1DTO = user1.toDTO();
           //user1DTOS.add(user1DTO);

            user1DTOS.add(user1.toDTO());//위의 두줄과 똑같음
        }
        */
       List<User1DTO> user1DTOS = user1s.stream()
                .map(entity -> User1DTO.builder()
                        .uid(entity.getUid())
                        .name(entity.getName())
                        .birth(entity.getBirth())
                        .hp(entity.getHp())
                        .age(entity.getAge())
                        .build())
                .collect(Collectors.toList());


        return user1DTOS;

    }
    public void updateUser1(User1DTO user1DTO){
        //DTO를 entity로 변환
        User1 user1 = user1DTO.toEntity();

        //entity 수정(데이터베이스 update)
        repository.save(user1);

    }
    public void deleteUser1(String uid){
        //entity 삭제(데이터베이스 delete)
        repository.deleteById(uid);
    }
}
