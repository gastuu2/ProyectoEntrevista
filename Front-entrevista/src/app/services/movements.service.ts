import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
//import {Http, Headers} from '@angular/http';
import { CurrentAccount } from '../models/currentAccount';
//import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators';



@Injectable()
export class MovementsService{

    private idAccountSelected;


    public setAccountSelected(id){
        this.idAccountSelected=id;
    }

    public getAccountSelected(){
        return this.idAccountSelected;
    }

    constructor(private http: HttpClient){
        
        console.log("servico clientes up!");
    }

    getMovements(accountId){
        const params = new HttpParams().set('accountId', accountId);
       return  this.http.get('/api/getMovements', { params : params , responseType: 'json'});
    }
}