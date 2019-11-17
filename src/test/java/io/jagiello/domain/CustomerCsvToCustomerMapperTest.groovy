package io.jagiello.domain

import spock.lang.Specification

class CustomerCsvToCustomerMapperTest extends Specification {
    def "customer mapper correct generate external indicators"() {
        given:
        CustomerCsv customerCsv = new CustomerCsv();
        customerCsv.setId(_id)
        customerCsv.setStartDate(_startDate)
        customerCsv.setType(_type)
        customerCsv.setAnnualRevenues(_annualRevenues)
        customerCsv.setIndicator1(_indicator1)
        customerCsv.setIndicator2(_indicator2)
        customerCsv.setIndicator3(_indicator3)
        customerCsv.setFlag(_flag)

        when:
        Customer customer = CustomerCsvToCustomerMapper.toModel(customerCsv)
        ExtraIndicators extraIndicators = customer.getExtraIndicators()

        then:
        extraIndicators.getLmgzc() == _lmgzc
        extraIndicators.getDxcf() == _dxcf

        where:
        _id | _startDate    | _type   | _annualRevenues | _indicator1  | _indicator2 | _indicator3 | _flag   | _lmgzc  | _dxcf
        "1" |"2010-01-20"   | "BS_1"  | "990900"        | "412321.22"  | "0.032"     | "1200"      | "0100"  | 0.077   | 11994.27904
        "2" |"2010-01-20"   | "DC_1"  | "88990.12"      | "23111.22"   | "0.322"     | "1400"      | "1000"  | 1.37763 | 7441.81284
        "3" |"2010-12-01"   | "OP"    | "123423.43"     | "381731.22"  | "0.89"      | "1120"      | "0000"  | 0.3197  | 339740.7858

    }
}
