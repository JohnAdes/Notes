package com.capgemini.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.capgemini.wallet.bean.Customer;
import com.capgemini.wallet.bean.Transactions;
	

public interface Wallet_Dao {
	public Customer Register (Customer customer) throws ClassNotFoundException, SQLException;
	public Customer login(String username,String pass) throws ClassNotFoundException, SQLException;
	public double DepositMoney(Double amount,Customer customer) throws ClassNotFoundException, SQLException;
	public Customer ShowAccount(Customer customer) throws ClassNotFoundException, SQLException;
	public double TransferMoney(Long accountid,Double amount,  Customer customer)throws ClassNotFoundException, SQLException;
	public double WithdrawMoney(Double amount, Customer customer)throws ClassNotFoundException, SQLException;
	public List<Transactions> Viewspecificdate(Customer customer,LocalDate searchdate)throws ClassNotFoundException, SQLException;
	public List<Transactions> ViewLastransaction(Customer customer)throws ClassNotFoundException, SQLException;
	public boolean CreateTransaction(Transactions transaction, Customer customer)throws ClassNotFoundException, SQLException;
	
}


