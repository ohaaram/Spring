package kr.co.ch07t.entity.shop;


import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
@Table(name="shop_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @ManyToOne
    @JoinColumn(name="orderer")
    private Customer customer;


    private int orderStatus;
    private int orderPrice;


    @CreationTimestamp
    private LocalDateTime orderDate;

    @OneToMany(mappedBy = "order")
    List<OrderItem> orderItems;


}
