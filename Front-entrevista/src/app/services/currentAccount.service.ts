import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { CurrentAccount } from '../models/currentAccount';




@Injectable()
export class CurrentAccountService{

    constructor(private http: HttpClient){
        
    }
    
    private currentAccount : CurrentAccount;


    createAccount(account){
        return  this.http.post('/api/createAccount', account, {responseType: 'text'});
    }

    deleteAccount(){


    }

    getAllAccounts(){
        return  this.http.get('/api/getAccounts', { responseType: 'json'});
    }



}  