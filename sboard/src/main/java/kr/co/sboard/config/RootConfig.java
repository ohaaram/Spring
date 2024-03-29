package kr.co.sboard.config;


import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@Getter
@Setter
@EnableAspectJAutoProxy
public class RootConfig {

    
    //빈드 정보 객체를 주입 받기 위해 build.gradle 파일 맨 밑에 builInfo()실행 해야됨
    @Autowired
    private BuildProperties buildProperties;//gradle 정보가 여기로 들어옴.build.gradle에 추가한거 참조(맨 마지막)

    @Bean
    public AppInfo appInfo(){
        
        String name = buildProperties.getName();
        String version = buildProperties.getVersion();

        return new AppInfo(name, version);
    }



    @Bean
    public ModelMapper modelMapper(){

        // Entity의 @Setter 선언없이 바로 private 속성으로 초기화 설정
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true);
        //Article에 setter가 없으면 에러가 나는데
        // (Article article = modelMapper.map(articleDTO,Article.class)<-여기서
        //직접적으로  Article에 setter를 넣어주기보다는 이 방법으로 해결하면 됨.

        return modelMapper;
    }

}
