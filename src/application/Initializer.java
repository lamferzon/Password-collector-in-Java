package application;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import accounts.Account;
import accounts.AdminAccount;

class Initializer {
	
	// Methods
	protected static AppData startInitilizer(ArrayList<Account> accountList) throws IOException {
		BufferedReader bR = new BufferedReader(new InputStreamReader(System.in));
		AppData data = null;
		
		System.out.println("** Pw_C0ll3ct0r **\n");
		System.out.print("Starting path: ");
		String startPath = bR.readLine();
		File findAppDataFile = new File(startPath + "/app_data.json");
		File findRootDir = new File(startPath + "/Pw_C0ll3ct0r");
		
		if(!findAppDataFile.exists() && !findRootDir.exists()) {
			System.out.print("In this path doesn't exist an installation of " + 
					"Pw_C0lle3ct0r. Do you want to install it? (Y/N) ");
			String resp = bR.readLine();
			
			if(resp.toUpperCase().compareTo(String.valueOf('Y')) == 0) {
				String rootPath = startPath + "/Pw_C0ll3ct0r";
				data = Installer.installApp(rootPath, accountList);
				System.out.println("Welcome!");
			}else
				System.out.println("\nExecution terminated.");
			
		}else {
			String rootPath = startPath;
			
			if(findRootDir.exists())
				rootPath += "/Pw_C0ll3ct0r";
			
			data = readAppDataFromJSON(rootPath + "/app_data.json");
			readAccountsFromJSON(rootPath, accountList);	
		}
		return data;
	}
	
	private static AppData readAppDataFromJSON(String appDataFilePath) {
		JSONParser parser = new JSONParser();
		AppData data = new AppData();
		
		try {
			System.out.println(appDataFilePath);
			Object obj = parser.parse(new FileReader(appDataFilePath));
            JSONArray appDataFile = (JSONArray) obj;
            JSONObject item = (JSONObject) appDataFile.get(0);
            JSONObject instDet = (JSONObject) item.get("installationDetails");
            data.setRootPath((String) instDet.get("root_path"));
            data.setPwPath((String) instDet.get("passwords_path"));
            data.setInstallationDatetime((String) instDet.get("installation_datetime"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	private static void readAccountsFromJSON(String rootPath,
			ArrayList<Account> accountList) {
		JSONParser parser = new JSONParser();
		
		try {
			Object obj = parser.parse(new FileReader(rootPath + "/accounts_data.json"));
            JSONArray usersDataFile = (JSONArray) obj;
            for(Object us: usersDataFile)
            	addAccountToList((JSONObject) us, accountList);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(ParseException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private static void addAccountToList(JSONObject us, 
			ArrayList<Account> accountList) {
		JSONObject obj = (JSONObject) us.get("account");
		String ID = (String) obj.get("account_ID");
		String accountType = (String) obj.get("account_type");
		String name = (String) obj.get("name");
		String surname = (String) obj.get("surname");
		String phoneNumber = (String) obj.get("phone_number");
		String accountEmail = (String) obj.get("account_email");
		String accountPw = (String) obj.get("account_pw");
		Account p = null;
		switch(accountType) {
		case "ADMINISTRATOR":
			p = new AdminAccount(name, surname, phoneNumber, accountEmail, 
					accountPw, false);
			break;
		case "USER":
			// TODO
			break;
		case "PREMIUM_USER":
			// TODO
			break;
		}
		accountList.add(p);
	}
	
}
