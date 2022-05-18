package com.capgemini.wallet.main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.capgemini.wallet.bean.Customer;
import com.capgemini.wallet.bean.Transactions;
import com.capgemni.wallet.services.Wallet_Service;
import com.capgemni.wallet.services.Wallet_Service_Imp;

public class Main {

	public static void main (String [] args) throws IOException{

		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		boolean resultcheck=true;

		//SignIn-Register Menu
		while(resultcheck) {
			System.out.println("~.~.~.~.~.~.~.~.~.~.~.~.~");
			System.out.println("1.Sign In");
			System.out.println("2.Register");
			System.out.println("3.Exit");
			System.out.println("Enter your choice: ");
			System.out.println("~.~.~.~.~.~.~.~.~.~.~.~.~");

			try {
				int Mainresult = Integer.parseInt(input.readLine());

				if (Mainresult == 1) {
					SignIn();
					resultcheck=false;

				}
				else if(Mainresult==2) {
					Register();
					resultcheck=true;

				}
				else if (Mainresult>3) {
					System.out.println("Enter a valid number!");
					System.out.println("Enter your choice:");
				}
				else if(Mainresult==3) {
					System.out.println("Thank you!");
					resultcheck=false;
				}

			} catch (Exception e) {

				System.out.println("Please only enter a valid number!");
				System.out.println("Enter your choice:");
				resultcheck=false;
			} 
		}	
	}

	public static void Menu(Customer customer) throws IOException {
		BufferedReader menuinput = new BufferedReader(new InputStreamReader(System.in));

		//User Menu
		String username =customer.getUsername();
		System.out.println("~.~.~.~.~.~.~.~.~.~.~.~.~");
		System.out.println("Welcome "+username+"!");
		System.out.println("Account number: "+customer.getAccountid());
		System.out.println("1.Show Balance");
		System.out.println("2.Deposit Amount");
		System.out.println("3.WithDraw Amount");
		System.out.println("4.Transfer Amount");
		System.out.println("5.Search in specific date");
		System.out.println("6.View last 10 transaction");
		System.out.println("Enter your choice: ");
		System.out.println("~.~.~.~.~.~.~.~.~.~.~.~.~");
		int menu = Integer.parseInt(menuinput.readLine());


		if (menu==1) {
			Balance(customer);
		}
		else if (menu==2){
			Deposit(customer);
		}
		else if (menu==3) {
			Withdraw(customer);
		}
		else if (menu==4) {
			Transfer(customer);
		}
		else if(menu==5) {
			ViewSpecificDate(customer);
		}
		else if(menu==6) {
			ViewLastTransaction(customer);
		}
	}

	static void Register() throws IOException {
		BufferedReader Reginput = new BufferedReader(new InputStreamReader(System.in));

		try {	
			System.out.println("Enter your Firstname: ");
			String fname = Reginput.readLine();
			System.out.println("Enter your Lastname: ");
			String lname = Reginput.readLine();
			System.out.println("Username: ");
			String username = Reginput.readLine();
			System.out.println("Create your Password: ");
			String pass = Reginput.readLine();
			System.out.println("Enter initial deposit: ");
			Double balance = Double.parseDouble(Reginput.readLine());


			Customer customer = new Customer();
			Wallet_Service userservice = new Wallet_Service_Imp();
			customer.setFname(fname);
			customer.setLname(lname);
			customer.setUsername(username);
			customer.setPass(pass);
			customer.setBalance(balance);
			customer.setDatecreated(LocalDate.now());
			customer.setTimecreated(LocalTime.now());
			userservice.Register(customer);



		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	static void SignIn() throws IOException, ClassNotFoundException, SQLException {

		BufferedReader Signinput = new BufferedReader(new InputStreamReader(System.in));

		try {

			System.out.println("Enter your Username: ");
			String username = Signinput .readLine();
			System.out.println("Enter your Password: ");
			String pass = Signinput.readLine();
			
			Customer customer = new Customer();
			customer.setUsername(username);
			customer.setPass(pass);
			Wallet_Service userservices = new Wallet_Service_Imp();
			customer =userservices.login(username,pass);



			if(customer!=null) {
				//Calling menu method
				Menu(customer);	
			}

			else  {
				System.out.println("Try again!");
				SignIn();	
			}


		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	static void Balance(Customer customer) throws IOException {
//		BufferedReader Reginput = new BufferedReader(new InputStreamReader(System.in));

		try {	

			Wallet_Service userservice = new Wallet_Service_Imp();

			userservice.ShowAccount(customer);
			
//
//
//			if(result!=false) {
//				//Calling menu method
//
//				System.out.println("Do you want to continue? Y/N");
//				String yesno = Reginput.readLine();
//
//				if (yesno.equalsIgnoreCase("Y")) {
//					Menu(customer);		
//				}
//				else if (yesno.equalsIgnoreCase("N")) {
//					System.out.println("Thank you! Have A Nice Day!");
//					System.exit(0);
//				}
//			}
//
//			else  {
//				System.out.println("Try again!");
//			}

		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	static void Withdraw(Customer customer) throws IOException {
		BufferedReader Reginput = new BufferedReader(new InputStreamReader(System.in));

		try {	

			Transactions transaction = new Transactions();
			transaction.setTransactiontype("Withdraw");

			System.out.println("Enter your Amount: ");
			Double a = Double.parseDouble(Reginput.readLine());
			transaction.setAmount(a);

			Wallet_Service userservice = new Wallet_Service_Imp();
			userservice.WithdrawMoney(a,customer);
			userservice.CreateTransaction(transaction,customer);

//
//
//			if(result!=false) {
//				//Calling menu method
//
//				System.out.println("Do you want to continue? Y/N");
//				String yesno = Reginput.readLine();
//
//				if (yesno.equalsIgnoreCase("Y")) {
//					Menu(customer);		
//				}
//				else if (yesno.equalsIgnoreCase("N")) {
//					System.out.println("Thank you! Have A Nice Day!");
//					System.exit(0);
////				}}
//			
//
//			else  {
//				System.out.println("Try again!");
//			}

		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	static void Deposit(Customer customer) throws IOException {
		BufferedReader Reginput = new BufferedReader(new InputStreamReader(System.in));

		try {	

			Transactions transaction = new Transactions();
			transaction.setTransactiontype("Deposit");

			System.out.println("Enter your Amount: ");
			Double depositamount = Double.parseDouble(Reginput.readLine());

			transaction.setAmount(depositamount);
			Wallet_Service userservice = new Wallet_Service_Imp();
			userservice.DepositMoney(depositamount,customer);
			userservice.CreateTransaction(transaction,customer);




		
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	static void Transfer(Customer customer) throws IOException {
		BufferedReader Reginput = new BufferedReader(new InputStreamReader(System.in));

		try {	
			Transactions transaction = new Transactions();
			transaction.setTransactiontype("Transfer");

			System.out.println("Enter your AccountID: ");
			Long account = Long.parseLong(Reginput.readLine());
			System.out.println("Enter the Amount: ");
			Double accamount = Double.parseDouble(Reginput.readLine());
			transaction.setAmount(accamount);

			Wallet_Service userservice = new Wallet_Service_Imp();
			userservice.TransferMoney(account,accamount,customer);
			userservice.CreateTransaction(transaction,customer);

		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	static void ViewSpecificDate(Customer customer) throws IOException {
		BufferedReader Reginput = new BufferedReader(new InputStreamReader(System.in));

		try {	
			
			System.out.println("Enter the Date (mm/dd/yyyy): ");
			LocalDate date = LocalDate.parse(Reginput.readLine(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));

			Transactions transaction = new Transactions();
			Wallet_Service userservice = new Wallet_Service_Imp();

			userservice.Viewspecificdate(customer,date);
			userservice.CreateTransaction(transaction,customer);

			

		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	static void ViewLastTransaction(Customer customer) throws IOException {

		try {	

			Wallet_Service userservice = new Wallet_Service_Imp();
			userservice.ViewLastransaction(customer);


		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}





