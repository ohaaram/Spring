package kr.co.ch06.service;


import kr.co.ch06.dto.User1DTO;
import kr.co.ch06.mapper.User1Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class User1Service {

    @Autowired
    private User1Mapper mapper;


    public void insertUser1(User1DTO user1DTO){
        mapper.insertUser1(user1DTO);
    }
    public User1DTO selectUser1(String uid){

        return mapper.selectUser1(uid);

    }
    public List<User1DTO> selectUser1s(){
        return mapper.selectUser1s();
    }
    public void updateUser1(User1DTO user1DTO){
        mapper.updateUser1(user1DTO);
    }
    public void deleteUser1(String uid){
        mapper.deleteUser1(uid);
    }
}
