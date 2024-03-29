package kr.co.ch07t.repository.shop.impl;


import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.ch07t.entity.shop.Order;
import kr.co.ch07t.entity.shop.QOrder;
import kr.co.ch07t.repository.shop.OrderRepository;
import kr.co.ch07t.repository.shop.custom.OrderRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryImpl implements OrderRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    QOrder qOrder = QOrder.order;


    @Override
    public List<Order> selectOrders() {

        List<Order> orders = jpaQueryFactory.select(qOrder).from(qOrder).fetch();

        return orders;
    }

    @Override

    public Order selectOrder(int orderId) {
        return jpaQueryFactory.selectFrom(qOrder).where(qOrder.orderId.eq(orderId)).fetchOne();
    }
}
