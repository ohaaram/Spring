package kr.co.sboard.service;

import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import kr.co.sboard.dto.ArticleDTO;
import kr.co.sboard.dto.TermsDTO;
import kr.co.sboard.dto.UserDTO;
import kr.co.sboard.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {


    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    private final JavaMailSender javaMailSender;

    public TermsDTO selectTerms(){
        return userMapper.selectTerms();
    }

    public String selectUser(String email){

         return userMapper.selectUser(email);

    }

    public int selectCountUser(String type,String value){

        return userMapper.selectCountUser(type,value);
    }

    public void insertUser(UserDTO userDTO){

        String encoded = passwordEncoder.encode(userDTO.getPass());

        userDTO.setPass(encoded);

        userMapper.insertUser(userDTO);
    }

    @Value("${spring.mail.username}")
    private String sender;

    public void sendEmailCode(HttpSession session, String receiver){

        log.info("sender : " + sender);

        // MimeMessage 생성
        MimeMessage message = javaMailSender.createMimeMessage();

        // 인증코드 생성 후 세션 저장
        int code = ThreadLocalRandom.current().nextInt(100000, 1000000);
        session.setAttribute("code", String.valueOf(code));

        String title = "sboard 인증코드 입니다.";
        String content = "<h1>인증코드는 " + code + "입니다.</h1>";

        try {
            message.setSubject(title);
            message.setFrom(new InternetAddress(sender, "보내는 사람", "UTF-8"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            message.setSubject(title);
            message.setContent(content, "text/html;charset=UTF-8");

            javaMailSender.send(message);

        }catch(Exception e){
            log.error("sendEmailConde : " + e.getMessage());
        }

    }

    public UserDTO selectUser(UserDTO userDTO){
        return userMapper.selectUser(userDTO.getName(), userDTO.getEmail());
    }

    public UserDTO findPass(UserDTO userDTO){
        return userMapper.findPass(userDTO.getUid(),userDTO.getEmail());
    }

    public UserDTO findById(String uid){
        return userMapper.findById(uid);
    }

    public void updatePass(UserDTO userDTO){
        String encoded = passwordEncoder.encode(userDTO.getPass());
        userDTO.setPass(encoded);

        userMapper.updatePass(userDTO.getPass(),userDTO.getUid());
    }

    public void updateInfo(UserDTO userDTO){
        userMapper.updateInfo(userDTO);

    }

}