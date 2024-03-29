package kr.co.ch07t.repository.shop;


import kr.co.ch07t.entity.shop.OrderItem;
import kr.co.ch07t.repository.shop.custom.OrderItemRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Integer>, OrderItemRepositoryCustom {


}
