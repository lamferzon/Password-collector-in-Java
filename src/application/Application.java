package application;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import accounts.*;

class Application {
	
	// Data
	private AppData data;
	private ArrayList<Account> accountList;
	
	// Constructor
	protected Application(){
		this.accountList = new ArrayList<>();
	}
	
	// Methods
	protected void startApplication() throws IOException{
		this.data = Initializer.startInitilizer(this.accountList);
		home();
	}
	
	private void home() throws NumberFormatException, IOException {
		BufferedReader bR = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("\n* Pw_C0ll3ct0r home *\n");
		int choice;
		System.out.println("1. Login.");
		System.out.println("2. Create an account.");
		System.out.println("0. Exit.");
		do {
			System.out.print("Your choice: ");
			choice = Integer.parseInt(bR.readLine());
		}while(choice < 0 || choice > 2);
	}
	
}
