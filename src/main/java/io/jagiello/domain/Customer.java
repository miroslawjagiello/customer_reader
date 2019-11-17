package io.jagiello.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    public Customer(Long id, String name, String region, String industry,
                    String phoneNumber, String email, Date startDate,
                    String type, BigDecimal annualRevenues,
                    BigDecimal indicator1, BigDecimal indicator2,
                    BigDecimal indicator3, BigDecimal flag,
                    ExtraIndicators extraIndicators) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.industry = industry;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.startDate = startDate;
        this.type = type;
        this.annualRevenues = annualRevenues;
        this.indicator1 = indicator1;
        this.indicator2 = indicator2;
        this.indicator3 = indicator3;
        this.flag = flag;
        this.extraIndicators = extraIndicators;
    }
}
