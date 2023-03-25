package accounts;

public class PremiumUserAccount extends UserAccount implements PwMethods{
	
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
	@Override
	public boolean removePw(String ID) {
		Password pw = searchPw(ID);
		if(pw == null) {
			return false;
		}else {
			pwList.remove(pw);
			return true;
		}
	}

	@Override
	public boolean modifyPwName(String ID, String newName) {
		Password pw = searchPw(ID);
		if(pw == null) {
			return false;
		}else {
			pw.modifyName(newName);
			return true;
		}
	}

	@Override
	public boolean modifyPwUsername(String ID, String newUserName) {
		Password pw = searchPw(ID);
		if(pw == null) {
			return false;
		}else {
			pw.modifyUsername(newUserName);
			return true;
		}
	}

	@Override
	public boolean modifyPw(String ID, String newPw) {
		Password pw = searchPw(ID);
		if(pw == null) {
			return false;
		}else {
			pw.modifyPw(newPw);
			return true;
		}
	}

	@Override
	public boolean modifyPwInformation(String ID, String newInformation) {
		Password pw = searchPw(ID);
		if(pw == null) {
			return false;
		}else {
			pw.modifyInformation(newInformation);
			return true;
		}
	}
	
	protected void setType() {
		this.accountType = AccountTypes.PREMIUM_USER;
	}
	
}
