package com.lyhuoth.customerservice.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String customerNumber;

    @Column(nullable = false)
    private  String firstname;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true)
    private String email;

    private String phoneNumber;

    private LocalDate dateOfBirth;

    @Column(precision = 15, scale = 2)
    private BigDecimal profitAbility = BigDecimal.ZERO;

    @Column(nullable = false)
    private LocalDateTime createAt;

    @Column(nullable = false)
    private LocalDateTime updateAt;

    @ManyToOne
    private CustomerSegmentEntity segment;

}
