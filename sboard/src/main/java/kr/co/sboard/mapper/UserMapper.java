package kr.co.sboard.mapper;

import kr.co.sboard.dto.ArticleDTO;
import kr.co.sboard.dto.TermsDTO;
import kr.co.sboard.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    public TermsDTO selectTerms();
    public void insertUser(UserDTO userDTO);
    public int selectCountUser(@Param("type") String type,@Param("value") String value);
    public String selectUser(String email);
    public UserDTO selectUser(String name, String email);
    public UserDTO findPass(String uid, String email);
    public UserDTO findById(String uid);
    public void updatePass(String pass,String uid);
    public void updateInfo(UserDTO userDTO);

}