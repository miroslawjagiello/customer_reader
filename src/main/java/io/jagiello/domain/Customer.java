package io.jagiello.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Customer {

    @Id
    private Long id;
    private String name;
    private String region;
    private String industry;
    private String phoneNumber;
    private String email;
    private Date startDate;
    private String type;

    @Column(scale = 2)
    private BigDecimal annualRevenues;

    @Column(scale = 2)
    private BigDecimal indicator1;

    @Column(scale = 3)
    private BigDecimal indicator2;

    private BigDecimal indicator3;

    private BigDecimal flag;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "customer_id")
    private ExtraIndicators extraIndicators;

}
