package io.jagiello.domain.mappers;

import io.jagiello.domain.Customer;
import io.jagiello.domain.CustomerView;
import io.jagiello.domain.ExtraIndicators;

public class CustomerViewToCustomerMapper {
    public static Customer toModel(CustomerView customerView) {
        Customer customer = new Customer(
                customerView.getId(),
                customerView.getName(),
                customerView.getRegion(),
                customerView.getIndustry(),
                customerView.getPhoneNumber(),
                customerView.getEmail(),
                customerView.getStartDate(),
                customerView.getType(),
                customerView.getAnnualRevenues(),
                customerView.getIndicator1(),
                customerView.getIndicator2(),
                customerView.getIndicator3(),
                customerView.getFlag(),
                null
        );

        ExtraIndicators extraIndicators = new ExtraIndicators();
        extraIndicators.setCustomerId(customerView.getId());
        extraIndicators.setLmgzc(customerView.getLmgzc());
        extraIndicators.setDxcf(customerView.getDxcf());

        customer.setExtraIndicators(extraIndicators);
        return customer;
    }
}
