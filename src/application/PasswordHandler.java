package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import accounts.Account;
import accounts.Password;
import accounts.PremiumUserAccount;
import accounts.UserAccount;

class PasswordHandler {
	
	// Fields
	private List<Account> accountList;
	private BufferedReader bR;
	private static String exitWord = "QUIT";
	
	// Builder
	protected PasswordHandler(List<Account> accountList) {
		this.accountList = accountList;
		this.bR = new BufferedReader(new InputStreamReader(System.in));
	}
	
	// Methods
	protected void addPassword() throws IOException {
		String name;
		String username;
		String pw;
		String information;
		
		System.out.println("\nInsert a new password (" + exitWord + " to exit): ");
		do {
			System.out.print("Name (i.e. web site): ");
			name = this.bR.readLine();
			if(name.compareTo(exitWord) == 0)
				return;
		}while(name.compareTo("") == 0);
		do {
			System.out.print("Username: ");
			username = this.bR.readLine();
			if(username.compareTo(exitWord) == 0)
				return;
		}while(username.compareTo("") == 0);
		do {
			System.out.print("Password: ");
			pw = this.bR.readLine();
			if(pw.compareTo(exitWord) == 0)
				return;
		}while(pw.compareTo("") == 0);
		System.out.print("Information (i.e. expiration date): ");
		information = this.bR.readLine();
		if(information.compareTo(exitWord) == 0)
			return;
		
		UserAccount acc = (UserAccount) accountList.get(Application.accountIdx);
		String ID;
		if(information.compareTo("") == 0)
			ID = acc.insertPw(name, username, pw);
		else
			ID = acc.insertPw(name, username, pw, information);
		
		System.out.println("\nWell done! " + ID + " inserted.");
	}
	
	protected void printPassword() throws IOException {
		System.out.println("\nSpecific password (" + exitWord + " to exit):");
		
		String ID;
		do {
			System.out.print("Password ID: ");
			ID = this.bR.readLine();
			if(ID.compareTo(exitWord) == 0)
				return;
		}while(ID.compareTo("") == 0);
		
		UserAccount acc = (UserAccount) accountList.get(Application.accountIdx);
		Password pw = acc.searchPw(ID);
		
		if(pw!= null)
			System.out.println(pw.toStringDecrypted());
		else
			System.out.println("\nAttention! " + ID + " doesn't exist.");
	}

	protected void printPasswords() {
		System.out.println("\nPassword list: ");
		UserAccount acc = (UserAccount) accountList.get(Application.accountIdx);
		if(acc.getPwList().size() == 0)
			System.out.println("NO passwords");
		else
			System.out.print(acc.pwListTostring());
	}

	protected void changePwName(Password pw) throws IOException {
		System.out.println("\nOld name of " + pw.getID() + ": " + pw.getName());
		
		String name;
		do {
			System.out.print("New name of " + pw.getID() + " (" + exitWord + " to exit): ");
			name = this.bR.readLine();
			if(name.compareTo(exitWord) == 0)
				return;
		}while(name.compareTo("") == 0);
		
		PremiumUserAccount acc = (PremiumUserAccount) accountList.get(Application.accountIdx);
		acc.modifyPwName(pw.getID(), name);
		System.out.println("\nWell done! Name of " + pw.getID() + " changed.");
	}

	protected void changePwUsername(Password pw) throws IOException {
		System.out.println("\nOld username of " + pw.getID() + ": " + pw.getUsername());
		
		String username;
		do {
			System.out.print("New username of " + pw.getID() + " (" + exitWord + " to exit): ");
			username = this.bR.readLine();
			if(username.compareTo(exitWord) == 0)
				return;
		}while(username.compareTo("") == 0);
		
		PremiumUserAccount acc = (PremiumUserAccount) accountList.get(Application.accountIdx);
		acc.modifyPwUsername(pw.getID(), username);
		System.out.println("\nWell done! Username of " + pw.getID() + " changed.");
	}

	protected void changePw(Password pw) throws IOException {
		System.out.println("\nOld password of " + pw.getID() + ": " + pw.getPw());
		
		String password;
		do {
			System.out.print("New password of " + pw.getID() + " (" + exitWord + " to exit): ");
			password = this.bR.readLine();
			if(password.compareTo(exitWord) == 0)
				return;
		}while(password.compareTo("") == 0);
		
		PremiumUserAccount acc = (PremiumUserAccount) accountList.get(Application.accountIdx);
		acc.modifyPw(pw.getID(), password);
		System.out.println("\nWell done! Password of " + pw.getID() + " changed.");
	}

	protected void changePwInformation(Password pw) throws IOException {
		System.out.println("\nOld information of " + pw.getID() + ": " + pw.getInformation());
		
		String information;
		do {
			System.out.print("New information of " + pw.getID() + " (" + exitWord + " to exit): ");
			information = this.bR.readLine();
			if(information.compareTo(exitWord) == 0)
				return;
		}while(information.compareTo("") == 0);
		
		PremiumUserAccount acc = (PremiumUserAccount) accountList.get(Application.accountIdx);
		acc.modifyPwInformation(pw.getID(), information);
		System.out.println("\nWell done! Information of " + pw.getID() + " changed.");
	}

	protected void removePw(Password pw) throws IOException {
		String choice = null;
		System.out.println();
		do {
			System.out.print("Are you sure to delete " + pw.getID() + "? (Y/N) ");
			choice = this.bR.readLine().toUpperCase();
		}while(choice.compareTo("Y") != 0 && choice.compareTo("N") != 0);
		
		if(choice.compareTo("Y") == 0) {
			PremiumUserAccount acc = (PremiumUserAccount) accountList.get(Application.accountIdx);
			acc.removePw(pw.getID());
			System.out.println("\nWell done! " + pw.getID() + " removed.");
		}
	}

	protected Password searchPw() throws IOException {
		System.out.println();
		String ID;
		do {
			System.out.print("Password ID (" + exitWord + " to exit): ");
			ID = this.bR.readLine();
			if(ID.compareTo(exitWord) == 0)
				return null;
		}while(ID.compareTo("") == 0);
		
		PremiumUserAccount acc = (PremiumUserAccount) accountList.get(Application.accountIdx);
		Password pw = acc.searchPw(ID);
		if(pw != null) {
			System.out.println("\n" + pw);
			return pw;
		}
		else {
			System.out.println("\nAttention! " + ID + " doesn't exist.");
			return null;
		}
	}

}
