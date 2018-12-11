import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
//import {Http, Headers} from '@angular/http';
import { CurrentAccount } from '../models/currentAccount';
//import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators';



@Injectable()
export class CurrentAccountService{

    private httpOptions = {
        headers: new HttpHeaders({
          'Content-Type':  'application/json',
          'responseType': 'text',
        })
    };  

    constructor(private http: HttpClient){
        
             console.log("servico clientes up!");
    }
    
    private currentAccount : CurrentAccount;


    createAccount(account){
        
        return  this.http.post<CurrentAccount>('/api/createAccount', account, this.httpOptions);
      // return  this.http.post('/api/createAccount', { params : params , responseType: 'json'});
    }

    deleteAccount(){


    }

    getAllAccounts(){

        return  this.http.get('/api/getAccounts', { responseType: 'json'});

    }



}  