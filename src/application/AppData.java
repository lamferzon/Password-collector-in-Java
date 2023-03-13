package application;

import java.time.*;
import java.time.format.DateTimeFormatter;

class AppData {
	
	// Data
	private String rootPath;
	private String pwPath;
	private LocalDateTime datetime;
	private DateTimeFormatter formatter;
	
	
	// Constructors
	protected AppData() {
		this.formatter = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
	}
	
	protected AppData(String rootPath) {
		this.formatter = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
		this.rootPath = rootPath;
		this.pwPath = rootPath + "/Collections";
		this.datetime = LocalDateTime.now();
	}
	
	// Methods
	protected void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}
	
	protected void setPwPath(String pwPath) {
		this.pwPath = pwPath;
	}
	
	protected void setInstallationDatetime(String datetime) {
		this.datetime = LocalDateTime.parse(datetime, formatter);
	}
	
	protected String getRootPath() {
		return this.rootPath;
	}
	
	protected String getPwPath() {
		return this.pwPath;
	}
	
	protected LocalDateTime getInstallationDatetime() {
		return datetime;
	}
	
	public String toString() {
		String res = "Root path: " + this.rootPath + "\n" + "Passwords path: " + 
				this.pwPath + "\n" + "Installation datetime: " + formatter.format(datetime);
		return res;
	}
	
}
