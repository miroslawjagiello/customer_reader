package io.jagiello.domain

import io.jagiello.domain.mappers.CustomerCsvToCustomerViewMapper
import spock.lang.Specification

class CustomerCsvToCustomerViewMapperTest extends Specification {
    def "CustomerView  mapper correct generate external indicators"() {
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
        CustomerView customerView = CustomerCsvToCustomerViewMapper.toView(customerCsv)

        then:
        customerView.getLmgzc() == _lmgzc
        customerView.getDxcf() == _dxcf

        where:
        _id | _startDate    | _type   | _annualRevenues | _indicator1  | _indicator2 | _indicator3 | _flag   || _lmgzc  | _dxcf
        "1" |"2010-01-20"   | "BS_1"  | "990900"        | "412321.22"  | "0.032"     | "1200"      | "0100"  || 0.077   | 11994.27904
        "2" |"2010-01-20"   | "DC_1"  | "88990.12"      | "23111.22"   | "0.322"     | "1400"      | "1000"  || 1.37763 | 7441.81284
        "3" |"2010-12-01"   | "OP"    | "123423.43"     | "381731.22"  | "0.89"      | "1120"      | "0000"  || 0.3197  | 339740.7858
        "4" |"2009-11-01"   | "LP"    | "12342.43"      | "38121.22"   | "0.921"     | "1192"      | "0100"  || 2.98190 | 33917.64362
        "5" |"2009-11-01"   | "DC_2"  | "623423.98"     | "32423.23"   | "0.212"     | "1542"      | "1110"  || 4.07627 | 6873.72476
        "6" |"2002-11-01"   | "AC_1"  | "6232400"       | "238838.22"  | "0.323"     | "1123"      | "1011"  || 28.095  | 76021.74506
        "7" |"2006-11-02"   | "DC_2"  | "543212.32"     | "1213422.91" | "0.983"     | "1121"      | "1001"  || 0.44006 | 1192794.72053
        "8" |"2012-09-04"   | "AC_1"  | "743212.67"     | "121422.34"  | "0.223"     | "1902"      | "1100"  || 4.54986 | 25175.18182
        "9" |"2012-09-04"   | "OP"    | "108512.11"     | "12422.34"   | "0.802"     | "1123"      | "1011"  || 7.78407 | 9962.71668
        "10"|"2012-09-04"   | "DC_2"  | "278912.67"     | "12192.22"   | "0.21"      | "1012"      | "1001"  || 4.8040  | 2560.3662

    }
}
