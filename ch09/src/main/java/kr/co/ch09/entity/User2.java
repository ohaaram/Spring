package kr.co.ch09.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.co.ch09.dto.User2DTO;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Getter
@ToString
@Slf4j
@NoArgsConstructor
@Table(name="user2")
@AllArgsConstructor
@Builder
@Entity
public class User2 {

    @Id
    private String uid;
    private String name;
    private String birth;
    private String email;
    private int age;
    private String addr;


    public User2DTO toDTO(){
        return User2DTO.builder()
                .uid(uid)
                .name(name)
                .birth(birth)
                .email(email)
                .age(age)
                .addr(addr)
                .build();
    }
}
