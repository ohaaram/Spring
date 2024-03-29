package kr.co.ch07t.repository.shop;

import kr.co.ch07t.entity.shop.Product;
import kr.co.ch07t.repository.shop.custom.ProductRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>, ProductRepositoryCustom {



}
