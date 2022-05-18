package com.capgemini.wallet_services.service;

import com.capgemini.wallet_services.entity.Account;
import com.capgemini.wallet_services.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AccountService {


    List<Account> getAllAccounts();
    void saveAccount(Account account);
    Account getAccountById(long accountId);
    void deleteAccountById(long accountId);
    Page<Account> findPagination(int pageNo, int pageSize, String sortField, String sortDirection);
}

