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
	
	// Methods
	protected static void createAccount(List<Account> accountList, 
			AppData data) throws IOException {
		BufferedReader bR = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("\nCreate an account:");
		
		String resp;
		do {
			System.out.print("Premium account? (Y/N) ");
			resp = bR.readLine();
		}while(resp.toUpperCase().compareTo("Y") != 0 &&
				resp.toUpperCase().compareTo("N") != 0);
		
		String name = null;
		String surname = null;
		String phoneNumber = null;
		String accountEmail = null;
		String accountPw = null;
		
		do {
			System.out.print("Your name: ");
			name = bR.readLine();
		}while(name.compareTo("") == 0);
		
		do {
			System.out.print("Your surname: ");
			surname = bR.readLine();
		}while(surname.compareTo("") == 0);
		
		do {
			System.out.print("Phone number: ");
			phoneNumber = bR.readLine();
		}while(phoneNumber.compareTo("") == 0);
		
		do {
			System.out.print("Account email: ");
			accountEmail = bR.readLine();
		}while(accountEmail.compareTo("") == 0);
		
		do {
			System.out.print("Account password: ");
			accountPw = bR.readLine();
		}while(accountPw.compareTo("") == 0);
		
		System.out.println("");
		
		Account acc = null;
		resp = resp.toUpperCase();
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
		
		accountList.add(acc);
		File pwFile = new File(data.getPwPath() + "/pw_" + acc.getID() + 
				".json");
		pwFile.createNewFile();
	}

	protected static void login(List<Account> accountList) {
		// TODO
	}
}
