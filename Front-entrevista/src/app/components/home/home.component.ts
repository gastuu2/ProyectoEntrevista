import { Component, OnInit } from '@angular/core';
import { CurrentAccount} from '../../models/currentAccount';
import { CurrentAccountService } from '../../services/currentAccount.service';
import { MovementsService } from '../../services/movements.service';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.sass']
})
export class HomeComponent implements OnInit {

  selectedCurrency: string;
  
  private currentAccount ={
    balance: 0,
    currency: "",
    creationTimestamp: Date,
    modificationTimestamp: Date
  }

  private currentAccounts:  CurrentAccount [] = [];

  
  currencies = [
    { name: "PESOS", value: 1 },
    { name: "DOLARES", value: 2 },
    { name: "EUROS", value: 3 }
  ]

  constructor(private currentAccountService: CurrentAccountService, private router: Router,
              private movementsService: MovementsService) { 
   
  }

  ngOnInit() {
     this.updateTable();
  }

  createAccount(){
      this.currentAccountService.createAccount(this.currentAccount).pipe(map(res => res as string)).subscribe(
        result => {
            console.log(result);
            this.updateTable();
        }
      );
      this.updateTable();
  }

  updateTable(){
      this.currentAccountService.getAllAccounts().pipe(map(res => res as CurrentAccount[])).subscribe(
        result => {
            this.currentAccounts= result;
        }
      );
  }

  getMovements(accountId){
      this.movementsService.setAccountSelected(accountId);
      this.router.navigate(['/movements']);
  
  }

}
