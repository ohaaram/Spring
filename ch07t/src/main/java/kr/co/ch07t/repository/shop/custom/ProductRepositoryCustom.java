package kr.co.ch07t.repository.shop.custom;

import kr.co.ch07t.entity.shop.Product;

import java.util.List;

public interface ProductRepositoryCustom {
    public List<Product> selectProducts();
    public Product selectProduct(int productId);
}
