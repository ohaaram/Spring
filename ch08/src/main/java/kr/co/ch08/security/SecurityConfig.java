package kr.co.ch08.security;

import kr.co.ch08.security.SecurityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        // 인증 설정(로그인)
        httpSecurity.formLogin(login -> login
                .loginPage("/user1/login")
                .defaultSuccessUrl("/user1/success")
                .failureUrl("/user1/login?success=100")
                .usernameParameter("uid")
                .passwordParameter("pass"));

        // 로그아웃 설정
        httpSecurity.logout(logout -> logout
                .invalidateHttpSession(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/user1/logout"))
                .logoutSuccessUrl("/user1/login?success=200"));



        // 인가 설정
        httpSecurity.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/").permitAll()//최상단(localhost:8080/ch08로 접근했을 때 비밀번호 묻지 않음)
                .requestMatchers("/admin/**").hasAuthority("ADMIN")
                .requestMatchers("/manager/**").hasAnyAuthority("ADMIN", "MANAGER")
                .anyRequest().permitAll());//존재하지 않는 주소 : 404에러, 시큐어리티는 기본적으로 리다이렉트를 시키기 때문에 이렇게 쓰면 리다이렉트를 시키지 않는다.


        /*
        * 인가 설정
        * - Spring Sercurity는 존재하지 않는 요청 주소에 대해 기본적으로 login페이지로 redirect를 수행
        * - 지원 요청의 추가 인가 처리 확장과 redirect 기본 해제를 위해 .anyRequest().permitAll() 설정
        * */




        // 사이트 위변조 방지 설정
        httpSecurity.csrf(CsrfConfigurer::disable);

        return httpSecurity.build();
    }

    // Security 인증 암호화 인코더 설정
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}