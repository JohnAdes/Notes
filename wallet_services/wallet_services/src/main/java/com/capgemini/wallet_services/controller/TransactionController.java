package com.capgemini.wallet_services.controller;

import com.capgemini.wallet_services.entity.Account;
import com.capgemini.wallet_services.entity.Transaction;
import com.capgemini.wallet_services.service.AccountService;
import com.capgemini.wallet_services.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountService accountService;

    // Display list of stores
    @GetMapping("/success")
    public String viewAccountPage(Model model){
        model.addAttribute("account", new Account());
//        findPagination(1, "name", "asc", model);
        model.addAttribute("transactionList", new Transaction());
        return "success";
    }

    @GetMapping("/store/{id}")
    public String viewAccountTransactions(@PathVariable (value = "id") long id, Model model){
        Account account = accountService.getAccountById(id);
        List<Transaction> transactionList = transactionService.getTransactionListByAccount(account);
        model.addAttribute("account", account );
//        findPagination(1, "name", "asc", model);
        model.addAttribute("transactionList", transactionList );
        return "/success";
    }

    @GetMapping("/account/newTransaction/{id}")
    public String addAccountTransaction(@PathVariable (value = "id") long id, Model model){
        Account account = accountService.getAccountById(id);
        Transaction transaction = new Transaction();
      transaction.setAccount(account);
        model.addAttribute("account", account );
        model.addAttribute("transaction", transaction );
        return "/store/newTransaction";
    }

    @PostMapping("store/save")
    public String saveTransaction(@ModelAttribute("transaction")  Transaction transaction){
        transactionService.saveTransaction(transaction);
        Long sId = transaction.getAccount().getAccountId();
        return "redirect:/account/" + sId;

    }

    @GetMapping("/edit/{id}")
    public String editTransactionForm(@PathVariable (value = "id") long id, Model model){
        Transaction transaction = transactionService.getTransactionById(id);
        model.addAttribute("transaction", transaction );
        return "updateTransaction";
    }

    @GetMapping("/delete/{id}")
    public String deleteTransaction(@PathVariable (value = "id") long id, Transaction transaction){
        this.transactionService.deleteTransactionById(id);
        Long sId =  transaction.getAccount().getAccountId();
        return "redirect:/account/" + sId;
    }



    @GetMapping("/store/page/{pageNo}")
    public String findPagination(@PathVariable (value = "pageNo") int pageNo,
                                 @RequestParam("sortField") String sortField,
                                 @RequestParam("sortDirection") String sortDirection,
                                 Model model){
        int pageSize = 10;

        Page<Transaction> page = transactionService.findPagination(pageNo, pageSize, sortField, sortDirection);
        List<Transaction> transactionList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc":"asc");

        model.addAttribute("transactionList", transactionList);
        return "/success";

    }
}
