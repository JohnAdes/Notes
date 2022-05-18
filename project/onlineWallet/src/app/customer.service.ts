import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customer } from './customer';
import { Transactions } from './transactions';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  constructor(private httpClient: HttpClient) { }

  public login(username: string, pass: string): Observable<Customer> {
    return this.httpClient.get<Customer>('http://localhost:8082/Wallet_App/customer/login/' + username + '/' + pass);

  }
  public register(firstname: string, lastname: string, username: string, password: string, balance: number): Observable<Customer> {
    return this.httpClient.post<Customer>('http://localhost:8082/Wallet_App/customer/register', {

      "fname": firstname,
      "lname": lastname,
      "username": username,
      "pass": password,
      "balance": balance

    });

  }


  public deposit(balance: string, username: string): Observable<Customer> {

    return this.httpClient.get<Customer>('http://localhost:8082/Wallet_App/customer/deposit/' + balance + '/' + username);

  }
  public withdraw(balance: string, username: string): Observable<Customer> {

    return this.httpClient.get<Customer>('http://localhost:8082/Wallet_App/customer/withdraw/' + balance + '/' + username);

  }

  public transfer(accountid: number, amount: number, username: string): Observable<Customer> {

    return this.httpClient.get<Customer>('http://localhost:8082/Wallet_App/customer/transferMoney/' + accountid + '/' + amount + '/' + username);

  }
  public transaction(accountid:number): Observable <Transactions[]>{
    return this.httpClient.post<Transactions[]>('http://localhost:8082/Wallet_App/customer/viewTransaction',accountid);
  }

  public transactionbydate(date:string, accountid:number):Observable <Transactions[]>{
  return this.httpClient.get<Transactions[]>('http://localhost:8082/Wallet_App/customer/transDateView/' + accountid +'/'+date);
  }

}
