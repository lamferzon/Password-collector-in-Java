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
	private Integer accountIndex;
	private BufferedReader bR;
	private CaesarEncryptor en;
	
	// Builder
	protected Handler(AppData data, List<Account> accountList) {
		this.data = data;
		this.accountList = accountList;
		this.accountIndex = null;
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

	protected boolean login() throws IOException {
		System.out.println("\nLogin:");
		String emailInserted;
		String pwInserted;
		
		do {
			System.out.print("Email address: ");
			emailInserted = this.bR.readLine();
		}while(emailInserted.compareTo("") == 0);
		do {
			System.out.print("Password: ");
			pwInserted = this.bR.readLine();
		}while(pwInserted.compareTo("") == 0);
		
		boolean find = false;
		Account temp = null;
		for(int i = 0; i < accountList.size(); i++) {
			if(accountList.get(i).getAccountEmail().compareTo(emailInserted) == 0) {
				this.accountIndex = i;
				temp = accountList.get(this.accountIndex);
				find = true;
				break;
			}
		}
		boolean correct = false;
		if(find) {
			if(temp.getAccountPw().compareTo(pwInserted) == 0) {
				correct = true;
				System.out.println("\n" + temp);
				System.out.println("\nWelcome back " + temp.getName() + 
						" (" + temp.getID() + ")!");
			}else {
				System.out.println("\nAttention: password or email address are wrong.");
			}
		}else {
			System.out.println("\nAttention: password or email address are wrong.");
		}
		System.out.println("");
		return correct;
	}

	protected void logout() {
		this.accountIndex = null;
	}

	protected void changeAccountName() throws IOException {
		String name;
		
		System.out.println("\nChange your name (0 to cancel): ");
		do {
			System.out.print("Your old name: ");
			name = this.bR.readLine();
			if(name.compareTo("0") == 0)
				return;
		}while(name.compareTo("") == 0 || 
				name.compareTo(accountList.get(this.accountIndex).getName()) != 0);
		do {
			System.out.print("Your new name: ");
			name = this.bR.readLine();
			if(name.compareTo("0") == 0)
				return;
		}while(name.compareTo("") == 0);
		
		accountList.get(this.accountIndex).modifyName(name);
	}
	
	protected void changeAccountSurname() throws IOException {
		String surname;
		
		System.out.println("\nChange your surname (0 to cancel): ");
		do {
			System.out.print("Your old surname: ");
			surname = this.bR.readLine();
			if(surname.compareTo("0") == 0)
				return;
		}while(surname.compareTo("") == 0 || 
				surname.compareTo(accountList.get(this.accountIndex).getSurname()) != 0);
		do {
			System.out.print("Your new surname: ");
			surname = this.bR.readLine();
			if(surname.compareTo("0") == 0)
				return;
		}while(surname.compareTo("") == 0);
		
		accountList.get(this.accountIndex).modifySurname(surname);
	}

	protected void printAccountDetails() {
		System.out.println("\nYour account details: ");
		System.out.println(accountList.get(this.accountIndex));
	}
	
}
