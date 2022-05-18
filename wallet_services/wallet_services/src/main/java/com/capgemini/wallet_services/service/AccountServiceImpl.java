package com.capgemini.wallet_services.service;

import com.capgemini.wallet_services.entity.Account;
import com.capgemini.wallet_services.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {


    @Autowired
    private AccountRepo accountRepo;

    @Override
    public List<Account> getAllAccounts() {
        return accountRepo.findAll();
    }

    @Override
    public void saveAccount(Account account) {
        this.accountRepo.save(account);
    }

    @Override
    public Account getAccountById(long accountId) {
        Optional<Account> optional = accountRepo.findById(accountId);
        Account account= null;
        if (optional.isPresent()){
            account = optional.get();
        } else {
            throw new RuntimeException("Store not found for id :: " + accountId);
        }
        return account;
    }

    @Override
    public void deleteAccountById(long accountId) {
        this.accountRepo.deleteById(accountId);
    }

    @Override
    public Page<Account> findPagination(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.accountRepo.findAll(pageable);
    }
}
