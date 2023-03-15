package accounts;

import java.util.ArrayList;
import java.util.List;

public class UserAccount extends Account{
	
	// Fields
	@SuppressWarnings("unused")
	private List<Triplet<String, String, String>> pwList;
	protected static int progNum;
	
	// Builders
	public UserAccount(String name, String surname, String phoneNumber, 
			String accountEmail, String accountPw, String ID) {
		super(name, surname, phoneNumber, accountEmail, accountPw);
		this.ID = ID;
		setType();
	}
	
	public UserAccount(String name, String surname, String phoneNumber, 
			String accountEmail, String accountPw) {
		super(name, surname, phoneNumber, accountEmail, accountPw);
		pwList = new ArrayList<>();
		this.ID = "US_" + progNum;
		progNum++;
		setType();
	}
	
	// Methods
	
	protected void setType() {
		this.accountType = AccountTypes.USER;
	}
	
	public static final void setProgNum(int num) {
		progNum = num + 1;
	}
	
}
