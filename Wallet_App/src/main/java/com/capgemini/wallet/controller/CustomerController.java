package com.capgemini.wallet.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.capgemini.wallet.bean.Customer;
import com.capgemini.wallet.bean.Transactions;
import com.capgemni.wallet.services.Wallet_Service;
import com.capgemni.wallet.services.Wallet_Service_Imp;

@Path("/customer")
public class CustomerController {
	private Wallet_Service service = new Wallet_Service_Imp();

	@Path("/login/{username}/{pass}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)

	public Customer login(@PathParam("username")String username, @PathParam("pass")String pass) throws Exception {

		return service.login(username,pass);
	}

	@Path("/register")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Customer Register(Customer customer) throws Exception {
		return service.Register(customer);
	}


	@Path("/showAccount")
	@POST
	@Produces(MediaType.APPLICATION_JSON)

	public Customer ShowAccount(Customer customer) throws Exception {
		return service.ShowAccount(customer);

	}

	@Path("/deposit/{amount}/{username}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)

	public double DepositMoney(@PathParam("amount")Double amount, @PathParam("username")String username) throws Exception {
		Transactions transaction = new Transactions();
		transaction.setTransactiontype("Deposit");
		transaction.setAmount(amount);

		Customer customer = new Customer();
		customer.setUsername(username);
		customer=service.ShowAccount(customer);
		
		service.CreateTransaction(transaction, customer);
		return service.DepositMoney(amount,customer);

	}


	@Path("/withdraw/{amount}/{username}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public double WithdrawMoney(@PathParam("amount")Double amount, @PathParam("username")String username) throws Exception
	{
		//customer = login("aya", "123");

		Transactions transaction = new Transactions();
		transaction.setTransactiontype("Withdraw");
		transaction.setAmount(amount);

		Customer customer = new Customer();
		customer.setUsername(username);
		customer=service.ShowAccount(customer);

		service.CreateTransaction(transaction, customer);
		return service.WithdrawMoney(amount,customer);
	}

	@Path("/transferMoney/{accountid}/{amount}/{username}")
	@GET
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public double transferMoney(@PathParam("accountid") Long accountid, @PathParam("amount")Double amount,@PathParam("username")String username) throws Exception
	{
		//customer = login("aya", "123");
		Transactions transaction = new Transactions();
		transaction.setTransactiontype("Transfer");
		transaction.setAmount(amount);

		Customer customer = new Customer();
		customer.setUsername(username);
		customer=service.ShowAccount(customer);

		service.CreateTransaction(transaction, customer);
		return service.TransferMoney(accountid, amount, customer);
	}
	@Path("/viewTransaction")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public List<Transactions> ViewLastransaction(Long accountid) throws Exception{
		//customer = login("aya", "123");
		Customer customer = new Customer();
		customer.setAccountid(accountid);
		return service.ViewLastransaction(customer);
	}

	@Path("/transDateView/{accountid}/{date}")
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<Transactions> Viewspecificdate( @PathParam("accountid")Long accountid, @PathParam("date")String date) throws Exception{
		Customer customer= new Customer();
		customer.setAccountid(accountid);
		
		return service.Viewspecificdate(customer, LocalDate.parse(date));
	}

}