package kr.co.ch09.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.co.ch09.dto.User3DTO;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@ToString
@Getter
@Builder
@Table(name="user3")
@Entity
public class User3 {

    @Id
    private String uid;
    private String name;
    private String birth;
    private String hp;
    private String addr;

    public User3DTO toEntity(){
        return User3DTO.builder()
                .uid(uid)
                .name(name)
                .birth(birth)
                .hp(hp)
                .addr(addr)
                .build();
    }
}
