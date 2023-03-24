package accounts;

public class PremiumKey implements Comparable<PremiumKey>{
	
	// Fields
	private String key;
	private String linkedEmailAddress;
	private boolean activated;
	
	// Builders
	public PremiumKey(String key, String linkedEmailAddress) {
		this.key = key;
		this.linkedEmailAddress = linkedEmailAddress;
		activated = false;
	}
	
	public PremiumKey(String key, String linkedEmailAddress, String status) {
		this.key = key;
		this.linkedEmailAddress = linkedEmailAddress;
		if(status.compareTo("true") == 0)
			activated = true;
		else
			activated = false;
	}
	
	// Getters
	public String getKey() {
		return key;
	}
	
	public String getLinkedEmailAddress(){
		return linkedEmailAddress;
	}
	
	public boolean getActivationStatus() {
		return activated;
	}
	
	// Setters
	public void activate() {
		activated = true;
	}

	@Override
	public int compareTo(PremiumKey o) {
		return key.compareTo(o.getKey());
	}
	
	public String toString() {
		return "(" + key + "; " + linkedEmailAddress + "; " + activated + ")";
	}
	
}
