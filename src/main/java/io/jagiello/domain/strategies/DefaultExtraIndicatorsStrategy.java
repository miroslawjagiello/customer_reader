package io.jagiello.domain.strategies;

import io.jagiello.domain.CustomerView;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DefaultExtraIndicatorsStrategy implements ExtraIndicatorsStrategy {

    @Override
    public BigDecimal countLmgzc(CustomerView customer){
        return (customer.getAnnualRevenues().multiply(customer.getIndicator2()))
                .divide(customer.getIndicator1(), RoundingMode.HALF_EVEN);
    }

    @Override
    public BigDecimal countDxcf(CustomerView customer){
        return customer.getIndicator1().multiply(customer.getIndicator2());
    }
}
