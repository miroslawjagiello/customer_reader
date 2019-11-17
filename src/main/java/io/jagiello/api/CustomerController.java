package io.jagiello.api;

import io.jagiello.domain.Customer;
import io.jagiello.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class CustomerController {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers(){
        Iterable<Customer> customerIterable = customerRepository.findAll();
        List<Customer> customers = StreamSupport.stream(customerIterable.spliterator(), false)
                .collect(Collectors.toList());
        return customers;
    }
}
