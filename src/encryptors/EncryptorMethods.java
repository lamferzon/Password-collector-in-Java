package encryptors;

public interface EncryptorMethods {
	
	// Fields
	int START = 32;
	int END = 127;
	
	// Methods
	public String[] encrypts(String... strs);
	public String[] decrypts (String... strs);
	
}
