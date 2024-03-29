package kr.co.sboard.confing;


import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RootConfig {

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
