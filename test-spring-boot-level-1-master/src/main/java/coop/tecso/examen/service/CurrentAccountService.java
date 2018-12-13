package coop.tecso.examen.service;

import java.sql.SQLException;
import java.util.List;

import coop.tecso.examen.model.CurrentAccount;
import coop.tecso.examen.model.Movement;

public interface CurrentAccountService {
	
	public void updateAccount (CurrentAccount currentAccount)  throws SQLException;
	public String deleteAccount (CurrentAccount currentAccount);
	public List<CurrentAccount> getAccounts();
	public CurrentAccount findAccountById(Long id);
	public void addMovement(Movement movement, CurrentAccount currentAccount);
	public List<Movement> getMovementsByAccount(Long id);

}
