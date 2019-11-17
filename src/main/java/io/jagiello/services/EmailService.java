package io.jagiello.services;

import io.jagiello.domain.Customer;

public interface EmailService {
    void sendEmail(Customer customer);
}
