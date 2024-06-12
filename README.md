# Spring

내용 : Spring IoC/DI 실습하기
  
  @Configuration
   - 애플리케이션을 구성하는 빈을 등록하기 위한 설정 클래스
    - XML,설정파일 대신 Java 클래스를 사용해 스프링 컨테이너 설정
     
  @Bean
   - 컨테이너에 등록하기 위한 빈 설정
   - 사용자 정의 클래스, 외부 라이브러리 빈 등록
 
  @ComponentScan
   - basePackage로 시작하는 패키지내의 빈을 스캔해서 컨테이너에 등록
   - 스캔 대상 컴포넌트는 @Component 선언
 
  @Component
    - 스캐닝으로 컨테이너에 등록할 대상 컴포넌트 설정
   - @Controller,@Service, @Repository로 파생
 
   @Autowired
   - 컨테이너의 빈을 주입
   - 이름 또는 클래스 타입으로 매칭된 빈을 주입





내용 : Spring AOP 실습하기
 
  라이브러리
   - spring-aspects 의존성 추가
 
  주요용어
   1) 조인포인트(JoinPoint)
    - 실행하는 모든 핵심관심 메서드
 
   2) 포인트컷(PointCut)
    - 조인포인트 가운데 실행(AOP가 설정)되는 핵심관심 메서드
 
   3) 어드바이스(Advice)
    - 횡단관심에 해당하는 공통의 부가기능 메서드
 
   4) 애스펙트(Aspect)
    - 포인트컷과 어드바이스의 결합된 모듈형태
 
   5) 위빙(Weaving)
    - 포인트컷(핵심관심)이 실행될때 어드바이스가 포인트컷에 결합되는 과정
 
  포인트컷 표현식
   execution(리턴타입 패키지명.클래스명.메서드명(매개변수))
 
   1) 리턴타입
    - *     : 모든 리턴타입 허용
    - void  : 리턴타입이 void인 메서드
    - !void : 리턴타입이 void가 아닌 메서드
 
   2) 패키지명
    - kr.co.ch03          : 해당 패키지 대상
    - kr.co.ch03..        : kr.co.ch03로 시작하는 모든 패키지 대상
    - kr.co.ch03..service : kr.co.ch03로 시작해서 마지막 패키지명이 service로 끝나는 패키지 대상
 
   3) 클래스명
    - BasicService : 해당 클래스 대상
    - *Service     : 클래스명이 Service로 끝나는 클래스 대상
 
   4) 메서드명
    - *(..)  : 매개변수가 제한이 없는 모든 메서드
    - *(*)   : 매개변수를 1개 갖는 모든 메서드
    - *(*,*) : 매개변수를 2개 갖는 모든 메서드
    - get*() : 매개변수가 없고 메서드 이름이 get으로 시작하는 메서드


    
    내용 :Spring MVC 실습하기

   Spring MVC 라이브러리
    - spring-context 6.1.4
    - spring-webmvc 6.1.4
    - jakarta.servlet-api 6.0.0
    - jakarta.servlet.jsp.jstl-api 3.0.0
    - jakarta.servlet.jsp.jstl 3.0.1
    - Apache Commons DBCP » 2.11.0
    - Spring JDBC » 6.1.4
    - MySQL Connector/J » 8.3.0

    *반드시 톰캣은 apache-tomcat-10-1.19으로 해야됨

    WebApplicationInitializer
     - 스프링 웹 애플리케이션 초기화를 위한 인터페이스
     - DispatcherServlet을 생성하고 컨텍스트 등록
     - 웹 애플리케이션 컨텍스트(컨테이너)를 설정하고 필요한 서블릿, 필터, 리스너 등 설정

     WebMvcConfigurer
      - 스프링 웹 MVC 구성 컴포넌트 설정을 위한 인터페이스
      - 뷰리졸버(ViewResolver) 설정 및 resourceHandler 설정 등 애플리케이션 전반에 걸친 자원 설정

     @EnableWebMvc
      - 스프링 MVC를 구성하고 MVC 관련 기능을 활성화하는 어노테이션

