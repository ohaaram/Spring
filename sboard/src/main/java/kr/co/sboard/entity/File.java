package kr.co.sboard.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="file")
@Builder
@ToString
@Entity
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fno;
    private int ano;
    private String oName;
    private String sName;

    @ColumnDefault("0")
    private int download;

    @CreationTimestamp
    private LocalDateTime rdate;

}
