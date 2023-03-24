package accounts;

import java.util.ArrayList;
import java.util.List;

public class UserAccount extends Account{
	
	// Fields
	protected List<Password> pwList;
	protected int progPw;
	protected static int progAcc;
	
	// Builders
	// Account which already exists
	public UserAccount(String name, String surname, String phoneNumber, 
			String accountEmail, String accountPw, String ID) {
		super(name, surname, phoneNumber, accountEmail, accountPw);
		this.ID = ID;
		setType();
		// TODO
	}
	
	// Account to build for the first time
	public UserAccount(String name, String surname, String phoneNumber, 
			String accountEmail, String accountPw) {
		super(name, surname, phoneNumber, accountEmail, accountPw);
		pwList = new ArrayList<>();
		this.ID = "US_" + progAcc;
		progAcc++;
		progPw = 0;
		setType();
	}
	
	// Methods
	
	protected void setType() {
		this.accountType = AccountTypes.USER;
	}
	
	public static final void setProgNum(int num) {
		progAcc = num;
	}
	
}
