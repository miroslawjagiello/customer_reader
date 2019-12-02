package io.jagiello.domain.strategies;

import io.jagiello.domain.CustomerView;

import java.math.BigDecimal;
import java.math.RoundingMode;

/*
Dla klientów typu BS_1:
LMGZC = (roczne_przychody * wskaznik_2) / wskaznik_1
DXCF = wskaznik_1*wskaznik_2 - wskaznik_3
 */
public class BS_1ExtraIndicatorsStrategy implements ExtraIndicatorsStrategy {

    @Override
    public BigDecimal countLmgzc(CustomerView customer){
        return (customer.getAnnualRevenues().multiply(customer.getIndicator2()))
                .divide(customer.getIndicator1(), RoundingMode.HALF_EVEN);
    }

    @Override
    public BigDecimal countDxcf(CustomerView customer){
        return (customer.getIndicator1().multiply(customer.getIndicator2())).subtract(customer.getIndicator3());
    }
}
