package coop.tecso.examen.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coop.tecso.examen.controller.AccountController;
import coop.tecso.examen.model.CurrentAccount;
import coop.tecso.examen.model.Movement;
import coop.tecso.examen.repository.CurrentAccountRepository;
import coop.tecso.examen.repository.MovementRepository;
import coop.tecso.examen.service.CurrentAccountService;

@Service
public class CurrentAccountServiceImpl implements CurrentAccountService {
	
	final static Logger logger = Logger.getLogger(AccountController.class);
	
	@Autowired
	CurrentAccountRepository currentAccountRepository;
	
	@Autowired
	MovementRepository movementRepository;


	@Override
	public void updateAccount(CurrentAccount currentAccount) throws SQLException {
		try {
			currentAccountRepository.save(currentAccount);
		}catch (JDBCException e) {
			logger.error("Error creating new account : " + e.getMessage());
			 throw e;
		}
	}

	@Override
	public CurrentAccount findAccountById(Long id) {
		return currentAccountRepository.findById(id).get();
	}
	
	@Override
	public List<CurrentAccount> getAccounts() {
		List<CurrentAccount> accounts= new ArrayList();
		try {
			accounts= currentAccountRepository.findAll();
		}catch (Exception e) {
			System.out.println(e.getMessage());
			
		}
		
		return accounts;
	}

	@Override
	public void addMovement(Movement movement, CurrentAccount currentAccount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Movement> getMovementsByAccount(Long id) {
		
		return null;
	}

	@Override
	public String deleteAccount(CurrentAccount currentAccount) {
		
		try {
			currentAccountRepository.delete(currentAccount);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return "Error while deleting account";
		}
		
		return "Account deleted";
	}
	
	

}
