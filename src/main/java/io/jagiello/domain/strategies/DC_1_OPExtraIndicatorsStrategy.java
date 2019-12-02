package io.jagiello.domain.strategies;

import io.jagiello.domain.CustomerView;

import java.math.BigDecimal;
import java.math.RoundingMode;

/*
Dla klientów typu DC_1 i OP:
LMGZC = (roczne_przychody * wskaznik_2) / (wskaznik_1 * 0.9)
DXCF = wskaznik_1*wskaznik_2
 */
public class DC_1_OPExtraIndicatorsStrategy implements ExtraIndicatorsStrategy {

    @Override
    public BigDecimal countLmgzc(CustomerView customer){
        return customer.getAnnualRevenues().multiply(customer.getIndicator2())
                .divide(customer.getIndicator1().multiply(new BigDecimal(0.9)), RoundingMode.HALF_EVEN);
    }

    @Override
    public BigDecimal countDxcf(CustomerView customer){
        return customer.getIndicator1().multiply(customer.getIndicator2());
    }
}
