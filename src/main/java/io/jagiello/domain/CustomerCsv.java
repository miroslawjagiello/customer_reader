package io.jagiello.domain;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerCsv  {
    @CsvBindByPosition(position = 0)
    private String id;
    @CsvBindByPosition(position = 1)
    private String name;
    @CsvBindByPosition(position = 2)
    private String region;
    @CsvBindByPosition(position = 3)
    private String industry;
    @CsvBindByPosition(position = 4)
    private String phoneNumber;
    @CsvBindByPosition(position = 5)
    private String email;
    @CsvBindByPosition(position = 6)
    private String startDate;
    @CsvBindByPosition(position = 7)
    private String type;
    @CsvBindByPosition(position = 8)
    private String annualRevenues;
    @CsvBindByPosition(position = 9)
    private String indicator1;
    @CsvBindByPosition(position = 10)
    private String indicator2;
    @CsvBindByPosition(position = 11)
    private String indicator3;
    @CsvBindByPosition(position = 12)
    private String flag;
}
