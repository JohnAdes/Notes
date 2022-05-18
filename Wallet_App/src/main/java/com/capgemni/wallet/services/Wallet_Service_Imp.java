package com.capgemni.wallet.services;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.capgemini.dao.Wallet_Dao;
import com.capgemini.dao.Wallet_Dao_Imp;
import com.capgemini.wallet.bean.Transactions;
import com.capgemini.wallet.bean.Customer;
import com.mysql.cj.x.protobuf.MysqlxExpect.Open.Condition;


public class Wallet_Service_Imp implements Wallet_Service {
	Wallet_Dao userdao = new Wallet_Dao_Imp();

	
	
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
	public List<Transactions>Viewspecificdate(Customer customer,LocalDate searchdate)  throws ClassNotFoundException, SQLException {
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
					t.getTransactionid(),t.getAccountid(),t.getFname(),
					t.getLname(),t.getTransactiontype(),t.getTotalbalance(),t.getDate());
			try {
				Thread.sleep(500);
			}catch (InterruptedException e) {
				throw e; 
			} 
		}
	}
	public List<Transactions>ViewLastransaction(Customer customer)  throws ClassNotFoundException, SQLException {
		List<Transactions> tList = null;
		try {
			tList = userdao.ViewLastransaction(customer);
			displayTransaction(tList);
			
	
		} catch(Exception e){
			System.out.println(e.getMessage());
			
		} 
		return tList;
	}
	public boolean CreateTransaction(Transactions transaction,Customer customer)  throws ClassNotFoundException, SQLException {
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

