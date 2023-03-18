package accounts;

import java.util.ArrayList;

public class PremiumUserAccount extends UserAccount{
	
	// Fields
	@SuppressWarnings("unused")
	private ArrayList<Triplet<String, String, String>> pwList;
	
	// Builders
	public PremiumUserAccount(String name, String surname, String phoneNumber, 
			String accountEmail, String accountPw, String ID) {
		super(name, surname, phoneNumber, accountEmail, accountPw, ID);
	}
	
	public PremiumUserAccount(String name, String surname, String phoneNumber, 
			String accountEmail, String accountPw) {
		super(name, surname, phoneNumber, accountEmail, accountPw);
	}
	
	// Methods
	protected void setType() {
		this.accountType = AccountTypes.PREMIUM_USER;
	}
	
}
