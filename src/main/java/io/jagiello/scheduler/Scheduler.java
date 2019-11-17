package io.jagiello.scheduler;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import io.jagiello.domain.Customer;
import io.jagiello.domain.CustomerCsv;
import io.jagiello.domain.CustomerCsvToCustomerMapper;
import io.jagiello.repositories.CustomerRepository;
import io.jagiello.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

@Component
public class Scheduler {

    @Value("${file.name}")
    private String fileName;

    @Value("${file.localization}")
    private String fileLocalization;

    @Value("${file.buckup.localization}")
    private String fileBuckupLocalization;

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH.mm");

    private final CustomerRepository customerRepository;
    private final EmailService emailService;

    @Autowired
    public Scheduler(CustomerRepository customerRepository, EmailService emailService) {
        this.customerRepository = customerRepository;
        this.emailService = emailService;
    }

    @Scheduled(fixedDelayString = "${scheduled.fixedDelay}")
    public void executeJob() throws IOException, ParseException {
        File f = new File(fileLocalization + fileName);
        if(!(f.exists() && !f.isDirectory())) return;

        Reader reader = Files.newBufferedReader(Paths.get(fileLocalization + fileName));
        CsvToBean<CustomerCsv> csvToBean = new CsvToBeanBuilder(reader)
                .withType(CustomerCsv.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();

        Iterator<CustomerCsv> csvUserIterator = csvToBean.iterator();
        //skip headers rows
        csvUserIterator.next();

        while (csvUserIterator.hasNext()) {
            CustomerCsv customerCsv = csvUserIterator.next();
            Long customerId = Long.valueOf(customerCsv.getId());
            boolean newCustomer = customerRepository.findById(customerId).isEmpty();
            Customer customer = CustomerCsvToCustomerMapper.toModel(customerCsv);
            if(newCustomer){
                emailService.sendEmail(customer);
            }
            customerRepository.save(customer);
        }

        Files.move(Paths.get(fileLocalization + fileName),
                   Paths.get(fileBuckupLocalization + format.format(new Date()) + fileName));
    }

}
