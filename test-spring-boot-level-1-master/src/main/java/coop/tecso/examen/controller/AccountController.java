package coop.tecso.examen.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import coop.tecso.examen.model.CurrentAccount;
import coop.tecso.examen.model.Movement;
import coop.tecso.examen.service.CurrentAccountService;
import coop.tecso.examen.service.MovementService;

@RestController
public class AccountController {
	
	private final Logger logger = Logger.getLogger(AccountController.class);
	
	@Autowired
	CurrentAccountService currentAccountService;
	
	@Autowired
	MovementService movementService;
	
	private final double MAX_PESOS= -1000;
	private final double MAX_DOLARES= -300;
	private final double MAX_EUROS= -150;
	
	@PostMapping("/createAccount")
	public ResponseEntity CreateAccount(@RequestBody CurrentAccount account) {
		
		try{
			currentAccountService.updateAccount(account);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error creating account");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("Account created");
		
	}
	
	@GetMapping("/getAccounts")
	public List<CurrentAccount> getAccounts(){
		return currentAccountService.getAccounts();
	}
	
	
	@GetMapping("/deleteMovement")
	public String deleteAccount(Long id ) {
		CurrentAccount ca= currentAccountService.findAccountById(id);
		if(ca.getMovements().size()<1) {
			return currentAccountService.deleteAccount(ca);
		}else {
			return "The account cannot be deleted";
		}
	}
	
	@PostMapping("/addMovement")
//	public String addMovementToAccount(@RequestBody AddMovementRequestDto addmovementRequestDto) {
	public String addMovementToAccount(@RequestBody Movement movement) {
		
		
		CurrentAccount ca= currentAccountService.findAccountById(movement.getAccount_id());
		movement.setAmount(round(movement.getAmount(),2));
		ca.getMovements().add(movement);
		ca.setBalance(ca.getBalance()+ movement.getAmount());
		boolean rejected=false;
		switch(ca.getCurrency()) {
		
			case "PESOS":
				if(ca.getBalance()< MAX_PESOS) {
					rejected= true;
				}
				break;
			case "DOLARES":
				if(ca.getBalance()< MAX_DOLARES) {
					rejected= true;
				}
				break;
			case "EUROS":
				if(ca.getBalance()< MAX_EUROS) {
					rejected=true;
				}
				break;
		}
		if(!rejected) {
			 try {
				currentAccountService.updateAccount(ca);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 return "Ok";
		}else {
			return " Your movement was rejected ";
		}
		
	
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
		
		
	

}
