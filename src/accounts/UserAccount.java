package accounts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import application.Util;

public class UserAccount extends Account{
	
	// Fields
	protected List<Password> pwList;
	protected int progPw;
	protected static int progAcc;
	protected static String pwPath;
	
	// Builders
	// Account which already exists
	public UserAccount(String name, String surname, String phoneNumber, 
			String accountEmail, String accountPw, String ID) {
		super(name, surname, phoneNumber, accountEmail, accountPw);
		this.ID = ID;
		setType();
		Util.readPwFromJSON(pwPath, this.ID, pwList);
		setProgPw();
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
	public static final void setProgNum(int num) {
		progAcc = num;
	}
	
	public boolean insertPw(String name, String username, String pw) {
		Password pass = new Password("PW_" + progPw, name, username, pw);
		progPw++;
		Collections.sort(pwList);
		return pwList.add(pass);
	}
	
	public boolean insertPw(String name, String username, String pw, 
			String information) {
		Password pass = new Password("PW_" + progPw, name, username, pw, information);
		progPw++;
		return pwList.add(pass);
	}
	
	public List<Password> getPwList(){
		return this.pwList;
	}
	
	public static void setPwPath(String path) {
		pwPath = path;
	}
	
	protected void setType() {
		this.accountType = AccountTypes.USER;
	}
	
	protected void setProgPw() {
		Password lastPw = pwList.get(pwList.size()-1);
		this.progPw = Integer.parseInt(lastPw.getID().substring(3)) + 1;
	}
	
}
