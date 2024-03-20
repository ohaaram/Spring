package kr.co.sboard.dto;

import jakarta.persistence.Id;
import kr.co.sboard.entity.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {

    private String uid;
    private String pass;
    private String name;
    private String nick;
    private String email;
    private String hp;
    private String role;
    private String zip;
    private String addr1;
    private String addr2;
    private String regip;
    private String sms;
    private LocalDateTime regDate;
    private LocalDateTime leaveDate;
}
