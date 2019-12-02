package io.jagiello.domain.mappers;

import io.jagiello.domain.CustomerCsv;
import io.jagiello.domain.CustomerView;
import io.jagiello.domain.strategies.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CustomerCsvToCustomerViewMapper {
    public static CustomerView toView(CustomerCsv csv) throws ParseException {
        ExtraIndicatorsStrategy strategy = new DefaultExtraIndicatorsStrategy();

        switch (csv.getType()){
            case "BS_1":
                strategy = new BS_1ExtraIndicatorsStrategy();
                break;
            case "DC_1":
            case "OP":
                strategy = new DC_1_OPExtraIndicatorsStrategy();
                break;
            case "AC_1":
                strategy = new AC_1ExtraIndicatorsStrategy();
                break;
            case "LP":
                strategy = new LPExtraIndicatorsStrategy();
                break;
        }

        CustomerView customerView = new CustomerView(
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
                strategy
        );
        return customerView;
    }
}
