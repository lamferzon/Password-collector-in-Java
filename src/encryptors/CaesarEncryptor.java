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
	
	public static void main(String[] args){
		System.out.println("Original Caesar encryptor\n");
		CaesarEncryptor ce = new CaesarEncryptor();
		String s1 = "Lamferzon-1998Ferrari4e!";
		String s2 = "G00glareCh3v3rb0!1998";
		String s3 = "Ferrari4e-2023";
		String[] crypted = ce.encrypts(s1, s2, s3);
		String[] decrypted = ce.decrypts(crypted);
		for(int i = 0; i < crypted.length; i++) {
			System.out.println(i + ". " + crypted[i] + " <--> " + decrypted[i]);
		}
	}

}
