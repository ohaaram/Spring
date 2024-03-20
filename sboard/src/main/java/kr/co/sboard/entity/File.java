package kr.co.sboard.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="file")
@Builder
@ToString
@Entity
public class File {

    @Id
    private int fno;
    private int ano;
    private String oName;
    private String sName;
    private int download;

    @CreationTimestamp
    private LocalDateTime rdate;
}
