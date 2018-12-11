package coop.tecso.examen.dto;

import coop.tecso.examen.model.Movement;

public class AddMovementRequestDto {
	
	private Long id;
	private Movement movement;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Movement getMovement() {
		return movement;
	}
	public void setMovement(Movement movement) {
		this.movement = movement;
	}
	
	

}
