import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerService } from '../../customer.service';
import { HttpClient } from '@angular/common/http';
import { Transactions } from 'src/app/transactions';

@Component({
  selector: 'app-viewtransactionbydate',
  templateUrl: './viewtransactionbydate.component.html',
  styleUrls: ['./viewtransactionbydate.component.css']
})
export class ViewtransactionbydateComponent implements OnInit {
  public transactiondata:Transactions[] = [] as Transactions[];
  ifTransactionbydate:boolean=false;
  
  constructor(private router: Router,private customerservice:CustomerService,private http:HttpClient) { 
  }

  ngOnInit(): void {
  }

  transactionbydate(date:string){
    
    let accountid=Number(localStorage.getItem("accountid"));
    this.customerservice.transactionbydate(date,accountid).subscribe((transactiondata:any)=>{
    this.transactiondata=transactiondata;

    this.ifTransactionbydate=true;

    });

  }
}
