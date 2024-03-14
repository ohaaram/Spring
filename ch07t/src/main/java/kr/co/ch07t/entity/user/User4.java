package kr.co.ch07t.entity.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.co.ch07t.dto.User4DTO;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name="user4")
@Entity
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
