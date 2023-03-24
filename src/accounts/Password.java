package accounts;

class Password {
	
	// Fields
	private String ID;
	private String name;
	private String username;
	private String pw;
	private String information;
	
	// Builders
	public Password(String ID, String name, String username, String pw) {
		this.setFields(ID, name, username, pw);
		this.information = "empty field";
	}
	
	public Password(String ID, String name, String username, String pw, String information) {
		this.setFields(ID, name, username, pw);
		this.information = information;
	}
	
	private void setFields(String ID, String name, String username, String pw) {
		this.ID = ID;
		this.name = name;
		this.username = username;
		this.pw = pw;
	}
	
	// Getters
	public String getID() {
		return this.ID;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPw() {
		return this.pw;
	}
	
	public String getInformation() {
		return this.information;
	}
	
	// Modifiers
	public void modifyName(String newName) {
		name = newName;
	}
	
	public void modifyUsername(String newUsername) {
		username = newUsername;
	}
	
	public void modifyPw(String newPw) {
		pw = newPw;
	}
	
	public void modifyInformation(String newInformation) {
		information = newInformation;
	}
	
}
