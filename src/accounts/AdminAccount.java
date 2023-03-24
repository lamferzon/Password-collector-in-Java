package accounts;

public class AdminAccount extends Account{
	
	// Fields
	public static KeysCollection keysList = new KeysCollection();;
	
	// Builder
	public AdminAccount(String name, String surname, String phoneNumber,
			String accountEmail, String accountPw) {
		super(name, surname, phoneNumber, accountEmail, accountPw);
		this.ID = "AD_0";
		this.accountType = AccountTypes.ADMINISTRATOR;
	}
	
}