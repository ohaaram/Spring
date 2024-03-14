package kr.co.ch06.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User1DTO {
    private String uid;
    private String name;
    private String birth;
    private String hp;
    private int age;

    //public User1DTO(String uid,String name,int age,String addr){}-->AllArgsConstructor가 이것
    //public User1DTO(){}-->NoAllArgsConstructor가 이것
}
