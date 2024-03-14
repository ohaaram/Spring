package kr.co.ch07t.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kr.co.ch07t.entity.user.User5;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User5DTO {

    private int seq;//이걸 int->Integer로 바꿈
    private String name;
    private String gender;
    private String age;
    private String addr;


    /*
    public User5 toEntity(){
        User5 user5 = new User5();
        if(this.seq!=null){
            user5.setSeq(this.seq);
        }
        user5.setName(this.name);
        user5.setGender(this.gender);
        user5.setAge(this.age);
        user5.setAddr(this.addr);
        return user5;
    }
    */


    public User5 toEntity(){

        return User5.builder()
                .seq(seq)
                .name(name)
                .gender(gender)
                .age(age)
                .addr(addr)
                .build();
    }

}
