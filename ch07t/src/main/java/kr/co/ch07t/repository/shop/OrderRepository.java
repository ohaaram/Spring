package kr.co.ch07t.repository.shop;

import kr.co.ch07t.entity.shop.OrderItem;
import kr.co.ch07t.repository.shop.custom.OrderRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderItem,Integer>, OrderRepositoryCustom {


}
