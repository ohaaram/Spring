package kr.co.ch07t.dto;


import kr.co.ch07t.entity.user.User3;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User3DTO {

    private String uid;
    private String name;
    private String birth;
    private String hp;
    private String addr;

    public User3 toEntity(){
        return User3.builder()
                .uid(uid)
                .name(name)
                .birth(birth)
                .hp(hp)
                .addr(addr)
                .build();
    }
}
