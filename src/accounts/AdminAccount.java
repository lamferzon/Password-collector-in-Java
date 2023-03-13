package accounts;

import encryptors.ModCaesarEncryptor;

public class AdminAccount extends Account{
	
	// Constructor
	public AdminAccount(String name, String surname, String phoneNumber,
			String accountEmail, String accountPw, boolean newAdmin) {
		this.ID = "ADMIN_0";
		this.accountType = AccountTypes.ADMINISTRATOR;
		this.name = name;
		this.surname = surname;
		if(newAdmin) {
			ModCaesarEncryptor en = new ModCaesarEncryptor();
			String[] crypted = en.encrypts(phoneNumber, accountEmail, accountPw);
			this.phoneNumber = crypted[0];
			this.accountEmail = crypted[1];
			this.accountPw = crypted[2];
		}else {
			this.phoneNumber = phoneNumber;
			this.accountEmail = accountEmail;
			this.accountPw = accountPw;
		}
	}
	
}