package kr.co.ch04.dao;

import kr.co.ch04.dto.User1DTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User1RowMapper implements RowMapper<User1DTO> {
    /*
    *  - select 결과 처리 메서드
    *  - select 결과가 1개 이상이면 리스트로 생성
    *  - select 결과가 1개이면 DTO 생성
    *
    *
    * */



    @Override
    public User1DTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        //select의 결과값이 여기로 들어온다

        User1DTO user1DTO = new User1DTO();

        user1DTO.setUid(rs.getString(1));
        user1DTO.setName(rs.getString(2));
        user1DTO.setBirth(rs.getString(3));
        user1DTO.setHp(rs.getString(4));
        user1DTO.setAge(rs.getInt(5));


        return user1DTO;
    }
}
