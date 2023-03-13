package encryptors;
import java.util.Random;

public class ModCaesarEncryptor extends CaesarEncryptor{
	
	// Data
	protected int max_lag;
	protected Random ran;
	
	// Methods
	public ModCaesarEncryptor(){
		max_lag = 10;
		ran = new Random();
	}
	
	public ModCaesarEncryptor(int max_lag){
		this.max_lag = max_lag;
		ran = new Random();
	}

	public String[] encrypts(String... strs) {
		String[] results = new String[strs.length];
		for(int i = 0; i < strs.length; i++) {
			int lag = getLag();
			char lagStr = (char)(lag*10 + 3);
			results[i] = startAlgorithm(strs[i], lag) + lagStr;
		}
		return results;
	}

	public String[] decrypts(String... strs) {
		String[] redStrs = new String[strs.length];
		String[] results = new String[strs.length];
		for(int i = 0; i < strs.length; i++) {
			int lag = (int)strs[i].charAt(strs[i].length()-1);
			lag = (lag - 3)/10;
			redStrs[i] = strs[i].substring(0, strs[i].length()-1);
			results[i] = startAlgorithm(redStrs[i], -lag);
		}
		return results;
	}

	protected int getLag() {
		int lag = MIN_LAG + ran.nextInt(max_lag - MIN_LAG + 1);
		return lag;
	}
	
	public static void main(String[] args){
		System.out.println("Modified Caesar encryptor\n");
		ModCaesarEncryptor mce = new ModCaesarEncryptor();
		String s1 = "Lamferzon-1998Ferrari4e!";
		String s2 = "G00glareCh3v3rb0!1998";
		String s3 = "Ferrari4e-2023";
		String[] crypted = mce.encrypts(s1, s2, s3);
		String[] decrypted = mce.decrypts(crypted);
		for(int i = 0; i < crypted.length; i++) {
			System.out.println(i + ". " + crypted[i] + " <--> " + decrypted[i]);
		}
	}
	
}
