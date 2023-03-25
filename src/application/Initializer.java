package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.InvalidPathException;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import accounts.Account;
import accounts.UserAccount;
import accounts.*;

class Initializer {
	
	// Methods
	protected static AppData startInitilizer(List<Account> accountList,
			KeysCollection collector) throws IOException {
		BufferedReader bR = new BufferedReader(new InputStreamReader(System.in));
		AppData data = null;
		
		System.out.println("** Pw_C0ll3ct0r **\n");
		System.out.println("Copyright 2023 Lorenzo Leoni (UniBG)\n");
		
		String startPath;
		do {
			System.out.print("Starting path: ");
			startPath = bR.readLine();
		}while(Initializer.checkStartPath(startPath));
		
		File findAppDataFile = new File(startPath + "/app_data.json");
		File findRootDir = new File(startPath + "/Pw_C0ll3ct0r");
		
		if(!findAppDataFile.exists() && !findRootDir.exists()) {
			System.out.print("In this path doesn't exist an installation of " + 
					"Pw_C0lle3ct0r. Do you want to install it? (Y/N) ");
			String resp = bR.readLine();
			
			if(resp.toUpperCase().compareTo(String.valueOf('Y')) == 0) {
				String rootPath = startPath + "/Pw_C0ll3ct0r";
				data = Installer.installApp(rootPath, accountList);
			}else {
				System.out.println("\nExecution terminated.");
				System.exit(0);
			}
		}else {
			String rootPath = startPath;
			if(findRootDir.exists())
				rootPath += "/Pw_C0ll3ct0r";
			
			data = readAppDataFromJSON(rootPath + "/app_data.json");
			UserAccount.setPwPath(data.getPwPath());
			Util.readAccountsFromJSON(rootPath, accountList);
			Util.readKeysFromJSON(rootPath, collector.getKeysCollection());
			System.out.println("");
		}
		setCounter(accountList);
		return data;
	}
	
	private static AppData readAppDataFromJSON(String appDataFilePath) {
		JSONParser parser = new JSONParser();
		AppData data = new AppData();
		
		try {
			FileReader fR = new FileReader(appDataFilePath);
			Object obj = parser.parse(fR);
            JSONArray appDataFile = (JSONArray) obj;
            JSONObject item = (JSONObject) appDataFile.get(0);
            JSONObject instDet = (JSONObject) item.get("installationDetails");
            data.setRootPath((String) instDet.get("root_path"));
            data.setPwPath((String) instDet.get("passwords_path"));
            data.setInstallationDatetime((String) instDet.get("installation_datetime"));
            fR.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	private static boolean checkStartPath(String path) {
		boolean invalid = false;
		
		try {
			FileSystems.getDefault().getPath(path);
		}catch(InvalidPathException e){
			System.out.println("Attention: invalid path. Please insert "
					+ "another one.");
			invalid = true;
		}
		return invalid;
	}
	
	private static void setCounter(List<Account> accountList) {
		Account lastAccount = accountList.get(accountList.size()-1);
		Integer counter = Integer.parseInt(lastAccount.getID().substring(3));
		UserAccount.setProgNum(counter.intValue() + 1);
	}
	
}
