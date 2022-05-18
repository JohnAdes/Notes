package com.capgemni.wallet.services;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.capgemini.wallet.bean.Transactions;
import com.capgemini.wallet.bean.Customer;


public interface Wallet_Service {

	public Customer Register (Customer customer)   throws ClassNotFoundException, SQLException;
	public Customer login(String username,String pass) throws ClassNotFoundException, SQLException;
	public double DepositMoney(Double amount,Customer customer) throws ClassNotFoundException, SQLException;
	public Customer ShowAccount(Customer customer)throws ClassNotFoundException, SQLException;
	public double TransferMoney(Long accountid,Double amount,  Customer customer)throws ClassNotFoundException, SQLException;
	public double WithdrawMoney(Double amount, Customer customer)throws ClassNotFoundException, SQLException;
	public List<Transactions>Viewspecificdate(Customer customer,LocalDate searchdate)throws ClassNotFoundException, SQLException;
	public List<Transactions> ViewLastransaction(Customer customer)throws ClassNotFoundException, SQLException;
	public boolean CreateTransaction(Transactions transaction,Customer customer)throws ClassNotFoundException, SQLException;
}

