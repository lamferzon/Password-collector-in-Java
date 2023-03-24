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
	private AccountHandler handler;
	private Integer accountIndex;
	BufferedReader bR;
	
	// Builder
	protected Application(){
		this.accountList = new ArrayList<>();
		this.accountIndex = null;
		bR = new BufferedReader(new InputStreamReader(System.in));
	}
	
	// Methods
	protected void startApplication() throws IOException{
		this.data = Initializer.startInitilizer(this.accountList, 
				AdminAccount.keysList);
		handler = new AccountHandler(this.data, this.accountList, AdminAccount.keysList);
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
		int choice = 0;
		
		do {
			System.out.println("* Pw_C0ll3ct0r - HOME *\n");
			System.out.println("1. Login.");
			System.out.println("2. Create an account.");
			System.out.println("0. Exit.");
			
			do {
				System.out.print("Your choice: ");
				try {
					choice = Integer.parseInt(bR.readLine());
				}catch(NumberFormatException e) {
					e.setStackTrace(null);
				}
			}while(choice < 0 || choice > 2);
			
			switch(choice) {
			case 0:
				cont = false;
				break;
			case 1:
				this.accountIndex = handler.login();
				if(this.accountIndex != null) {
					if(accountList.get(this.accountIndex.intValue())
							.getAccountType().compareTo(AccountTypes.ADMINISTRATOR) == 0)
						yourAdminHome();
					else
						yourUserHome();
				}
				break;
			case 2:
				handler.createAccount();
				break;
			}
			
		}while(cont);
		System.out.println("\nBye bye!");
	}
	
	private void yourUserHome() throws NumberFormatException, IOException {
		boolean cont = true;
		int choice = 0;
		
		do {
			System.out.println("* Pw_C0ll3ct0r - YOUR HOME *\n");
			System.out.println("1. Manage your account.");
			System.out.println("2. Manage your passwords.");
			System.out.println("0. Logout.");
			
			do {
				System.out.print("Your choice: ");
				try {
					choice = Integer.parseInt(bR.readLine());
				}catch(NumberFormatException e) {
					e.setStackTrace(null);
				}
			}while(choice < 0 || choice > 2);
			
			switch(choice) {
			case 0:
				handler.logout();
				this.accountIndex = null;
				cont = false;
				break;
			case 1:
				if(homeAccountManager()) {
					handler.logout();
					cont = false;
				}
				break;
			case 2:
				if(accountList.get(this.accountIndex.intValue()).getAccountType()
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
		int choice = 0;
		
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
				try {
					choice = Integer.parseInt(bR.readLine());
				}catch(NumberFormatException e) {
					e.setStackTrace(null);
				}
			}while(choice < 0 || choice > 6);
			
			switch(choice) {
			case 0:
				handler.logout();
				this.accountIndex = null;
				cont = false;
				break;
			case 1:
				if(homeAccountManager()) {
					handler.logout();
					cont = false;
				}
				break;
			case 2:
				handler.addPremiumKey();
				break;
			case 3:
				handler.removePremiumKey();
				break;
			case 4:
				handler.printPremiumKeys();
				break;
			case 5:
				handler.printAccountList();
				break;
			case 6:
				Installer.uninstallApp(this.data, 
						accountList.get(this.accountIndex.intValue()));
			}
		}while(cont);
		System.out.println("");
	}

	private boolean homeAccountManager() throws IOException {
		boolean cont = true;
		int choice = 0;
		
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
				try {
					choice = Integer.parseInt(bR.readLine());
				}catch(NumberFormatException e) {
					e.setStackTrace(null);
				}
			}while(choice < 0 || choice > 7);
			
			switch(choice) {
			case 0:
				cont = false;
				break;
			case 1:
				handler.changeAccountName();
				break;
			case 2:
				handler.changeAccountSurname();
				break;
			case 3:
				handler.changePhoneNumber();
				break;
			case 4:
				handler.changeEmailAddress();
				break;
			case 5:
				handler.changeAccountPw();
				break;
			case 6:
				handler.printAccountDetails();
				break;
			case 7:
				if(handler.deleteAccount())
					return true;
				break;
			}
		}while(cont);
		System.out.println("");
		return false;
	}

	private void homePwManager() throws IOException {
		boolean cont = true;
		int choice = 0;
		
		do {
			System.out.println("* Pw_C0ll3ct0r - PASSWORDS MANAGER *\n");
			System.out.println("1. Add a new password.");
			System.out.println("2. See your passwords.");
			System.out.println("0. Come back.");
			
			do {
				System.out.print("Your choice: ");
				try {
					choice = Integer.parseInt(bR.readLine());
				}catch(NumberFormatException e) {
					e.setStackTrace(null);
				}
			}while(choice < 0 || choice > 2);
			
			switch(choice) {
			case 0:
				cont = false;
				break;
			case 1:
				// TODO
				break;
			case 2:
				// TODO
				break;
			}
		}while(cont);
		System.out.println("");
	}
	
	private void homePremiumPwManager() {
		// TODO Auto-generated method stub
		
	}
	
}
