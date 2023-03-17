package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.json.simple.*;
import accounts.*;

class Installer {
	
	// Methods
	protected static AppData installApp(String rootPath, 
			List<Account> accountList) throws IOException {
		
		// Part1
		AppData data = new AppData(rootPath);
		System.out.println("\n* Pw_C0ll3ct0r INSTALLER *\n");
		fillRootDir(rootPath);
		writeAppDataOnJSON(data);
		
		// Part 2
		Account admin = createAdmin();
		accountList.add(admin);
		System.out.println("\nInstallation details: \n" + data + "\n");
		return data;
	}
	
	private static void fillRootDir(String rootPath) throws IOException {
		File rootDir = new File(rootPath);
		rootDir.mkdirs();
		System.out.println("1. Root directory created.");
		Path p = FileSystems.getDefault().getPath(rootPath);
		Files.setAttribute(p, "dos:hidden", true);
		
		File usersFile = new File(rootPath + "/accounts_data.json");
		usersFile.createNewFile();
		System.out.println("2. Accounts-data file created.");
		
		File appDataFile = new File(rootPath + "/app_data.json");
		appDataFile.createNewFile();
		System.out.println("3. Application-data file created.");
		
		File collDir = new File(rootPath + "/Collections");
		collDir.mkdirs();
		System.out.println("4. Collections directory created.");
	}
	
	@SuppressWarnings("unchecked")
	private static void writeAppDataOnJSON(AppData data) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
		
		JSONObject obj1 = new JSONObject();
		obj1.put("root_path", data.getRootPath());
		obj1.put("passwords_path", data.getPwPath());
		obj1.put("installation_datetime", 
				formatter.format(data.getInstallationDatetime()).toString());
		
		JSONObject obj2 = new JSONObject();
		obj2.put("installationDetails", obj1);
		JSONArray array = new JSONArray();
		array.add(obj2);
		
		Util.writeOnJSON(data.getRootPath() + "/app_data.json", array);
		System.out.println("5. Application-data file filled.");
	}
	
	private static Account createAdmin() throws IOException {
		System.out.println("\nCreate an administrator account:");
		
		BufferedReader bR = new BufferedReader(new InputStreamReader(System.in));
		String name;
		String surname;
		String phoneNumber;
		String accountEmail;
		String accountPw;
		
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
		}while(phoneNumber.compareTo("") == 0 || 
				Util.checkPhoneNumber(phoneNumber));
		do {
			System.out.print("Account email: ");
			accountEmail = bR.readLine();
		}while(accountEmail.compareTo("") == 0);
		do {
			System.out.print("Account password: ");
			accountPw = bR.readLine();
		}while(accountPw.compareTo("") == 0 || Util.checkPw(accountPw));
		
		return new AdminAccount(name, surname, phoneNumber, 
				accountEmail, accountPw);	
	}
	
}
