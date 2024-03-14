package kr.co.ch06.dto;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User4DTO {

    private String uid;
    private String name;
    private String gender;
    private String age;
    private String hp;
    private String addr;

}
