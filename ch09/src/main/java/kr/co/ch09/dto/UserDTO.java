package kr.co.ch09.dto;

import kr.co.ch09.entity.User;
import lombok.*;

import java.time.LocalDateTime;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {


    private String uid;
    private String pass;
    private String name;
    private String age;
    private String hp;
    private String role;
    private LocalDateTime regDate;

    public User toEntity(){

        return User.builder()
                .uid(uid)
                .pass(pass)
                .name(name)
                .age(age)
                .hp(hp)
                .role(role)
                .regDate(regDate)
                .build();
    }




}