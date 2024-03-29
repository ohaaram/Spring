package kr.co.ch07t.repository.shop;

import kr.co.ch07t.entity.shop.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void selectProducts(){
        List<Product> products = productRepository.selectProducts();
        log.info("selectProducts : "+products);
    }

    @Test
    public void selectProduct(){
        Product product = productRepository.selectProduct(5);
        log.info("selectProduct : "+product);

    }

}