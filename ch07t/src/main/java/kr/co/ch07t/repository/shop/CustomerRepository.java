package kr.co.ch07t.repository.shop;


import kr.co.ch07t.entity.shop.Customer;
import kr.co.ch07t.repository.shop.custom.CustomerRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,String>, CustomerRepositoryCustom {


}
