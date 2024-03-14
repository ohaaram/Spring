package kr.co.ch07t.repository;


import kr.co.ch07t.entity.user.User5;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface User5Repository extends JpaRepository<User5,Integer> {



}
