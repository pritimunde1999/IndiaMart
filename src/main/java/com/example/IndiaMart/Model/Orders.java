package com.example.IndiaMart.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="orders")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String orderNo;
    int totalValue;

    @CreationTimestamp
    Date orderDate;
    String cardUsed;

    @OneToMany(mappedBy = "orders",cascade = CascadeType.ALL)
    List<Items> itemsList = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    Customer customer;
}
