package encryptors;

import java.util.Random;

public class ModCaesarEncryptor extends CaesarEncryptor{
	
	// Fields
	protected static final int MIN_LAG = 7;
	protected static final int MAX_LAG = 25;
	protected int cur_max_lag;
	protected Random ran;
	
	// Builders
	public ModCaesarEncryptor(){
		cur_max_lag = 25;
		ran = new Random();
	}
	
	public ModCaesarEncryptor(int max_lag){
		if(max_lag > MAX_LAG)
			this.cur_max_lag = MAX_LAG;
		else if(max_lag < MIN_LAG)
			this.cur_max_lag = MIN_LAG;
		else
			this.cur_max_lag = MAX_LAG;
		ran = new Random();
	}
	
	// Methods
	public String[] encrypts(String... strs) {
		String[] results = new String[strs.length];
		for(int i = 0; i < strs.length; i++) {
			int lag = getLag();
			char lagStr = (char)(lag*5 + 2);
			results[i] = startAlgorithm(strs[i], lag) + lagStr;
		}
		return results;
	}

	public String[] decrypts(String... strs) {
		String[] redStrs = new String[strs.length];
		String[] results = new String[strs.length];
		for(int i = 0; i < strs.length; i++) {
			int lag = (int)strs[i].charAt(strs[i].length()-1);
			lag = (lag - 2)/5;
			redStrs[i] = strs[i].substring(0, strs[i].length()-1);
			results[i] = startAlgorithm(redStrs[i], -lag);
		}
		return results;
	}

	protected int getLag() {
		int lag = MIN_LAG + ran.nextInt(cur_max_lag - MIN_LAG + 1);
		return lag;
	}
	
}
