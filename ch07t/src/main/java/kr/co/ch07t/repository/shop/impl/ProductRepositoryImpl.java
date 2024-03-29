package kr.co.ch07t.repository.shop.impl;


import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.ch07t.entity.shop.Product;
import kr.co.ch07t.entity.shop.QProduct;
import kr.co.ch07t.repository.shop.custom.ProductRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    QProduct qProduct = QProduct.product;

    public List<Product> selectProducts(){
        List<Product> products = jpaQueryFactory.select(qProduct).from(qProduct).fetch();

        return products;
    }

    @Override
    public Product selectProduct(int productId) {
        return jpaQueryFactory.selectFrom(qProduct).where(qProduct.productId.eq(productId)).fetchOne();
    }

}
