package com.capgemini.wallet_services.controller;

import com.capgemini.wallet_services.entity.Customer;
import com.capgemini.wallet_services.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;


    @GetMapping("/login")
    public String loginPage(Model model) {
        return "login";
    }

    @RequestMapping(value="/validateLogin.html",method=RequestMethod.POST)
    public ModelAndView validateLogin(@RequestParam("email")String email, @RequestParam("pwd")String pwd)
    {
        Customer customer =new Customer();
        customer.setEmail(email);
        customer.setPassword(pwd);
        String results=customerService.validateLogin(customer);
        ModelAndView modelandView=new ModelAndView();
        if(results.equals("success"))
        {
            modelandView.setViewName("success.html");
            modelandView.addObject("message","Welcome: "+email);
        }else
        {
            modelandView.setViewName("failure.html");
            modelandView.addObject("errorMessage","Please Login again with valid Credentials");
        }
        return modelandView;

    }

}
