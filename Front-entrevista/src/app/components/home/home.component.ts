import { Component, OnInit } from '@angular/core';
import { CurrentAccount} from '../../models/currentAccount';
import { CurrentAccountService } from '../../services/currentAccount.service';
import { MovementsService } from '../../services/movements.service';
import { Router } from '@angular/router';
import { FormControl, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { map } from 'rxjs/operators';
import * as $ from 'jquery';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.sass']
})
export class HomeComponent implements OnInit {

  selectedCurrency: string;
  accountForm: FormGroup;
  
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
    this.accountForm = this.createFormGroup();
    this.updateTable();
  }

  ngOnInit() {
     
  }


  createFormGroup() {
    return new FormGroup({
        currency: new FormControl(),
        balance: new FormControl()
    });
  }

  createAccount(){
      if(!this.accountForm.valid){
        //$("#fieldsRequired").removeAttr('hidden');
        $("#fieldsRequired").fadeIn();
        setTimeout(() => {
          //$("#fieldsRequired").attr('hidden','true');
          $("#fieldsRequired").fadeOut();
        }, 1500);
        return;
      }
      const now= new Date();
      this.currentAccount= Object.assign({}, this.accountForm.value);
      this.currentAccountService.createAccount(this.currentAccount).subscribe(
        result => {
            this.updateTable();
            if(result === 'Ok'){
              $("#newAccountOk").fadeIn();
            }else{
              $("#newAccountFail").fadeIn();
            }
            setTimeout(() => {
              //$("#fieldsRequired").attr('hidden','true');
              $("#newAccountOk").fadeOut();
              $("#newAccountFail").fadeOut();
            }, 1500);
            this.updateTable();
        },
        error => {
            console.log("el error ", error );
        }
      );
      
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
