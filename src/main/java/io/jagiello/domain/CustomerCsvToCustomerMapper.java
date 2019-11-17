package io.jagiello.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

public class CustomerCsvToCustomerMapper {
    public static Customer toModel(CustomerCsv csv) throws ParseException {
        Customer customer = new Customer(
                Long.valueOf(csv.getId()),
                csv.getName(),
                csv.getRegion(),
                csv.getIndustry(),
                csv.getPhoneNumber(),
                csv.getEmail(),
                new SimpleDateFormat("yyyy-MM-dd").parse(csv.getStartDate()),
                csv.getType(),
                new BigDecimal(csv.getAnnualRevenues()),
                new BigDecimal(csv.getIndicator1()),
                new BigDecimal(csv.getIndicator2()),
                new BigDecimal(csv.getIndicator3()),
                new BigDecimal(csv.getFlag()),
                null
        );

        ExtraIndicators extraIndicators = new ExtraIndicators();
        extraIndicators.setCustomerId(Long.valueOf(csv.getId()));
        switch (csv.getType()){
            //Dla klientów typu BS_1:
            //LMGZC = (roczne_przychody * wskaznik_2) / wskaznik_1
            //DXCF = wskaznik_1*wskaznik_2 - wskaznik_3
            case "BS_1":
                extraIndicators.setLmgzc((customer.getAnnualRevenues().multiply(customer.getIndicator2())).divide(customer.getIndicator1(), RoundingMode.HALF_EVEN));
                extraIndicators.setDxcf((customer.getIndicator1().multiply(customer.getIndicator2())).subtract(customer.getIndicator3()));
                break;

            //Dla klientów typu DC_1 i OP:
            //LMGZC = (roczne_przychody * wskaznik_2) / (wskaznik_1 * 0.9)
            //DXCF = wskaznik_1*wskaznik_2
            case "DC_1":
            case "OP":
                extraIndicators.setLmgzc(customer.getAnnualRevenues().multiply(customer.getIndicator2()).divide(customer.getIndicator1().multiply(new BigDecimal(0.9)), RoundingMode.HALF_EVEN));
                extraIndicators.setDxcf(customer.getIndicator1().multiply(customer.getIndicator2()));
                break;

            //Dla klientów typu AC_1:
            case "AC_1":
                Period period = Period.between(customer.getStartDate().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate(),LocalDate.now());
                int diff = period.getYears();
            //a) w przypadku gdy działalność trwa dłużej niż 5 lat
            //        LMGZC = (roczne_przychody * wskaznik_2) / (wskaznik_1 * 0.3)
            //        DXCF = wskaznik_1*wskaznik_2 - wskaznik_3
                if(diff > 5){
                    extraIndicators.setLmgzc(customer.getAnnualRevenues().multiply(customer.getIndicator2()).divide(customer.getIndicator1().multiply(new BigDecimal(0.3)), RoundingMode.HALF_EVEN));
                }
            //b) w przypadku gdy działalność trwa nie dłużej niż 5 lat
            //        LMGZC = (roczne_przychody * wskaznik_2) / (wskaznik_1 * 0.8)
            //        DXCF = wskaznik_1*wskaznik_2 - wskaznik_3
                else{
                    extraIndicators.setLmgzc(customer.getAnnualRevenues().multiply(customer.getIndicator2()).divide(customer.getIndicator1().multiply(new BigDecimal(0.8)), RoundingMode.HALF_EVEN));
                }
                extraIndicators.setDxcf((customer.getIndicator1().multiply(customer.getIndicator2())).subtract(customer.getIndicator3()));
                break;

            //TODO wyklucza się z 2 przypadkiem chodzi o LP??
            //Dla klientów typu OP ?? - wyklucza się z 2 przypadkiem chodzi o LP??
            case "LP":
            //a) w przypadku gdy roczne przychody są mniejsze lub równe 800000
            //      LMGZC = (roczne_przychody * wskaznik_2) / wskaznik_1 * 0.5
            //      DXCF = (wskaznik_1 * wskaznik_2) - (wskaznik_3 * 1.5)
                if(customer.getAnnualRevenues().compareTo(new BigDecimal(800000)) >= 0){
                    extraIndicators.setLmgzc(customer.getAnnualRevenues().multiply(customer.getIndicator2()).divide(customer.getIndicator1().multiply(new BigDecimal(0.5)), RoundingMode.HALF_EVEN));
                    extraIndicators.setDxcf((customer.getIndicator1().multiply(customer.getIndicator2())).subtract(customer.getIndicator3().multiply(new BigDecimal(1.5))));
                }
            //b) w przypadku gdy roczne przychody są większe niż 800000
            //      LMGZC = (roczne_przychody * wskaznik_2) / wskaznik_1 * 0.1
            //      DXCF = (wskaznik_1 * wskaznik_2) - wskaznik_3
                else{
                    extraIndicators.setLmgzc(customer.getAnnualRevenues().multiply(customer.getIndicator2()).divide(customer.getIndicator1().multiply(new BigDecimal(0.1)), RoundingMode.HALF_EVEN));
                    extraIndicators.setDxcf((customer.getIndicator1().multiply(customer.getIndicator2())).subtract(customer.getIndicator3()));
                }
                break;
            //Dla pozostałych typów
            //      LMGZC = (roczne_przychody * wskaznik_2) / wskaznik_1
            //      DXCF = wskaznik_1*wskaznik_2
            default:
                extraIndicators.setLmgzc((customer.getAnnualRevenues().multiply(customer.getIndicator2())).divide(customer.getIndicator1(), RoundingMode.HALF_EVEN));
                extraIndicators.setDxcf(customer.getIndicator1().multiply(customer.getIndicator2()));
                break;

        }
        customer.setExtraIndicators(extraIndicators);
        return customer;
    }
}
