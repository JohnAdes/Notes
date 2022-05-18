import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { CustomerService } from 'src/app/customer.service';
import { Customer } from 'src/app/customer';


@Component({
  selector: 'app-withdraw',
  templateUrl: './withdraw.component.html',
  styleUrls: ['./withdraw.component.css']
})
export class WithdrawComponent implements OnInit {
customer:Customer = new Customer();
  constructor(private router: Router,private customerservice:CustomerService,private http:HttpClient) { 
  }
  ngOnInit(): void {
  }

 withdraw(balance: string) {

    let username=""+localStorage.getItem("username");
    this.customerservice.withdraw(balance,username).subscribe((depositdata:any)=>{

      this.customer.balance=depositdata;
      localStorage.setItem('balance',this.customer.balance.toString());
      alert("Withdraw Sucessfully!");



    });
  }
}