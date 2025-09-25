package com.lyhuoth.customerservice.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customer_segments")
public class CustomerSegmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = true)
    private String name;

    @Column(unique = true, nullable = false)
    private String code;

    @OneToMany(mappedBy = "segment")
    private List<CustomerEntity> customers;
}
