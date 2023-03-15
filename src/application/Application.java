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
	
	// Builder
	protected Application(){
		this.accountList = new ArrayList<>();
	}
	
	// Methods
	protected void startApplication() throws IOException{
		this.data = Initializer.startInitilizer(this.accountList);
		//System.out.println(this.accountList.get(1));
		home();
		Util.writeAccountsOnJSON(accountList, data);
	}
	
	private void home() throws NumberFormatException, IOException {
		BufferedReader bR = new BufferedReader(new InputStreamReader(System.in));
		
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
				Handler.login(this.accountList);
				break;
			case 2:
				Handler.createAccount(this.accountList, this.data);
				break;
			}
			
		}while(cont);
		System.out.println("\nBye bye!");
	}
	
}
