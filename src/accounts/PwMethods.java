package accounts;

public interface PwMethods {
	
	// Methods
	public boolean removePw(String ID);
	public boolean modifyPwName(String ID, String newName);
	public boolean modifyPwUsername(String ID, String newUserName);
	public boolean modifyPw(String ID, String newPw);
	public boolean modifyPwInformation(String ID, String newInformation);
	
}
