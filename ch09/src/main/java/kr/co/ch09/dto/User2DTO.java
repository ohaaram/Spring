package kr.co.ch09.dto;


import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import kr.co.ch09.entity.User2;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User2DTO {
    /*
    - REST API 서비스 특성상 프론트앤드에서 유효성 검사를 진행하기 어려움
    - @Validated,@NotBlank,@NotEmpty등 백엔드에서 유효성 검사를 진행



     */


    private String uid;
    private String name;
    private String birth;
    private String email;
    private int age;
    private String addr;

    public User2 toEntity(){
        return User2.builder()
                .uid(uid)
                .name(name)
                .birth(birth)
                .email(email)
                .age(age)
                .addr(addr)
                .build();
    }
}
