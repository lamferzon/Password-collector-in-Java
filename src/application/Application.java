package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import accounts.*;

class Application {
	
	// Fields
	private AppData data;
	private List<Account> accountList;
	private AccountHandler accountHandler;
	private PasswordHandler pwHandler;
	private BufferedReader bR;
	protected static Integer accountIdx = null;
	
	// Builder
	protected Application(){
		this.accountList = new ArrayList<>();
		bR = new BufferedReader(new InputStreamReader(System.in));
	}
	
	// Methods
	protected void startApplication() throws IOException{
		this.data = Initializer.startInitilizer(this.accountList, 
				AdminAccount.keysList);
		accountHandler = new AccountHandler(this.data, this.accountList, AdminAccount.keysList);
		pwHandler = new PasswordHandler(accountList);
		homeApp();
		Util.writeAccountsOnJSON(accountList, data);
		for(Account acc : accountList) {
			if(acc.getAccountType().compareTo(AccountTypes.ADMINISTRATOR) != 0) {
				UserAccount uA = (UserAccount) acc;
				Util.writePwOnJSON(uA.getPwList(), data, acc.getID());
			}
		}
		Util.writeKeysOnJSON(AdminAccount.keysList.getKeysCollection(), data);
	}
	
	private void homeApp() throws IOException {
		boolean cont = true;
		String choice;
		
		do {
			System.out.println("* Pw_C0ll3ct0r - HOME *\n");
			System.out.println("1. Login.");
			System.out.println("2. Create an account.");
			System.out.println("0. Exit.");
			
			do {
				System.out.print("Your choice: ");
				choice = bR.readLine();
			}while(!checkChoice(2, choice));
			
			switch(choice) {
			case "0":
				cont = false;
				break;
			case "1":
				accountHandler.login();
				if(accountIdx != null) {
					if(accountList.get(accountIdx.intValue())
							.getAccountType().compareTo(AccountTypes.ADMINISTRATOR) == 0)
						yourAdminHome();
					else
						yourUserHome();
				}
				break;
			case "2":
				accountHandler.createAccount();
				break;
			}
			
		}while(cont);
		System.out.println("\nBye bye!");
	}
	
	private void yourUserHome() throws NumberFormatException, IOException {
		boolean cont = true;
		String choice;
		
		do {
			System.out.println("* Pw_C0ll3ct0r - YOUR HOME *\n");
			System.out.println("1. Manage your account.");
			System.out.println("2. Manage your passwords.");
			System.out.println("0. Logout.");
			
			do {
				System.out.print("Your choice: ");
				choice = bR.readLine();
			}while(!checkChoice(2, choice));
			
			switch(choice) {
			case "0":
				accountHandler.logout();
				accountIdx = null;
				cont = false;
				break;
			case "1":
				if(homeAccountManager()) {
					accountHandler.logout();
					cont = false;
				}
				break;
			case "2":
				if(accountList.get(accountIdx.intValue()).getAccountType()
						.compareTo(AccountTypes.USER) == 0)
					homePwManager();
				else
					homePremiumPwManager();
				break;
			}
		}while(cont);
		System.out.println("");
	}

	private void yourAdminHome() throws NumberFormatException, IOException {
		boolean cont = true;
		String choice;
		
		do {
			System.out.println("* Pw_C0ll3ct0r - YOUR HOME *\n");
			System.out.println("1. Manage your account.");
			System.out.println("2. Add a Premium key.");
			System.out.println("3. Remove a Premium key.");
			System.out.println("4. See the Premium keys list.");
			System.out.println("5. See the account list.");
			System.out.println("6. Uninstall the application");
			System.out.println("0. Logout.");
			
			do {
				System.out.print("Your choice: ");
				choice = bR.readLine();
			}while(!checkChoice(6, choice));
			
			switch(choice) {
			case "0":
				accountHandler.logout();
				accountIdx = null;
				cont = false;
				break;
			case "1":
				if(homeAccountManager()) {
					accountHandler.logout();
					cont = false;
				}
				break;
			case "2":
				accountHandler.addPremiumKey();
				break;
			case "3":
				accountHandler.removePremiumKey();
				break;
			case "4":
				accountHandler.printPremiumKeys();
				break;
			case "5":
				accountHandler.printAccountList();
				break;
			case "6":
				Installer.uninstallApp(this.data, 
						accountList.get(accountIdx.intValue()));
			}
		}while(cont);
		System.out.println("");
	}

	private boolean homeAccountManager() throws IOException {
		boolean cont = true;
		String choice;
		
		do {
			System.out.println("\n* Pw_C0ll3ct0r - ACCOUNT MANAGER *\n");
			System.out.println("1. Change your name.");
			System.out.println("2. Change your surname.");
			System.out.println("3. Change your phone number.");
			System.out.println("4. Change your email address.");
			System.out.println("5. Change your account password.");
			System.out.println("6. See your account details.");
			System.out.println("7. Delete your account.");
			System.out.println("0. Come back.");
			
			do {
				System.out.print("Your choice: ");
				choice = bR.readLine();
			}while(!checkChoice(7, choice));
			
			switch(choice) {
			case "0":
				cont = false;
				break;
			case "1":
				accountHandler.changeAccountName();
				break;
			case "2":
				accountHandler.changeAccountSurname();
				break;
			case "3":
				accountHandler.changePhoneNumber();
				break;
			case "4":
				accountHandler.changeEmailAddress();
				break;
			case "5":
				accountHandler.changeAccountPw();
				break;
			case "6":
				accountHandler.printAccountDetails();
				break;
			case "7":
				if(accountHandler.deleteAccount())
					return true;
				break;
			}
		}while(cont);
		System.out.println("");
		return false;
	}

	private void homePwManager() throws IOException {
		boolean cont = true;
		String choice;
		
		do {
			System.out.println("\n* Pw_C0ll3ct0r - PASSWORDS MANAGER *\n");
			System.out.println("1. Insert a new password.");
			System.out.println("2. See a specific password.");
			System.out.println("3. See all passwords");
			System.out.println("0. Come back.");
			
			do {
				System.out.print("Your choice: ");
				choice = bR.readLine();
			}while(!checkChoice(3, choice));
			
			switch(choice) {
			case "0":
				cont = false;
				break;
			case "1":
				pwHandler.addPassword();
				break;
			case "2":
				pwHandler.printPassword();
				break;
			case "3":
				pwHandler.printPasswords();
				break;
			}
		}while(cont);
		System.out.println("");
	}
	
	private void homePremiumPwManager() throws IOException {
		boolean cont = true;
		String choice;
		
		do {
			System.out.println("\n* Pw_C0ll3ct0r - PREMIUM PASSWORD MANAGER *\n");
			System.out.println("1. Insert a new password.");
			System.out.println("2. See a specific password.");
			System.out.println("3. See your password list.");
			System.out.println("4. Change a password.");
			System.out.println("0. Come back.");
			
			do {
				System.out.print("Your choice: ");
				choice = bR.readLine();
			}while(!checkChoice(4, choice));
			
			switch(choice) {
			case "0":
				cont = false;
				break;
			case "1":
				pwHandler.addPassword();
				break;
			case "2":
				pwHandler.printPassword();
				break;
			case "3":
				pwHandler.printPasswords();
				break;
			case "4":
				Password pw = pwHandler.searchPw();
				if(pw != null)
					homePwModifier(pw);
				break;
			}
		}while(cont);
		System.out.println("");
	}
	
	private void homePwModifier(Password pw) throws IOException {
		boolean cont = true;
		String choice;
		
		do {
			System.out.println("\n* Pw_C0ll3ct0r - PASSWORD MODIFIER *\n");
			System.out.println("1. Change the name of " + pw.getID() + ".");
			System.out.println("2. Change the username of " + pw.getID() + ".");
			System.out.println("3. Change the password of " + pw.getID() + ".");
			System.out.println("4. Change the information of " + pw.getID() + ".");
			System.out.println("5. Remove " + pw.getID() + ".");
			System.out.println("0. Come back.");
			
			do {
				System.out.print("Your choice: ");
				choice = bR.readLine();
			}while(!checkChoice(5, choice));
			
			switch(choice) {
			case "0":
				cont = false;
				break;
			case "1":
				pwHandler.changePwName(pw);
				break;
			case "2":
				pwHandler.changePwUsername(pw);
				break;
			case "3":
				pwHandler.changePw(pw);
				break;
			case "4":
				pwHandler.changePwInformation(pw);
				break;
			case "5":
				pwHandler.removePw(pw);
				break;
			}
		}while(cont);
	}
	
	private boolean checkChoice(int up, String choice) {
		Integer numChoice;
		try {
			numChoice = Integer.parseInt(choice);
		}catch(NumberFormatException e) {
			return false;
		}
		
		if(numChoice < 0 || numChoice > up)
			return false;
		else
			return true;
	}
	
}
