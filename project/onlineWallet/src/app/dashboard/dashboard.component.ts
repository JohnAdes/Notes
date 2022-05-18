import { LocationStrategy } from '@angular/common';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  balance: number = 0;
  username:string;
  ifDeposit: boolean = false;
  ifWithdraw: boolean = false;
  ifViewBalance: boolean = false;
  ifTransferMoney: boolean = false;
  ifViewTransaction: boolean = false;
  ifViewTransactionBySpecificDate: boolean = false;


  constructor() {
    this.balance = Number(localStorage.getItem("balance"));
    this.username=String(localStorage.getItem("username"));
  }

  ngOnInit(): void {
  }

  deposit() {
    this.ifDeposit = true;
    this.ifWithdraw = false;
    this.ifViewBalance = false;
    this.ifTransferMoney = false;
    this.ifViewTransaction =false;
    this.ifViewTransactionBySpecificDate = false;



  }
  withdraw() {
    this.ifWithdraw = true;
    this.ifDeposit = false;
    this.ifViewBalance = false;
    this.ifTransferMoney = false;
    this.ifViewTransaction =false;
    this.ifViewTransactionBySpecificDate = false;
  }
  viewbalance() {

    this.ifViewBalance = true;
    this.ifDeposit = false;
    this.ifWithdraw = false;
    this.ifTransferMoney = false;
    this.ifViewTransaction =false;
    this.ifViewTransactionBySpecificDate = false;
  }

  transfermoney() {
    this.ifTransferMoney = true;
    this.ifDeposit = false;
    this.ifWithdraw = false;
    this.ifViewBalance = false;
    this.ifViewTransaction =false;
    this.ifViewTransactionBySpecificDate = false;
  }
  viewtransactionbyspecificdate() {
    this.ifViewTransactionBySpecificDate = true;
    this.ifDeposit = false;
    this.ifWithdraw = false;
    this.ifViewBalance = false;
    this.ifViewTransaction =false;
    this.ifTransferMoney = false;
  }
  viewtransaction() {
    this.ifViewTransaction = true;
    this.ifDeposit = false;
    this.ifWithdraw = false;
    this.ifViewBalance = false;
    this.ifTransferMoney = false;
    this.ifViewTransactionBySpecificDate = false;
  }

  logout() {
    localStorage.clear;
  }
}
