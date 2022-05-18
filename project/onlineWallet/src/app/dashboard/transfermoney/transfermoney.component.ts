import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerService } from '../../customer.service';
import { HttpClient } from '@angular/common/http';
import { Customer } from 'src/app/customer';

@Component({
  selector: 'app-transfermoney',
  templateUrl: './transfermoney.component.html',
  styleUrls: ['./transfermoney.component.css']
})
export class TransfermoneyComponent implements OnInit {

  customer: Customer = new Customer();
  constructor(private router: Router, private customerservice: CustomerService, private http: HttpClient) {


  }

  ngOnInit(): void {
  }
 
  transfer(accountid: string, amount: string) {
    let aid = Number(accountid);
    let amt = Number(amount);

    let username = "" + localStorage.getItem("username");
    this.customerservice.transfer(aid, amt, username).subscribe((depositdata: any) => {

      this.customer.balance = depositdata;
      localStorage.setItem('balance', this.customer.balance.toString());
      alert("Transfered Sucessfully!");

    }


    );



  }
}