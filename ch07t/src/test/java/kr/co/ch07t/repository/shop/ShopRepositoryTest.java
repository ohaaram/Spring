package kr.co.ch07t.repository.shop;

import jakarta.transaction.Transactional;
import kr.co.ch07t.entity.shop.Order;
import kr.co.ch07t.entity.shop.OrderItem;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class ShopRepositoryTest {

    @Autowired private CustomerRepository customerRepository;
    @Autowired private OrderRepository orderRepository;
    @Autowired private OrderItemRepository orderItemRepository;
    @Autowired private ProductRepository productRepository;


    @Transactional
    public void selectOrder(){


    }


    public void selectOrders(){


        }
}