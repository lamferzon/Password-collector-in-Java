package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import accounts.Account;
import accounts.PremiumUserAccount;
import accounts.UserAccount;
import encryptors.CaesarEncryptor;
import encryptors.ModCaesarEncryptor;

class Handler {
	
	// Fields
	private AppData data;
	private List<Account> accountList;
	private Account currentAccount;
	private BufferedReader bR;
	private CaesarEncryptor en;
	
	// Builder
	protected Handler(AppData data, List<Account> accountList) {
		this.data = data;
		this.accountList = accountList;
		this.currentAccount = null;
		this.bR = new BufferedReader(new InputStreamReader(System.in));
		this.en = new ModCaesarEncryptor(15);
	}
	
	// Methods
	protected void createAccount() throws IOException {
		System.out.println("\nCreate an account:");
		
		String name = null;
		String surname = null;
		String phoneNumber = null;
		String accountEmail = null;
		String accountPw = null;
		String resp = null;
		
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
		do {
			System.out.print("Premium account? (Y/N) ");
			resp = bR.readLine().toUpperCase();
		}while(resp.compareTo("Y") != 0 && resp.compareTo("N") != 0);
		
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

	protected void login() throws IOException {
		System.out.println("\nLogin:");
		String emailInserted;
		String pwInserted;
		
		do {
			System.out.print("Account email: ");
			emailInserted = this.bR.readLine();
		}while(emailInserted.compareTo("") == 0);
		do {
			System.out.print("Account password: ");
			pwInserted = this.bR.readLine();
		}while(pwInserted.compareTo("") == 0);
		
		boolean find = false;
		Account temp = null;
		for(Account acc : accountList) {
			if(acc.getAccountEmail().compareTo(emailInserted) == 0) {
				temp = acc;
				find = true;
				break;
			}
		}
		if(find) {
			if(temp.getAccountPw().compareTo(pwInserted) == 0) {
				this.currentAccount = temp;
				System.out.println("\n" + this.currentAccount);
				System.out.println("\nWelcome back " + temp.getName() + 
						" (" + temp.getID() + ")!");
			}else {
				System.out.println("\nAttention: password or email address are wrong.");
			}
		}else {
			System.out.println("\nAttention: password or email address are wrong.");
		}
		System.out.println("");
	}
	
}
