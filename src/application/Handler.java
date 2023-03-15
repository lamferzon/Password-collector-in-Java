package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import accounts.Account;
import accounts.PremiumUserAccount;
import accounts.UserAccount;

class Handler {
	
	// Fields
	private AppData data;
	private List<Account> accountList;
	private Account currentAccount;
	private BufferedReader bR;
	
	// Builder
	protected Handler(AppData data, List<Account> accountList) {
		this.data = data;
		this.accountList = accountList;
		this.currentAccount = null;
		this.bR = new BufferedReader(new InputStreamReader(System.in));
	}
	
	// Methods
	protected void createAccount() throws IOException {
		System.out.println("\nCreate an account:");
		
		String resp;
		do {
			System.out.print("Premium account? (Y/N) ");
			resp = bR.readLine().toUpperCase();
		}while(resp.compareTo("Y") != 0 && resp.compareTo("N") != 0);
		
		String name = null;
		String surname = null;
		String phoneNumber = null;
		String accountEmail = null;
		String accountPw = null;
		
		do {
			System.out.print("Your name: ");
			name = this.bR.readLine();
		}while(name.compareTo("") == 0);
		do {
			System.out.print("Your surname: ");
			surname = this.bR.readLine();
		}while(surname.compareTo("") == 0);
		do {
			System.out.print("Phone number: ");
			phoneNumber = this.bR.readLine();
		}while(phoneNumber.compareTo("") == 0 || 
				Util.checkPhoneNumber(phoneNumber));
		do {
			System.out.print("Account email: ");
			accountEmail = this.bR.readLine();
		}while(accountEmail.compareTo("") == 0 || Util.checkEmail(accountEmail,
				this.accountList));
		do {
			System.out.print("Account password: ");
			accountPw = this.bR.readLine();
		}while(accountPw.compareTo("") == 0 || Util.checkPw(accountPw));
		
		System.out.println("");
		
		Account acc = null;
		switch(resp) {
		case "Y":
			acc = new PremiumUserAccount(name, surname, phoneNumber, 
					accountEmail, accountPw);
			break;
		case "N":
			acc = new UserAccount(name, surname, phoneNumber, 
					accountEmail, accountPw);
			break;
		}
		
		this.accountList.add(acc);
		
		File pwFile = new File(this.data.getPwPath() + "/pw_" + acc.getID() + 
				".json");
		pwFile.createNewFile();
	}

	protected void login() {
		// TODO
	}
	
}
