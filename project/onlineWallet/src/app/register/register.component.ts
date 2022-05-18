import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerService } from '../customer.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private router: Router,private customerservice:CustomerService) { 


  }

  ngOnInit(): void {
  }

  register(firstname:string,lastname:string,username:string,password:string,balance:string){

    this.customerservice.register(firstname,lastname,username,password,Number(balance)).subscribe((response: any) => {
      this.router.navigate(['']);
      alert("success");
     
    }, (err) => console.log(err));


  }

  }

