package coop.tecso.examen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import coop.tecso.examen.model.Movement;
import coop.tecso.examen.service.MovementService;

@RestController
public class MovementsController {

	@Autowired
	MovementService movementService;
	
	
	
	@GetMapping("/getMovements")
	public List<Movement> getMovementsByAccount(Long accountId){
		List<Movement> movements= movementService.getMovementsFromAccount(accountId);
		
		return movements;
		
	}
	
	
}
