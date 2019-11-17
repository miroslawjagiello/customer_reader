package io.jagiello.services;

import io.jagiello.domain.Customer;
import io.jagiello.domain.Email;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class EmailServiceMock implements EmailService {

    private String fromEmail = "customer.reader@cr.pl";

    private Set<String> toBS_1 = Set.of("opiekunbs1@fikcyjnymailkorporacyjny.pl");
    private Set<String> toDC_2 = Set.of("opiekundc2@fikcyjnymailkorporacyjny.pl",
                                        "depart.zarzadz.dc2@fikcyjnymailkorporacyjny.pl");
    private Set<String> toAC_1 = Set.of("opiekunac1@fikcyjnymailkorporacyjny.pl",
                                        "waznypandyrektor@fikcyjnymailkorporacyjny.pl");
    private Set<String> toOP = Set.of("wysokimanager.op@fikcyjnymailkorporacyjny.pl");

    private Set<String> types = Set.of("BS_1","DC_2","AC_1","OP");

    @Override
    public void sendEmail(Customer customer) {

        if(!types.contains(customer.getType())) return;

        Email email = new Email();
        email.setFrom(fromEmail);

        Set<String> toEmail = null;
        switch (customer.getType()){
            case "BS_1":
                toEmail = toBS_1;
                break;
            case "DC_2":
                toEmail = toDC_2;
                break;
            case "AC_1":
                toEmail = toAC_1;
                break;
            case "OP":
                toEmail = toOP;
                break;
        }
        email.setTo(toEmail);
        email.setTitle(createTitle(customer));
        email.setBody(createBody(customer));

        System.out.println("============EMAIL SEND============");
        System.out.println(email);
        System.out.println("==================================");
    }

    private String createTitle(Customer customer){
        return String.format("Dodano nowego klienta: %s", customer.getName());
    }

    private String createBody(Customer customer){
        return String.format("Dodano nowego klienta: %s", customer.getName());
    }
}
