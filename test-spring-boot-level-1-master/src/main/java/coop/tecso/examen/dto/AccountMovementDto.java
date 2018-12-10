package coop.tecso.examen.dto;

import coop.tecso.examen.model.Movement;

public class AccountMovementDto {
	
	private Long accountId;
	private Movement movement;
	
	
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public Movement getMovement() {
		return movement;
	}
	public void setMovement(Movement movement) {
		this.movement = movement;
	}
	
	
	

}
