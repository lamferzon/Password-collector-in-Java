package accounts;

public interface PwMethods {
	
	// Methods
	public boolean removePw(String ID);
	public void modifyPwName(String ID, String newName);
	public void modifyPwUsername(String ID, String newUserName);
	public void modifyPw(String ID, String newPw);
	public void modifyInformation(String ID, String newInformation);
	public int searchPw(String ID);
	
}
