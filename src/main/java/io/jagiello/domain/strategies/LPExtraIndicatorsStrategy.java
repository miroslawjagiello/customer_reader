package io.jagiello.domain.strategies;

import io.jagiello.domain.Customer;
import io.jagiello.domain.CustomerView;

import java.math.BigDecimal;
import java.math.RoundingMode;

/*
Dla klientów typu OP ?? - wyklucza się z 2 przypadkiem chodzi o LP??
a) w przypadku gdy roczne przychody są mniejsze lub równe 800000
      LMGZC = (roczne_przychody * wskaznik_2) / wskaznik_1 * 0.5
      DXCF = (wskaznik_1 * wskaznik_2) - (wskaznik_3 * 1.5)
b) w przypadku gdy roczne przychody są większe niż 800000
      LMGZC = (roczne_przychody * wskaznik_2) / wskaznik_1 * 0.1
      DXCF = (wskaznik_1 * wskaznik_2) - wskaznik_3
 */
public class LPExtraIndicatorsStrategy implements ExtraIndicatorsStrategy {

    @Override
    public BigDecimal countLmgzc(CustomerView customer){
        if(customer.getAnnualRevenues().compareTo(new BigDecimal(800000)) >= 0) {
            return customer.getAnnualRevenues().multiply(customer.getIndicator2()).divide(customer.getIndicator1().multiply(new BigDecimal(0.5)), RoundingMode.HALF_EVEN);
        } else {
            return customer.getAnnualRevenues().multiply(customer.getIndicator2()).divide(customer.getIndicator1().multiply(new BigDecimal(0.1)), RoundingMode.HALF_EVEN);
        }
    }

    @Override
    public BigDecimal countDxcf(CustomerView customer){
        if(customer.getAnnualRevenues().compareTo(new BigDecimal(800000)) >= 0){
            return (customer.getIndicator1().multiply(customer.getIndicator2())).subtract(customer.getIndicator3().multiply(new BigDecimal(1.5)));
        } else {
            return (customer.getIndicator1().multiply(customer.getIndicator2())).subtract(customer.getIndicator3());
        }
    }
}
