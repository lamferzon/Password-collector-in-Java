package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
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
	private BufferedReader bR;
	private static String exitWord = "QUIT";
	
	// Builder
	protected AccountHandler(AppData data, List<Account> accountList,
			KeysCollection keysList) {
		this.data = data;
		this.accountList = accountList;
		this.keysList = keysList;
		this.bR = new BufferedReader(new InputStreamReader(System.in));
	}
	
	// Methods
	protected void createAccount() throws IOException {
		System.out.println("\nCreate an account (" + exitWord + " to exit):");
		
		String name = null;
		String surname = null;
		String phoneNumber = null;
		String accountEmail = null;
		String accountPw = null;
		String resp = null;
		
		do {
			System.out.print("Your name: ");
			name = this.bR.readLine();
			if(name.compareTo(exitWord) == 0)
				return;
		}while(name.compareTo("") == 0);
		do {
			System.out.print("Your surname: ");
			surname = this.bR.readLine();
			if(surname.compareTo(exitWord) == 0)
				return;
		}while(surname.compareTo("") == 0);
		do {
			System.out.print("Phone number: ");
			phoneNumber = this.bR.readLine();
			if(phoneNumber.compareTo(exitWord) == 0)
				return;
		}while(phoneNumber.compareTo("") == 0 || Account.checkPhoneNumber(phoneNumber));
		do {
			System.out.print("Account email: ");
			accountEmail = this.bR.readLine();
			if(accountEmail.compareTo(exitWord) == 0)
				return;
		}while(accountEmail.compareTo("") == 0 || Account.checkEmail(accountEmail,
				this.accountList));
		do {
			System.out.print("Account password: ");
			accountPw = this.bR.readLine();
			if(accountPw.compareTo(exitWord) == 0)
				return;
		}while(accountPw.compareTo("") == 0 || Account.checkPw(accountPw));
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
			System.out.println("\nWell done! Premium account created.\n");
			break;
		case "N":
			acc = new UserAccount(name, surname, phoneNumber, 
					accountEmail, accountPw);
			System.out.println("\nWell done! Account created.\n");
			break;
		}
		
		this.accountList.add(acc);
		Collections.sort(accountList);
		
		File pwFile = new File(this.data.getPwPath() + "/pw_" + acc.getID() + 
				".json");
		pwFile.createNewFile();
	}

	protected void login() throws IOException {
		System.out.println("\nLogin (" + exitWord + " to exit):");
		String emailInserted;
		String pwInserted;
		
		do {
			System.out.print("Email address: ");
			emailInserted = this.bR.readLine();
			if(emailInserted.compareTo(exitWord) == 0)
				return;
		}while(emailInserted.compareTo("") == 0);
		do {
			System.out.print("Password: ");
			pwInserted = this.bR.readLine();
			if(pwInserted.compareTo(exitWord) == 0)
				return;
		}while(pwInserted.compareTo("") == 0);
		
		boolean find = false;
		Account temp = null;
		for(int i = 0; i < accountList.size(); i++) {
			if(accountList.get(i).getAccountEmail().compareTo(emailInserted) == 0) {
				Application.accountIdx = i;
				temp = accountList.get(Application.accountIdx);
				find = true;
				break;
			}
		}
		if(find) {
			if(temp.getAccountPw().compareTo(pwInserted) == 0) {
				System.out.println("\n" + temp);
				System.out.println("\nWelcome back " + temp.getName() + 
						" (" + temp.getID() + ")!");
			}else {
				Application.accountIdx = null;
				System.out.println("\nAttention! Password or email address are wrong.");
			}
		}else {
			System.out.println("\nAttention! Password or email address are wrong.");
		}
		System.out.println("");
	}

	protected void logout() {
		Application.accountIdx = null;
	}

	protected void changeAccountName() throws IOException {
		String name;
		
		System.out.println("\nChange your name (" + exitWord + " to exit): ");
		do {
			System.out.print("Your old name: ");
			name = this.bR.readLine();
			if(name.compareTo(exitWord) == 0)
				return;
		}while(name.compareTo("") == 0 || 
				name.compareTo(accountList.get(Application.accountIdx).getName()) != 0);
		do {
			System.out.print("Your new name: ");
			name = this.bR.readLine();
			if(name.compareTo(exitWord) == 0)
				return;
		}while(name.compareTo("") == 0);
		
		accountList.get(Application.accountIdx).modifyName(name);
		System.out.println("\nWell done! Name changed.");
	}
	
	protected void changeAccountSurname() throws IOException {
		String surname;
		
		System.out.println("\nChange your surname (" + exitWord + " to exit): ");
		do {
			System.out.print("Your old surname: ");
			surname = this.bR.readLine();
			if(surname.compareTo(exitWord) == 0)
				return;
		}while(surname.compareTo("") == 0 || 
				surname.compareTo(accountList.get(Application.accountIdx).getSurname()) != 0);
		do {
			System.out.print("Your new surname: ");
			surname = this.bR.readLine();
			if(surname.compareTo(exitWord) == 0)
				return;
		}while(surname.compareTo("") == 0);
		
		accountList.get(Application.accountIdx).modifySurname(surname);
		System.out.println("\nWell done! Surname changed.");
	}
	
	protected void changePhoneNumber() throws IOException {
		String phoneNumber;
		
		System.out.println("\nChange your phone number (" + exitWord + " to exit): ");
		do {
			System.out.print("Your old phone number: ");
			phoneNumber = this.bR.readLine();
			if(phoneNumber.compareTo(exitWord) == 0)
				return;
		}while(phoneNumber.compareTo("") == 0 || 
				phoneNumber.compareTo(accountList.get(Application.accountIdx).getPhoneNumber()) != 0);
		do {
			System.out.print("Your new phone number: ");
			phoneNumber = this.bR.readLine();
			if(phoneNumber.compareTo(exitWord) == 0)
				return;
		}while(phoneNumber.compareTo("") == 0 || Account.checkPhoneNumber(phoneNumber));
		
		accountList.get(Application.accountIdx).modifyPhoneNumber(phoneNumber);
		System.out.println("\nWell done! Phone number changed.");
	}
	
	protected void changeEmailAddress() throws IOException {
		String email;
		
		System.out.println("\nChange your email address (" + exitWord + " to exit): ");
		do {
			System.out.print("Your old email address: ");
			email = this.bR.readLine();
			if(email.compareTo(exitWord) == 0)
				return;
		}while(email.compareTo("") == 0 || 
				email.compareTo(accountList.get(Application.accountIdx).getAccountEmail()) != 0);
		do {
			System.out.print("Your new email address: ");
			email = this.bR.readLine();
			if(email.compareTo(exitWord) == 0)
				return;
		}while(email.compareTo("") == 0 || Account.checkEmail(email, accountList));
		
		accountList.get(Application.accountIdx).modifyAccountEmail(email);
		System.out.println("\nWell done! Email address changed.");
	}
	
	protected void changeAccountPw() throws IOException {
		String pw;
		
		System.out.println("\nChange your account password (" + exitWord + " to exit): ");
		do {
			System.out.print("Your old account password: ");
			pw = this.bR.readLine();
			if(pw.compareTo(exitWord) == 0)
				return;
		}while(pw.compareTo("") == 0 || 
				pw.compareTo(accountList.get(Application.accountIdx).getAccountPw()) != 0);
		do {
			System.out.print("Your new account passwors: ");
			pw = this.bR.readLine();
			if(pw.compareTo(exitWord) == 0)
				return;
		}while(pw.compareTo("") == 0 || Account.checkPw(pw));
		
		accountList.get(Application.accountIdx).modifyAccountPw(pw);
		System.out.println("\nWell done! Password changed.");
	}
	
	protected boolean deleteAccount() throws IOException {
		System.out.println("\nDelete your account: ");
		
		if(accountList.get(Application.accountIdx).getAccountType().compareTo(AccountTypes.ADMINISTRATOR) == 0)
			System.out.println("\nAttention: you cannot delete your account because you are the administrator.");
		else {
			String choice = null;
			do {
				System.out.print("Are you sure to delete your account? (Y/N) ");
				choice = this.bR.readLine().toUpperCase();
			}while(choice.compareTo("Y") != 0 && choice.compareTo("N") != 0);
			if(choice.compareTo("Y") == 0) {
				File f = new File(this.data.getPwPath() + "/pw_" + 
						accountList.get(Application.accountIdx.intValue()).getID() + 
						".json");
				f.delete();
				accountList.remove(Application.accountIdx.intValue());
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
		System.out.println(accountList.get(Application.accountIdx));
	}
	
	protected void addPremiumKey() throws IOException {
		System.out.println("\nAdd a Premium key (" + exitWord + " to exit):");
		
		String key;
		String linkedEmailAddress;
		
		do {
			System.out.print("Premium key: ");
			key = this.bR.readLine();
			if(key.compareTo(exitWord) == 0)
				return;
		}while(key.compareTo("") == 0);
		do {
			System.out.print("Email address of the Premium account to link: ");
			linkedEmailAddress = this.bR.readLine();
			if(linkedEmailAddress.compareTo(exitWord) == 0)
				return;
		}while(linkedEmailAddress.compareTo("") == 0);
		
		PremiumKey pK = new PremiumKey(key, linkedEmailAddress);
		if(keysList.add(pK))
			System.out.println("\nPremium key inserted correclty.\n");
		else
			System.out.println("\nAttention! This key already exists.\n");
	}
	
	protected void removePremiumKey() throws IOException {
		System.out.println("\nRemove a Premium key (" + exitWord + " to cancel):");
		
		String emailAddress;
		do {
			System.out.print("Email address of the Premium account linked: ");
			emailAddress = this.bR.readLine();
			if(emailAddress.compareTo(exitWord) == 0) {
				System.out.println("");
				return;
			}
		}while(emailAddress.compareTo("") == 0);
		
		if(keysList.remove(emailAddress))
			System.out.println("\nPremium key removed correclty.\n");
		else
			System.out.println("\nAttention! This key doesn't exists.\n");
	}
	
	protected void printPremiumKeys() {
		System.out.println("\nPremium keys list: ");
		System.out.println(keysList);
	}
	
	protected boolean checkPremiumKey(String accountEmail) throws IOException {
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

	protected void printAccountList() {
		System.out.println("\nAccount list: ");
		for(Account el : accountList)
			System.out.println(el + "\n");
	}
	
}
