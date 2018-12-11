package coop.tecso.examen.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import coop.tecso.examen.dto.AddMovementRequestDto;
import coop.tecso.examen.model.CurrentAccount;
import coop.tecso.examen.service.CurrentAccountService;
import coop.tecso.examen.service.MovementService;

@RestController
public class AccountController {
	
	@Autowired
	CurrentAccountService currentAccountService;
	
	@Autowired
	MovementService movementService;
	
	private final double MAX_PESOS= -1000;
	private final double MAX_DOLARES= -300;
	private final double MAX_EUROS= -150;
	
	@PostMapping("/createAccount")
	public String CreateAccount(@RequestBody CurrentAccount account) {
		try{
			System.out.println("la cuenta q llego  : "+ account.getCurrency() + "       :" + account.getBalance());
			currentAccountService.createAccount(account);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return "error creating Account";
		}
		
		return "Account created";
		
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
	public String addMovementToAccount(@RequestBody AddMovementRequestDto addmovementRequestDto) {
		
		
		CurrentAccount ca= currentAccountService.findAccountById(addmovementRequestDto.getId());
		addmovementRequestDto.getMovement().setAmount(round(addmovementRequestDto.getMovement().getAmount(),2));
		ca.getMovements().add(addmovementRequestDto.getMovement());
		ca.setBalance(ca.getBalance()+ addmovementRequestDto.getMovement().getAmount());
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
			return currentAccountService.updateAccount(ca);
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
