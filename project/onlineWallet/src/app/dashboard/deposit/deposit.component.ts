import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerService } from '../../customer.service';
import { HttpClient } from '@angular/common/http';
import { Customer } from 'src/app/customer';

@Component({
  selector: 'app-deposit',
  templateUrl: './deposit.component.html',
  styleUrls: ['./deposit.component.css']
})
export class DepositComponent implements OnInit {

  customer:Customer=new Customer(); 
  constructor(private router: Router, private customerservice: CustomerService, private http: HttpClient) {


  }

  ngOnInit(): void {
  }

  deposit(balance: string) {

    let username=""+localStorage.getItem("username");
    this.customerservice.deposit(balance,username).subscribe((depositdata:any)=>{

      this.customer.balance=depositdata;
      localStorage.setItem('balance',this.customer.balance.toString());
      alert("Deposit Sucessfully!");



    });



  }

}