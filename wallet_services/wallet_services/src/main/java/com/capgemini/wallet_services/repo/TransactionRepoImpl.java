package com.capgemini.wallet_services.repo;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepoImpl {
    public Customer Register (Customer customer) throws ClassNotFoundException, SQLException {
        try {
            int rowscount=0;


            String registerinfo = "insert into User_details (firstname,lastname,username,pass,balance,date_created,time_created)"+" values('"+ customer.getFname() + "','" + customer.getLname() + "','" + customer.getUsername()
                    + "','" + customer.getPass()+ "','"+customer.getBalance()+ "','"+ LocalDate.now()+"','"+ LocalTime.now()+"')";

            connection = DBconnection.getDBConnection();
            statement = connection.createStatement();
            rowscount = statement.executeUpdate(registerinfo);



            if(rowscount > 0) {
                return customer;
                //System.out.println("Successfully Registered!");
            }
            else {
                System.out.println("Opss something went wrong.");
            }

        } catch (Exception e) {
            throw e;
        } finally {
            DBconnection.getDBConnectionClose();
        }
        return null;
    }

    public Customer login(String username,String pass) throws ClassNotFoundException, SQLException {
        Customer customer = new Customer();
        try {


            connection = DBconnection.getDBConnection();
            statement = connection.createStatement();
            String selectlogin = "Select * from user_details where username='" + username + "' and pass='" + pass +"'";

            ResultSet rs = statement.executeQuery(selectlogin);

            if(rs.next()) {
                customer.setAccountid(rs.getLong("accountid"));
                customer.setFname(rs.getString("firstname"));
                customer.setLname(rs.getString("lastname"));
                customer.setUsername(rs.getString("username"));
                customer.setPass(rs.getString("pass"));
                customer.setBalance(rs.getDouble("balance"));
                customer.setDatecreated(rs.getDate("date_created").toLocalDate());
                customer.setTimecreated(rs.getTime("time_created").toLocalTime());

                return customer;
            }

            else {
                System.out.println("no username and password");

            }

        } catch (Exception e) {
            throw e;
        } finally {
            DBconnection.getDBConnectionClose();

        }

        return null;
    }

    public Customer ShowAccount(Customer customer) throws ClassNotFoundException, SQLException {
        try {


            String show = "Select * from user_details where username = '"+customer.getUsername()+"'";

            connection = DBconnection.getDBConnection();
            statement = connection.createStatement();
            statement.executeQuery(show);

            ResultSet rs=statement.executeQuery(show);
            if(rs.next()) {
                customer.setAccountid(rs.getLong("accountid"));
                customer.setFname(rs.getString("firstname"));
                customer.setLname(rs.getString("lastname"));
                customer.setUsername(rs.getString("username"));
                customer.setPass(rs.getString("pass"));
                customer.setBalance(rs.getDouble("balance"));
                customer.setDatecreated(rs.getDate("date_created").toLocalDate());
                customer.setTimecreated(rs.getTime("time_created").toLocalTime());

                return customer;
            }

            else {
                System.out.println("no username and password");

            }
        }catch(ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        finally{
            statement.close();
            DBconnection.getDBConnectionClose();
        }
        return null;
    }

    public double DepositMoney(Double amount, Customer customer) throws ClassNotFoundException, SQLException {
        double updatedamount=0.0;
        double currentamount=0.0;


        try {
            int rowscount = 0;
            connection = DBconnection.getDBConnection();
            statement = connection.createStatement();

            double updateamount=customer.getBalance() + amount;

            String add = "UPDATE user_details SET BALANCE = '" + updateamount+ "' WHERE username = '"
                    + customer.getUsername()+ "'";
            rowscount = statement.executeUpdate(add);



            if(rowscount > 0) {

                System.out.println("Money Deposit Successfully");
                return updateamount;

            }

        }catch(ClassNotFoundException | SQLException e) {
            System.out.println(e);
            return 0.0;
        }
        finally{
            statement.close();
            DBconnection.getDBConnectionClose();

        }
        return 0.0;

    }
    public double WithdrawMoney(Double amount,Customer customer) throws ClassNotFoundException, SQLException {
        try {
            int rowscount = 0;
            connection = DBconnection.getDBConnection();
            statement = connection.createStatement();

            Double withdraw =customer.getBalance() - amount;

            String less = "UPDATE user_details SET BALANCE = '" + withdraw+ "' WHERE username = '"
                    + customer.getUsername()+ "'";


            statement.executeUpdate(less);
            rowscount = statement.executeUpdate(less);

            customer.setBalance(withdraw);


            if(rowscount > 0) {
                System.out.println("Money Withdraw Successfully");
                return withdraw;
            }
            else {
                System.out.println("Opss something went wrong.");
            }


        }catch(ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        finally{
            statement.close();
            DBconnection.getDBConnectionClose();
        }
        return 0.0;
    }
    public double TransferMoney(Long accountid, Double amount,Customer customer) throws ClassNotFoundException, SQLException {
        try {
            Transactions transaction = new Transactions();

            int rowscount = 0;
            connection = DBconnection.getDBConnection();
            statement = connection.createStatement();

            String add = "update user_details set balance = balance +'" +amount + "' where accountid = '" + accountid
                    + "'";

            Double lessamount =customer.getBalance() - amount;
            String less = "update user_details set balance = '" + lessamount + "' where accountid = '"
                    + customer.getAccountid()+ "'";


            statement.executeUpdate(add);
            statement.executeUpdate(less);
            rowscount = statement.executeUpdate(less);
            customer.setBalance(lessamount);

            if(rowscount > 0) {
                System.out.println("Money Transfered Successfully");
                return lessamount;
            }
            else {
                System.out.println("Opss something went wrong.");
            }


        }catch(ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        finally{
            statement.close();
            DBconnection.getDBConnectionClose();
        }

        return 0.0;

    }

    public List<Transactions> ViewLastransaction(Customer customer) throws ClassNotFoundException, SQLException {
        try {


            String show = "Select * from user_transaction where accountid = '"+customer.getAccountid()+"' order by transaction_date DESC, transactionid DESC LIMIT 10";

            connection = DBconnection.getDBConnection();
            statement = connection.createStatement();

            ResultSet resultSet=statement.executeQuery(show);


            List<Transactions> tList = new ArrayList<>();

            while (resultSet.next()) {
                Transactions transaction = new Transactions();
                transaction.setTransactionid(resultSet.getLong("transactionid"));
                transaction.setAccountid(resultSet.getLong("accountid"));
                transaction.setFname(resultSet.getString("firstname"));
                transaction.setLname(resultSet.getString("lastname"));
                transaction.setTransactiontype(resultSet.getString("transactiontype"));
                transaction.setAmount(resultSet.getDouble("amount"));
                transaction.setTotalbalance(resultSet.getDouble("TotalBalance"));
                transaction.setDate(resultSet.getDate("transaction_date").toLocalDate());
                transaction.setTime(resultSet.getTime("transaction_time").toLocalTime());

                tList.add(transaction);
            }
            return tList;

        }catch(ClassNotFoundException | SQLException e) {
            throw e;
        }
        finally{
            statement.close();
            DBconnection.getDBConnectionClose();
        }

    }

    public List<Transactions> Viewspecificdate(Customer customer,LocalDate searchdate) throws ClassNotFoundException, SQLException {
        try {


            String show = "Select * from user_transaction where accountid= '" + customer.getAccountid() +
                    "' AND transaction_date = '" + Date.valueOf(searchdate) + "'ORDER BY transaction_date DESC";

            connection = DBconnection.getDBConnection();
            statement = connection.createStatement();

            ResultSet resultSet=statement.executeQuery(show);


            List<Transactions> tList = new ArrayList<>();

            while (resultSet.next()) {
                Transactions transaction = new Transactions();
                transaction.setTransactionid(resultSet.getLong("transactionid"));
                transaction.setAccountid(resultSet.getLong("accountid"));
                transaction.setFname(resultSet.getString("firstname"));
                transaction.setLname(resultSet.getString("lastname"));
                transaction.setTransactiontype(resultSet.getString("transactiontype"));
                transaction.setAmount(resultSet.getDouble("amount"));
                transaction.setTotalbalance(resultSet.getDouble("TotalBalance"));
                transaction.setDate(resultSet.getDate("transaction_date").toLocalDate());
                transaction.setTime(resultSet.getTime("transaction_time").toLocalTime());


                tList.add(transaction);
            }
            return tList;

        }catch(ClassNotFoundException | SQLException e) {
            throw e;
        }
        finally{
            statement.close();
            DBconnection.getDBConnectionClose();
        }

    }


    public boolean CreateTransaction(Transactions transaction,Customer customer)throws ClassNotFoundException, SQLException {
        try {


            connection = DBconnection.getDBConnection();
            String query = "insert into User_transaction (transactionid,accountid,firstname,lastname,transactiontype,amount,totalbalance,transaction_date,transaction_time) values('"+ transaction.getTransactionid() + "','" +customer.getAccountid() + "','" + customer.getFname()
                    + "','" + customer.getLname() + "','"+transaction.getTransactiontype()+ "','"+transaction.getAmount()+ "','"+customer.getBalance()+"','"+LocalDate.now()+"','"+LocalTime.now()+"')";

            statement = connection.createStatement();
            statement.executeUpdate(query);

            return true;
        }catch(ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        finally{
            statement.close();
            DBconnection.getDBConnectionClose();
        }
        return false;
    }

}
