package coop.tecso.examen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import coop.tecso.examen.dto.AccountMovementDto;
import coop.tecso.examen.model.CurrentAccount;
import coop.tecso.examen.model.Movement;
import coop.tecso.examen.service.CurrentAccountService;
import coop.tecso.examen.service.MovementService;

@RestController
public class MovementsController {

	@Autowired
	MovementService movementService;
	
	@Autowired
	CurrentAccountService currentAccountService;
	
	
	@GetMapping("/getMovements")
	public AccountMovementDto getMovementsByAccount(Long accountId){
		AccountMovementDto accountMovements= new AccountMovementDto();
		CurrentAccount ca= currentAccountService.findAccountById(accountId);
		List<Movement> movements= movementService.getMovementsFromAccount(accountId);
		accountMovements.setAccount(ca);
		accountMovements.setMovement(movements);
		return accountMovements;
		
	}
	
	
}
