package io.jagiello.domain.strategies;

import io.jagiello.domain.CustomerView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

/*
Dla klientów typu AC_1:
a) w przypadku gdy działalność trwa dłużej niż 5 lat
        LMGZC = (roczne_przychody * wskaznik_2) / (wskaznik_1 * 0.3)
        DXCF = wskaznik_1*wskaznik_2 - wskaznik_3
b) w przypadku gdy działalność trwa nie dłużej niż 5 lat
        LMGZC = (roczne_przychody * wskaznik_2) / (wskaznik_1 * 0.8)
        DXCF = wskaznik_1*wskaznik_2 - wskaznik_3
*/
public class AC_1ExtraIndicatorsStrategy implements ExtraIndicatorsStrategy {


    @Override
    public BigDecimal countLmgzc(CustomerView customer){
        if(period(customer) > 5) {
            return customer.getAnnualRevenues().multiply(customer.getIndicator2()).divide(customer.getIndicator1().multiply(new BigDecimal(0.3)), RoundingMode.HALF_EVEN);
        } else {
            return customer.getAnnualRevenues().multiply(customer.getIndicator2()).divide(customer.getIndicator1().multiply(new BigDecimal(0.8)), RoundingMode.HALF_EVEN);
        }
    }

    @Override
    public BigDecimal countDxcf(CustomerView customer){
        return (customer.getIndicator1().multiply(customer.getIndicator2())).subtract(customer.getIndicator3());
    }

    private int period(CustomerView customer){
        Period period = Period.between(customer.getStartDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate(), LocalDate.now());
        return period.getYears();
    }
}
