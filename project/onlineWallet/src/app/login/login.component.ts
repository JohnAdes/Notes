
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Customer } from '../customer';
import { CustomerService } from '../customer.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  public customer:Customer;
  constructor(private router: Router,private customerservice:CustomerService) {
    localStorage.clear();
    this.customer=new Customer();
  }

  ngOnInit(): void {
  }

  login(username: string, password: string) {
    this.customerservice.login(username, password).subscribe((response: any) => {
      if (response != null) {
        this.customer = response;
        console.log(this.customer);
        localStorage.setItem('accountid', this.customer.accountid.toString());
        localStorage.setItem('fname', this.customer.fname);
        localStorage.setItem('lname', this.customer.lname);
        localStorage.setItem('username', this.customer.username);
        localStorage.setItem('pass', this.customer.pass);
        localStorage.setItem('balance', this.customer.balance.toString());
        localStorage.setItem('date_created', this.customer.Date_created);
        localStorage.setItem('time_created', this.customer.Time_created);
        this.router.navigate(['/dashboard']);
        // this.check = true;
        alert("Login Successfully!")
      }
      else {
        alert("Incorrect details! Try Again!");
      }
    }, (err) => console.log(err));



  }

}
  

