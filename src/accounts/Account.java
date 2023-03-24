package accounts;

import encryptors.CaesarEncryptor;
import encryptors.ModCaesarEncryptor;

public abstract class Account implements Comparable<Account>{
	
	// Fields
	protected String ID;
	protected AccountTypes accountType;
	protected String name;
	protected String surname;
	protected String phoneNumber;
	protected String accountEmail;
	protected String accountPw;
	protected static CaesarEncryptor cE = new ModCaesarEncryptor(15);
	
	// Builder
	public Account(String name, String surname, String phoneNumber,
			String accountEmail, String accountPw) {
		this.name = name;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.accountEmail = accountEmail;
		this.accountPw = accountPw;
	}
	
	// Getters
	public String getID() {
		return this.ID;
	}
	
	public AccountTypes getAccountType() {
		return this.accountType;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getSurname() {
		return this.surname;
	}
	
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public String getAccountEmail() {
		return this.accountEmail;
	}
	
	public String getAccountPw() {
		return this.accountPw;
	}
	
	/// Modifiers
	public void modifyName(String name) {
		this.name = name;
	}
	
	public void modifySurname(String surname) {
		this.surname = surname;
	}
	
	public void modifyPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public void modifyAccountEmail(String accountEmail) {
		this.accountEmail = accountEmail;
	}
	
	public void modifyAccountPw(String accountPw) {
		this.accountPw = accountPw;
	}
	
	public int compareTo(Account a) {
		return a.ID.compareTo(a.getID());
		
	}
	
	public String toString() {
		String pwEncrypted = cE.encrypts(this.accountPw)[0];
		return "Account ID: " + this.ID + "\n" + "User type: " + 
				this.accountType + "\n" + "Name: " + this.name + "\n" + 
				"Surname: " + this.surname + "\n" + "Phone number: " + 
				this.phoneNumber + "\n" + "Account email: " + 
				this.accountEmail + "\n" + "Account password: " + pwEncrypted;
	}
	
}
