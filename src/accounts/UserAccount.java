package accounts;

import java.util.ArrayList;

public class UserAccount extends Account{
	
	// Data
	private ArrayList<Triplet<String, String, String>> pwList;
	private static int progNum = 1;
	
	// Costructor
	public UserAccount(String name, String surname, String phoneNumber, 
			String accountEmail, String accountPw) {
		super(name, surname, phoneNumber, accountEmail, accountPw);
		this.ID = "USER_" + this.progNum;
		this.accountType = AccountTypes.USER;
	}
	
}
