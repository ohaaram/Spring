# Spring

날짜 : 2024/03/04
   이름 : 오아람
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


