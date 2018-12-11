import {CurrentAccount} from './currentAccount';
import { Movement } from './movement';
import { MovementsComponent } from '../components/movements/movements.component';

export interface MovementsAccount {
    account: CurrentAccount;
    movements: Movement[];
} 