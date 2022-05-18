package com.capgemini.wallet_services.service;

import com.capgemini.wallet_services.entity.Account;
import com.capgemini.wallet_services.entity.Customer;
import com.capgemini.wallet_services.entity.Transaction;
import com.capgemini.wallet_services.repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService{


    @Autowired
    private TransactionRepo transactionRepo;

    @Override
    public List<Transaction> getAllTransaction() {
        return transactionRepo.findAll();
    }

    public List<Transaction> getTransactionListByAccount(Account account){
        return transactionRepo.findByAccount(account);
    };

//    public  List<Transaction> findByAccountAndType(Account account, String accountType){
//        return transactionRepo.findByAccountAndType(account, accountType);
//    }

    @Override
    public void saveTransaction(Transaction transaction) {
        this.transactionRepo.save(transaction);
    }

    @Override
    public Transaction getTransactionById(long transactionId) {
        Optional<Transaction> optional = transactionRepo.findById(transactionId);
        Transaction transaction = null;
        if (optional.isPresent()){
            transaction = optional.get();
        } else {
            throw new RuntimeException("Transaction not found for id :: " + transactionId);
        }
        return transaction;
    }

    @Override
    public void deleteTransactionById(long transactionId) {
        this.transactionRepo.deleteById(transactionId);
    }

    @Override
    public Page<Transaction> findPagination(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.transactionRepo.findAll(pageable);
    }


    public Customer Register (Customer customer)   throws ClassNotFoundException, SQLException {
	/*	conditionalLock=new ReentrantLock();
		condition=conditionalLock.newCondition(); */

        try {
            userdao.Register(customer);


        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        } catch(Exception e){
            throw e;
        }
        return null;
    }

    public Customer login(String username,String pass) throws ClassNotFoundException, SQLException {
        Customer customer=new Customer();
        try {

            customer=userdao.login(username,pass);


        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        } catch(Exception e) {
            throw e;
        }
        return customer;
    }
    public Customer ShowAccount(Customer customer)  throws ClassNotFoundException, SQLException {
        try {
            return userdao.ShowAccount(customer);


        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        } catch(Exception e){
            throw e;
        }
        return null;

    }

    public double DepositMoney(Double amount,Customer customer)  throws ClassNotFoundException, SQLException {

        try {



            return userdao.DepositMoney(amount,customer);

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return 0.0;
        }

    }
    public double WithdrawMoney(Double amount,Customer customer)  throws ClassNotFoundException, SQLException {
        try {


            return userdao.WithdrawMoney(amount,customer);

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        } catch(Exception e){
            throw e;
        }
        return 0.0;

    }

    public double TransferMoney(Long accountid,Double amount,Customer customer)  throws ClassNotFoundException, SQLException {
        try {
            return userdao.TransferMoney(accountid,amount,customer);



        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        } catch(Exception e){
            throw e;
        }
        return 0.0;

    }
    public List<Transactions>Viewspecificdate(Customer customer, LocalDate searchdate)  throws ClassNotFoundException, SQLException {
        List<Transactions> tList = null;
        try {

            tList=userdao.Viewspecificdate(customer,searchdate);
            displayTransaction(tList);


        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return tList;

    }
    public void displayTransaction(List <Transactions> tList) throws InterruptedException {
        System.out.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s \n",
                "ID","AccountID","Firstname","Lastname","Transactiontype","TotalBalance","Date");

        for(Transactions t : tList) {
            System.out.format("%-15d %-15d %-15s %-15s %-15s %-15f %-15s \n",
                    t.getTransactionId(),t.getAccountId(),t.getFname(),
                    t.getLname(),t.getTransactionType(),t.getTotalBalance(),t.getDate());
            try {
                Thread.sleep(500);
            }catch (InterruptedException e) {
                throw e;
            }
        }
    }
    public List<Transaction>ViewLastTransaction(Customer customer)  throws ClassNotFoundException, SQLException {
        List<Transaction> tList = null;
        try {
            tList = userdao.ViewLastransaction(customer);
            displayTransaction(tList);


        } catch(Exception e){
            System.out.println(e.getMessage());

        }
        return tList;
    }
    public boolean CreateTransaction(Transaction transaction,Customer customer)  throws ClassNotFoundException, SQLException {
        try {
            userdao.CreateTransaction(transaction,customer);

            return true;

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        } catch(Exception e){
            throw e;
        }
        return false;

    }

}
