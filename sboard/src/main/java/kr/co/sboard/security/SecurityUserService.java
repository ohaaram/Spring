package kr.co.sboard.security;

import kr.co.sboard.entity.User;
import kr.co.sboard.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@AllArgsConstructor
@Service
@Slf4j
public class SecurityUserService implements UserDetailsService {


    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> result = userRepository.findById(username);//사용자의 아이디가 여기로 들어옴.위의 String username으로.


        UserDetails userDetails = null;
        if(!result.isEmpty()){
            //해당하는 사용자가 존재하면 인증 객체 생성
            User user = result.get();

            log.info("user안 : "+user);

            userDetails = MyUserDetails.builder()
                    .user(user)
                    .build();

            log.info("userDetails : "+userDetails);
        }
        //Security ContextHolder 저장
        return userDetails;
    }
}
