package kr.co.ch07t.repository.shop;

import kr.co.ch07t.entity.shop.Order;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void selectOders(){
        List<Order> orders = orderRepository.selectOrders();
        log.info("selectOrders : "+orders);
    }

    public void selectOrder(){
        Order order = orderRepository.selectOrder(4);
        log.info("selectOrder : "+order);
    }




}