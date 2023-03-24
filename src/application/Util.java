package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import accounts.Account;
import accounts.AdminAccount;
import accounts.Password;
import accounts.PremiumKey;
import accounts.PremiumUserAccount;
import accounts.UserAccount;
import encryptors.CaesarEncryptor;
import encryptors.ModCaesarEncryptor;

public class Util {
	
	// Methods
	@SuppressWarnings("unchecked")
	public static void writeAccountsOnJSON(List<Account> list, 
			AppData data) {
		CaesarEncryptor ce = new ModCaesarEncryptor();
		JSONArray JSONList = new JSONArray();
		
		for(Account item : list) {
			String[] crypted = ce.encrypts(item.getPhoneNumber(), 
					item.getAccountEmail(), item.getAccountPw());
			
			JSONObject obj1 = new JSONObject();
			obj1.put("account_ID", item.getID());
			obj1.put("account_type", item.getAccountType().toString());
			obj1.put("name", item.getName());
			obj1.put("surname", item.getSurname());
			obj1.put("phone_number", crypted[0]);
			obj1.put("account_email", crypted[1]);
			obj1.put("account_pw", crypted[2]);
	        
	        JSONObject obj2 = new JSONObject(); 
	        obj2.put("account", obj1);
	        JSONList.add(obj2);
		}
		Util.writeOnJSON(data.getRootPath() + "/accounts_data.json", JSONList);
	}
	
	public static void readAccountsFromJSON(String rootPath,
			List<Account> list) {
		JSONParser parser = new JSONParser();
		
		try {
			FileReader fR = new FileReader(rootPath 
					+ "/accounts_data.json");
			Object obj = parser.parse(fR);
            JSONArray usersDataFile = (JSONArray) obj;
            for(Object us: usersDataFile) {
            	Util.addAccountToList((JSONObject) us, list);
            }
            fR.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(ParseException e) {
			e.printStackTrace();
		}
	}
	
	public static void addAccountToList(JSONObject us, 
			List<Account> list) {
		JSONObject obj = (JSONObject) us.get("account");
		CaesarEncryptor ce = new ModCaesarEncryptor();
		
		String[] decrypted = ce.decrypts((String) obj.get("phone_number"), 
				(String) obj.get("account_email"), 
				(String) obj.get("account_pw"));
		String ID = (String) obj.get("account_ID");
		String accountType = (String) obj.get("account_type");
		String name = (String) obj.get("name");
		String surname = (String) obj.get("surname");
		String phoneNumber = decrypted[0];
		String accountEmail = decrypted[1];
		String accountPw = decrypted[2];
		Account account = null;
		
		switch(accountType) {
		case "ADMINISTRATOR":
			account = new AdminAccount(name, surname, phoneNumber, 
					accountEmail, accountPw);
			break;
		case "USER":
			account = new UserAccount(name, surname, phoneNumber, 
					accountEmail, accountPw, ID);
			break;
		case "PREMIUM_USER":
			account = new PremiumUserAccount(name, surname, phoneNumber, 
					accountEmail, accountPw, ID);
			break;
		}
		list.add(account);
	}
	
	@SuppressWarnings("unchecked")
	public static void writeKeysOnJSON(List<PremiumKey> list, AppData data) {
		CaesarEncryptor ce = new ModCaesarEncryptor();
		JSONArray JSONList = new JSONArray();
		
		for(PremiumKey item : list) {
			String[] crypted = ce.encrypts(item.getKey(), 
					item.getLinkedEmailAddress());
			
			JSONObject obj1 = new JSONObject();
			obj1.put("key_value", crypted[0]);
			obj1.put("linked_email_address", crypted[1]);
			obj1.put("activation_status", 
					Boolean.toString(item.getActivationStatus()));
	        
	        JSONObject obj2 = new JSONObject(); 
	        obj2.put("premiumKey", obj1);
	        JSONList.add(obj2);
		}
		Util.writeOnJSON(data.getRootPath() + "/premium_keys.json", JSONList);
	}
	
	public static void readKeysFromJSON(String rootPath, List<PremiumKey> list) {
		JSONParser parser = new JSONParser();
		
		try {
			FileReader fR = new FileReader(rootPath 
					+ "/premium_keys.json");
			Object obj = parser.parse(fR);
            JSONArray premiumKeysFile = (JSONArray) obj;
            for(Object us: premiumKeysFile) {
            	Util.addKeyToList((JSONObject) us, list);
            }
            fR.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(ParseException e) {
			e.printStackTrace();
		}
	}
	
	public static void addKeyToList(JSONObject us, List<PremiumKey> list) {
		JSONObject obj = (JSONObject) us.get("premiumKey");
		CaesarEncryptor ce = new ModCaesarEncryptor();
		
		String[] decrypted = ce.decrypts((String) obj.get("key_value"), 
				(String) obj.get("linked_email_address"));
		String status = (String)obj.get("activation_status");
		
		list.add(new PremiumKey(decrypted[0], decrypted[1], status));
	}
	
	@SuppressWarnings("unchecked")
	public static void writePwOnJSON(List<Password> list, AppData data, String userID) {
		CaesarEncryptor ce = new ModCaesarEncryptor();
		JSONArray JSONList = new JSONArray();
		
		for(Password item : list) {
			String[] crypted = ce.encrypts(item.getUsername(), 
					item.getPw());
			
			JSONObject obj1 = new JSONObject();
			obj1.put("ID", item.getID());
			obj1.put("name", item.getName());
			obj1.put("username", crypted[0]);
			obj1.put("pw", crypted[1]);
	        
	        JSONObject obj2 = new JSONObject(); 
	        obj2.put("password", obj1);
	        JSONList.add(obj2);
		}
		Util.writeOnJSON(data.getPwPath() + "/pw_" + userID + ".json", JSONList);
	}
	
	public static void readPwFromJSON(String pwPath, String userID, 
			List<Password> list) {
		JSONParser parser = new JSONParser();
	
		try {
			FileReader fR = new FileReader(pwPath + "/pw_" + userID + ".json");
			Object obj = parser.parse(fR);
            JSONArray pwFile = (JSONArray) obj;
            for(Object us: pwFile) {
            	Util.addPwToList((JSONObject) us, list);
            }
            fR.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(ParseException e) {
			e.printStackTrace();
		}
	}
	
	public static void addPwToList(JSONObject us, List<Password> list) {
		JSONObject obj = (JSONObject) us.get("password");
		CaesarEncryptor ce = new ModCaesarEncryptor();
		
		String[] decrypted = ce.decrypts((String) obj.get("pw"));
		String ID = (String)obj.get("ID");
		String name = (String)obj.get("name");
		String username = (String)obj.get("username");
		String information = (String)obj.get("information");
		
		list.add(new Password(ID, name, username, decrypted[0], information));
	}
	
	public static void writeOnJSON(String filePath, JSONArray array) {
		try(FileWriter appDataFile = new FileWriter(filePath)){
            appDataFile.write(array.toJSONString()); 
            appDataFile.flush();
            appDataFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static void deleteDirectory(String rootPath) {
		try {
		      File directory = new File(rootPath);
		      File[] files = directory.listFiles();
		      for(File file : files) {
		    	  file.delete();
		      }
		      directory.delete();
		}catch(Exception e) {
			e.getStackTrace();
		}
	}
	
}
