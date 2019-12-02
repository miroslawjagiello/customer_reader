package io.jagiello.domain;

import io.jagiello.domain.strategies.ExtraIndicatorsStrategy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class CustomerView {

    private Long id;
    private String name;
    private String region;
    private String industry;
    private String phoneNumber;
    private String email;
    private Date startDate;
    private String type;
    private BigDecimal annualRevenues;
    private BigDecimal indicator1;
    private BigDecimal indicator2;
    private BigDecimal indicator3;
    private BigDecimal flag;

    private ExtraIndicatorsStrategy extraIndicatorsStrategy;

    public BigDecimal getLmgzc(){
        return extraIndicatorsStrategy.countLmgzc(this);
    }
    public BigDecimal getDxcf(){
        return extraIndicatorsStrategy.countDxcf(this);
    }

}
