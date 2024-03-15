package kr.co.ch10t.security;

import io.jsonwebtoken.JwtParser;
import kr.co.ch10t.jwt.JwtAuthenticationFilter;
import kr.co.ch10t.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtProvider jwtProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

       //토큰기반 인증 시큐리티 설정
        httpSecurity
                .csrf(CsrfConfigurer::disable)//사이트 위변조 방지
                .httpBasic(HttpBasicConfigurer::disable)//기본 HTTP 인증 방식 비활성
                .formLogin(FormLoginConfigurer::disable)//폼로그인 비활성
                .sessionManagement(config->config.sessionCreationPolicy(SessionCreationPolicy.STATELESS))//세션 비활성
                //토큰 검사 필터 등록
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/").permitAll()//최상단(localhost:8080/ch09로 접근했을 때 비밀번호 묻지 않음 - 로그인 화면이 안떠!)
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .requestMatchers("/manager/**").hasAnyAuthority("ADMIN", "MANAGER")
                        .anyRequest().permitAll());//존재하지 않는 주소 : 404에러, 시큐어리티는 기본적으로 리다이렉트를 시키기 때문에 이렇게 쓰면 리다이렉트를 시키지 않는다.


        // 사이트 위변조 방지 설정
        httpSecurity.csrf(CsrfConfigurer::disable);

        return httpSecurity.build();
    }

    // Security 인증 암호화 인코더 설정
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)throws Exception{
        return config.getAuthenticationManager();
    }


}