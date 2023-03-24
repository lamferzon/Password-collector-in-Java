package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import accounts.Account;
import accounts.AccountTypes;
import accounts.KeysCollection;
import accounts.PremiumKey;
import accounts.PremiumUserAccount;
import accounts.UserAccount;

class AccountHandler {
	
	// Fields
	private AppData data;
	private List<Account> accountList;
	private KeysCollection keysList;
	private Integer accountIndex;
	private BufferedReader bR;
	
	// Builder
	protected AccountHandler(AppData data, List<Account> accountList,
			KeysCollection keysList) {
		this.data = data;
		this.accountList = accountList;
		this.keysList = keysList;
		this.accountIndex = null;
		this.bR = new BufferedReader(new InputStreamReader(System.in));
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
			if(resp.compareTo("Y") == 0) {
				boolean flag = checkPremiumKey(accountEmail);
				if(flag) {
					System.out.println("Premium key correct.");
				}else {
					System.out.println("Premium key wrong or it doesn't exist.");
					System.out.println("\nAccount doesn't created.");
					return;
				}
			}
		}while(resp.compareTo("Y") != 0 && resp.compareTo("N") != 0);
		
		Account acc = null;
		switch(resp) {
		case "Y":
			acc = new PremiumUserAccount(name, surname, phoneNumber, 
					accountEmail, accountPw);
			System.out.println("\nWell done! Account created.\n");
			break;
		case "N":
			acc = new UserAccount(name, surname, phoneNumber, 
					accountEmail, accountPw);
			System.out.println("\nWell done! Premium account created.\n");
			break;
		}
		
		this.accountList.add(acc);
		
		File pwFile = new File(this.data.getPwPath() + "/pw_" + acc.getID() + 
				".json");
		pwFile.createNewFile();
	}

	protected Integer login() throws IOException {
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
		
		if(correct)
			return this.accountIndex;
		else
			return null;
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
		System.out.println("\nWell done! Name changed.");
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
		System.out.println("\nWell done! Surname changed.");
	}
	
	protected void changePhoneNumber() throws IOException {
		String phoneNumber;
		
		System.out.println("\nChange your phone number (B to cancel): ");
		do {
			System.out.print("Your old phone number: ");
			phoneNumber = this.bR.readLine();
			if(phoneNumber.compareTo("B") == 0)
				return;
		}while(phoneNumber.compareTo("") == 0 || 
				phoneNumber.compareTo(accountList.get(this.accountIndex).getPhoneNumber()) != 0);
		do {
			System.out.print("Your new phone number: ");
			phoneNumber = this.bR.readLine();
			if(phoneNumber.compareTo("B") == 0)
				return;
		}while(phoneNumber.compareTo("") == 0 || Util.checkPhoneNumber(phoneNumber));
		
		accountList.get(this.accountIndex).modifyPhoneNumber(phoneNumber);
		System.out.println("\nWell done! Phone number changed.");
	}
	
	protected void changeEmailAddress() throws IOException {
		String email;
		
		System.out.println("\nChange your email address (0 to cancel): ");
		do {
			System.out.print("Your old email address: ");
			email = this.bR.readLine();
			if(email.compareTo("0") == 0)
				return;
		}while(email.compareTo("") == 0 || 
				email.compareTo(accountList.get(this.accountIndex).getAccountEmail()) != 0);
		do {
			System.out.print("Your new email address: ");
			email = this.bR.readLine();
			if(email.compareTo("0") == 0)
				return;
		}while(email.compareTo("") == 0 || Util.checkEmail(email, accountList));
		
		accountList.get(this.accountIndex).modifyAccountEmail(email);
		System.out.println("\nWell done! Email address changed.");
	}
	
	protected void changeAccountPw() throws IOException {
		String pw;
		
		System.out.println("\nChange your account password (0 to cancel): ");
		do {
			System.out.print("Your old account password: ");
			pw = this.bR.readLine();
			if(pw.compareTo("0") == 0)
				return;
		}while(pw.compareTo("") == 0 || 
				pw.compareTo(accountList.get(this.accountIndex).getAccountPw()) != 0);
		do {
			System.out.print("Your new account passwors: ");
			pw = this.bR.readLine();
			if(pw.compareTo("0") == 0)
				return;
		}while(pw.compareTo("") == 0 || Util.checkPw(pw));
		
		accountList.get(this.accountIndex).modifyAccountPw(pw);
		System.out.println("\nWell done! Password changed.");
	}
	
	// @SuppressWarnings("unlikely-arg-type")
	protected boolean deleteAccount() throws IOException {
		System.out.println("\nDelete your account: ");
		
		if(accountList.get(this.accountIndex).getAccountType().compareTo(AccountTypes.ADMINISTRATOR) == 0)
			System.out.println("\nAttention: you cannot delete your account because you are the administrator.");
		else {
			String choice = null;
			do {
				System.out.print("Are you sure to delete your account? (Y/N) ");
				choice = this.bR.readLine().toUpperCase();
			}while(choice.compareTo("Y") != 0 && choice.compareTo("N") != 0);
			if(choice.compareTo("Y") == 0) {
				File f = new File(this.data.getPwPath() + "/pw_" + 
						accountList.get(this.accountIndex.intValue()).getID() + 
						".json");
				f.delete();
				accountList.remove(this.accountIndex.intValue());
				System.out.println("\nWell done! Account deleted.");
				return true;
			}else {
				return false;
			}
		}
		return false;
	}

	protected void printAccountDetails() {
		System.out.println("\nYour account details: ");
		System.out.println(accountList.get(this.accountIndex));
	}
	
	protected void addPremiumKey() throws IOException {
		System.out.println("\nAdd a Premium key (0 to cancel):");
		
		String key;
		String linkedEmailAddress;
		
		do {
			System.out.print("Premium key: ");
			key = this.bR.readLine();
			if(key.compareTo("0") == 0)
				return;
		}while(key.compareTo("") == 0);
		do {
			System.out.print("Email address of the Premium account to link: ");
			linkedEmailAddress = this.bR.readLine();
			if(linkedEmailAddress.compareTo("0") == 0)
				return;
		}while(linkedEmailAddress.compareTo("") == 0);
		
		PremiumKey pK = new PremiumKey(key, linkedEmailAddress);
		if(keysList.add(pK))
			System.out.println("\nPremium key inserted correclty.\n");
		else
			System.out.println("\nAttention: this key already exists.\n");
	}
	
	protected void removePremiumKey() throws IOException {
		System.out.println("\nRemove a Premium key (0 to cancel):");
		
		String emailAddress;
		do {
			System.out.print("Email address of the Premium account linked: ");
			emailAddress = this.bR.readLine();
			if(emailAddress.compareTo("0") == 0) {
				System.out.println("");
				return;
			}
		}while(emailAddress.compareTo("") == 0);
		
		if(keysList.remove(emailAddress))
			System.out.println("\nPremium key removed correclty.\n");
		else
			System.out.println("\nAttention: this key doesn't exists.\n");
	}
	
	protected void printPremiumKeys() {
		System.out.println("\nPremium keys list: ");
		System.out.println(keysList);
	}
	
	private boolean checkPremiumKey(String accountEmail) throws IOException {
		String keyInserted;
		do {
			System.out.print("Premium key: ");
			keyInserted = this.bR.readLine();
		}while(keyInserted.compareTo("") == 0);
		
		int idx = keysList.searchByEmail(accountEmail);
		if(idx >= 0) {
			keysList.getPremiumKey(idx).activate();
			return true;
		}else {
			return false;
		}
	}

	public void printAccountList() {
		System.out.println("\nAccount list: ");
		for(Account el : accountList)
			System.out.println(el + "\n");
	}
	
}
