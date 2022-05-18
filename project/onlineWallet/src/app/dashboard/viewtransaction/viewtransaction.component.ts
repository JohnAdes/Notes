import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerService } from '../../customer.service';
import { HttpClient } from '@angular/common/http';
import { Transactions } from 'src/app/transactions';

@Component({
  selector: 'app-viewtransaction',
  templateUrl: './viewtransaction.component.html',
  styleUrls: ['./viewtransaction.component.css']
})
export class ViewtransactionComponent implements OnInit {
public transactiondata:Transactions[] = [] as Transactions[];

ifTransaction:boolean=false;

  constructor(private router: Router,private customerservice:CustomerService,private http:HttpClient) { 


  }

  ngOnInit(): void {
  }

  transaction(){

    let accountid=Number(localStorage.getItem("accountid"));
    this.customerservice.transaction(accountid).subscribe((transactiondata:any)=>{
    this.transactiondata=transactiondata;

    this.ifTransaction=true;

    });

    
  }
}
