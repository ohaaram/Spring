package kr.co.ch09.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.co.ch09.dto.User4DTO;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@ToString
@Getter
@Builder
@Table(name="user4")
public class User4 {

    @Id
    private String uid;
    private String name;
    private String gender;
    private String age;
    private String hp;
    private String addr;

    public User4DTO toDTO(){

        return User4DTO.builder()
                .uid(uid)
                .name(name)
                .gender(gender)
                .age(age)
                .hp(hp)
                .addr(addr)
                .build();
    }
}
