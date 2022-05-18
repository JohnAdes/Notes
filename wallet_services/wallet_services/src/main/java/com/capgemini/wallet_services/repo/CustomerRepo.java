package com.capgemini.wallet_services.repo;

import com.capgemini.wallet_services.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

//    Customer findByEmail(String email);
//    Customer findByPhoneNumber(String phoneNo);
//    Customer findByCustomerCId(Long id);

    public static String validateLogin(Customer customer)
    {
        String email=customer.getEmail();
        String password= customer.getPassword();
        if(email.equals("johnson")&&password.equals("Jadesh123"))
        {
            return "success";
        }
        else
        {
            return "failure";
        }
    }

}
