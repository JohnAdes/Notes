import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { CustomerService } from 'src/app/customer.service';

@Component({
  selector: 'app-viewbalance',
  templateUrl: './viewbalance.component.html',
  styleUrls: ['./viewbalance.component.css']
})
export class ViewbalanceComponent implements OnInit {
  balance:number=0;
  constructor(private router: Router,private customerservice:CustomerService,private http:HttpClient) { 
    this.balance=Number(localStorage.getItem("balance"));


  }

  ngOnInit(): void {
  }

}
