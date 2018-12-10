package coop.tecso.examen.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="current_account")
public class CurrentAccount extends AbstractPersistentObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
//	@Id
//	@GeneratedValue
//	private Long Id;
//	@Override
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long idAccount;
	private String currency;
	private double balance;
//	@OneToMany(mappedBy="currentAccount")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name= "account_id" , referencedColumnName= "id")
    private List<Movement> movements;
//	@OneToMany(
//			mappedBy = "accountId", 
//			cascade = CascadeType.ALL,
//			orphanRemoval = true
//	)
//	private List<Movement> movement;
	
	

	
	
	
	
	public String getCurrency() {
		return currency;
	}
	
	public List<Movement> getMovements() {
		return movements;
	}

	public void setMovements(List<Movement> movements) {
		this.movements = movements;
	}

	//	public Long getId() {
//		return Id;
//	}
//	public void setId(Long id) {
//		Id = id;
//	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
//	public List<Movement> getMovement() {
//		return movement;
//	}
//	public void setMovement(List<Movement> movement) {
//		this.movement = movement;
//	} 

	@Override
	public String toString() {
		return "CurrentAccount [currency=" + currency + ", balance=" + balance + ", movements=" + movements + "]";
	}
	
	
	




	

}
