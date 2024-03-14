package kr.co.ch07t.service;


import kr.co.ch07t.dto.User2DTO;
import kr.co.ch07t.entity.user.User2;
import kr.co.ch07t.repository.User2Repository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class User2Service {

    private final User2Repository repository;

    public void insertUser2(User2DTO user2DTO){

        //DTO를 Entity로 변환
        User2 user2 = user2DTO.toEntity();

        //Entity 저장(데이터베이스 Insert)
        repository.save(user2);
    }


    public User2DTO selectUser2(String uid){

        Optional<User2> result = repository.findById(uid);
        User2 user2 = result.get();

        return user2.toDTO();
    }

    public List<User2DTO> selectUser2s(){
        List<User2> user2s = repository.findAll();

        List<User2DTO> user2DTOS = user2s.stream()
                .map(entity -> User2DTO.builder()
                        .uid(entity.getUid())
                        .name(entity.getName())
                        .birth(entity.getBirth())
                        .addr(entity.getAddr())
                        .build())
                .collect(Collectors.toList());

        return user2DTOS;
    }

    public void updateUser2(User2DTO user2DTO){

        //DTO를 entity로 변환
        User2 user2 = user2DTO.toEntity();

        //entity 수정(데이터 베이스 update)
        repository.save(user2);
    }

    public void deleteUser2(String uid){
        repository.deleteById(uid);
    }
}
