package accounts;

public class AdminAccount extends Account{
	
	// Builder
	public AdminAccount(String name, String surname, String phoneNumber,
			String accountEmail, String accountPw) {
		super(name, surname, phoneNumber, accountEmail, accountPw);
		this.ID = "ADMIN_0";
		this.accountType = AccountTypes.ADMINISTRATOR;
	}
	
}