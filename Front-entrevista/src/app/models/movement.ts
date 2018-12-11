export interface Movement{
    id : number;
    date: Date;
	movementType: string;
	account_id: number;
	description: string;
	amount: number;
}