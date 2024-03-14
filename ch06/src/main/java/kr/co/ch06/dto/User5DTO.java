package kr.co.ch06.dto;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User5DTO {

    private String seq;
    private String name;
    private String gender;
    private String age;
    private String addr;

}
