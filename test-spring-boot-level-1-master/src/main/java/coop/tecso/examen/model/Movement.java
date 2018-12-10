package coop.tecso.examen.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="movements")
public class Movement extends AbstractPersistentObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4L;
	
//	@Id
//	@GeneratedValue
//	private Long id;
//	@ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id")
//	private CurrentAccount accountId;
	private Date date;
	private String movementType;
	//@ManyToOne(fetch = FetchType.LAZY)
    private Long account_id;
	private String description;
	private float amount;
	
	
	
//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
	
	
	
	public Long getAccount_id() {
		return account_id;
	}
	public void setAccount_id(Long account_id) {
		this.account_id = account_id;
	}
	public Date getDate() {
		return date;
	}
//	public CurrentAccount getCurrentAccount() {
//		return currentAccount;
//	}
//	public void setCurrentAccount(CurrentAccount currentAccount) {
//		this.currentAccount = currentAccount;
//	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getMovementType() {
		return movementType;
	}
	public void setMovementType(String movementType) {
		this.movementType = movementType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Movement [date=" + date + ", movementType=" + movementType 
				+ ", description=" + description + ", amount=" + amount + "]";
	}
	
	
	
	

}
