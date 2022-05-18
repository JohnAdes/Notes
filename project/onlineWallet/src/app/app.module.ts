import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ViewbalanceComponent } from './dashboard/viewbalance/viewbalance.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { DepositComponent } from './dashboard/deposit/deposit.component';
import { WithdrawComponent } from './dashboard/withdraw/withdraw.component';
import { TransfermoneyComponent } from './dashboard/transfermoney/transfermoney.component';
import { ViewtransactionbydateComponent } from './dashboard/viewtransactionbydate/viewtransactionbydate.component';
import { ViewtransactionComponent } from './dashboard/viewtransaction/viewtransaction.component';

import { RouterModule,Routes } from '@angular/router';
import { RouteGuard } from './route.guard';



const routes:Routes=[
  {path:'',component:LoginComponent},
  {path:'register',component:RegisterComponent},
  {path:'dashboard',component:DashboardComponent,canActivate:[RouteGuard]}
 
 
]

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    RegisterComponent,
    DepositComponent,
    WithdrawComponent,
    ViewbalanceComponent,
    TransfermoneyComponent,
    ViewtransactionbydateComponent,
    ViewtransactionComponent,
   
  

  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    [RouterModule.forRoot(routes)]

  ],
  exports:[RouterModule],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
