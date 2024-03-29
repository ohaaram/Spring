package kr.co.sboard.entity;

import jakarta.persistence.*;
import kr.co.sboard.dto.ArticleDTO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="article")
@Builder
@Setter
@ToString
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;

    private int parent;
    private int comment;
    private String cate;
    private String title;
    private String content;
    private String writer;
    private int file;
    private int hit;

     private String regip;

    @CreationTimestamp
    private LocalDateTime rdate;


    @OneToMany(mappedBy = "ano")//mappedBy연관관계의 주인이 누구냐(외래키 지정)
    private List<File> fileList;//one to Many 이면 리스트


    private String nick;//@Transient 이거는 테이블 만들때 생성하지 않는다.
}
