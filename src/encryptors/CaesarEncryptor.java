package encryptors;

public class CaesarEncryptor implements EncryptorMethods{
	
	// Fields
	private static final int MIN_LAG = 3;
	
	//Methods
	public String[] encrypts(String... strs) {
		String[] results = new String[strs.length];
		for(int i = 0; i < strs.length; i++) {
			results[i] = startAlgorithm(strs[i], MIN_LAG);
		}
		return results;
	}

	public String[] decrypts(String... strs) {
		String[] results = new String[strs.length];
		for(int i = 0; i < strs.length; i++) {
			results[i] = startAlgorithm(strs[i], -MIN_LAG);
		}
		return results;
	}
	
	protected String startAlgorithm(String str, int lag) {
		String res = "";
		for(int i = 0; i < str.length(); i++) {
			int char2Num = ((int) str.charAt(i)) + lag;
			if(lag > 0) {
				if(char2Num > END) {
					int diff = char2Num - END;
					char2Num = START + diff - 1;
				}
			}else {
				if(char2Num < START) {
					int diff = START - char2Num;
					char2Num = END - diff + 1;
				}
			}
			res  += String.valueOf((char)char2Num);
		}
		return res;
	}
	
}
