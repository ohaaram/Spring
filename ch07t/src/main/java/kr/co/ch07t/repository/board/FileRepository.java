package kr.co.ch07t.repository.board;

import kr.co.ch07t.entity.board.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File,Integer> {
}
