package kr.co.ch07t.repository.shop;

import kr.co.ch07t.entity.shop.OrderItem;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Slf4j
class OrderItemRepositoryTest {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Test
    public void selectOderItems(){
        List<OrderItem> orderItems = orderItemRepository.selectOrderItems();
        log.info("selectOrderItems : "+orderItems);
    }

    @Test
    public void selectOrderItem(){

        OrderItem orderItem = orderItemRepository.selectOrderItem(3);
        log.info("selectOrderItem : "+orderItem);
    }

}