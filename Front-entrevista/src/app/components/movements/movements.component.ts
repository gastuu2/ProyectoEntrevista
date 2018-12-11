import { Component, OnInit } from '@angular/core';
import { Movement} from '../../models/movement';
import { MovementsAccount} from '../../models/movementsAccount';
import { CurrentAccountService } from '../../services/currentAccount.service';
import { MovementsService } from '../../services/movements.service';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';


@Component({
  selector: 'app-movements',
  templateUrl: './movements.component.html',
  styleUrls: ['./movements.component.scss']
})
export class MovementsComponent implements OnInit {

  private movementsAccounts:  MovementsAccount [] = [];
  private newMovement:  Movement ;
  constructor(private movementsService: MovementsService ) { 

  }

  ngOnInit() {
    this.getMovements();
  }

  getMovements(){
    this.movementsService.getMovements(this.movementsService.getAccountSelected())
    .pipe(map(res => res as MovementsAccount[])).subscribe(
      result => {
          this.movementsAccounts= result;
          console.log(this.movementsAccounts);
      });
  }

}
