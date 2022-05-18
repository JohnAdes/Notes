package com.capgemini.wallet_services.repo;

import com.capgemini.wallet_services.entity.Account;
import com.capgemini.wallet_services.entity.Customer;
import com.capgemini.wallet_services.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccount(Account account);
//    List<Transaction> findByAccountAndType(Account account, String accountType);
public Customer Register (Customer customer) throws ClassNotFoundException, SQLException;
    public Customer login(String username,String pass) throws ClassNotFoundException, SQLException;
    public double DepositMoney(Double amount,Customer customer) throws ClassNotFoundException, SQLException;
    public Customer ShowAccount(Customer customer) throws ClassNotFoundException, SQLException;
    public double TransferMoney(Long accountId,Double amount,  Customer customer)throws ClassNotFoundException, SQLException;
    public double WithdrawMoney(Double amount, Customer customer)throws ClassNotFoundException, SQLException;
    public List<Transaction> ViewSpecificDate(Customer customer, LocalDate searchDate)throws ClassNotFoundException, SQLException;
    public List<Transaction> ViewLastTransaction(Customer customer)throws ClassNotFoundException, SQLException;
    public boolean CreateTransaction(Transaction transaction, Customer customer)throws ClassNotFoundException, SQLException;

}
