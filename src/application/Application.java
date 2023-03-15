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
	private Handler handler;
	BufferedReader bR;
	
	// Builder
	protected Application(){
		this.accountList = new ArrayList<>();
		bR = new BufferedReader(new InputStreamReader(System.in));
	}
	
	// Methods
	protected void startApplication() throws IOException{
		this.data = Initializer.startInitilizer(this.accountList);
		handler = new Handler(this.data, this.accountList);
		homeApp();
		Util.writeAccountsOnJSON(accountList, data);
	}
	
	private void homeApp() throws NumberFormatException, IOException {
		boolean cont = true;
		
		do {
			int choice;
			System.out.println("* Pw_C0ll3ct0r home *\n");
			System.out.println("1. Login.");
			System.out.println("2. Create an account.");
			System.out.println("0. Exit.");
			
			do {
				System.out.print("Your choice: ");
				choice = Integer.parseInt(bR.readLine());
			}while(choice < 0 || choice > 2);
			
			switch(choice) {
			case 0:
				cont = false;
				break;
			case 1:
				handler.login();
				homeAccount();
				break;
			case 2:
				handler.createAccount();
				break;
			}
			
		}while(cont);
		System.out.println("\nBye bye!");
	}
	
	private void homeAccount() throws NumberFormatException, IOException {
		boolean cont = true;
		
		do {
			int choice;
			System.out.println("* Pw_C0ll3ct0r home account *\n");
			System.out.println("1. Manage your account.");
			System.out.println("2. Manage your passwords.");
			System.out.println("0. Logout.");
			
			do {
				System.out.print("Your choice: ");
				choice = Integer.parseInt(bR.readLine());
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
	
}
