package com.capgemini.wallet_services.service;

import com.capgemini.wallet_services.entity.Customer;
import com.capgemini.wallet_services.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    public String validateLogin(Customer customer)
    {
        return CustomerRepo.validateLogin(customer);
    }

}
