package encryptors;

public class CaesarEncryptor implements EncryptorMethods{
	
	// Fields
	protected static final int MIN_LAG = 3;
	
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
		String res = null;
		int[] numVect = new int[str.length()+1];
		for(int i = 0; i < str.length(); i++) {
			numVect[i] = ((int) str.charAt(i)) + lag;
			if(res == null)
				res  = String.valueOf((char)numVect[i]);
			else
				res = res + String.valueOf((char)numVect[i]);
		}
		return res;
	}

}
