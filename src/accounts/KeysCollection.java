package accounts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KeysCollection {
	
	// Fields
	private List<PremiumKey> collection;
	
	// Builder
	public KeysCollection() {
		collection = new ArrayList<>();
	}
	
	// Methods
	public boolean add(PremiumKey pK) {
		if(unique(pK)) {
			collection.add(pK);
			Collections.sort(collection);
			return true;
		}else {
			return false;
		}
	}
	
	public boolean remove(String email) {
		int idx = searchByEmail(email);
		if(idx >= 0) {
			collection.remove(idx);
			return true;
		}else {
			return false;
		}
	}
	
	public boolean unique(PremiumKey pK) {
		for(PremiumKey el : collection) {
			if(el.getKey().compareTo(pK.getKey()) == 0 || 
					el.getLinkedEmailAddress()
					.compareTo(pK.getLinkedEmailAddress()) == 0)
				return false;
		}
		return true;
	}
	
	public int searchByEmail(String email) {
		for(PremiumKey el : collection) {
			if(el.getLinkedEmailAddress().compareTo(email) == 0)
				return collection.indexOf(el);
		}
		return -1;
	}
	
	public List<PremiumKey> getKeysCollection() {
		return this.collection;
	}
	
	public PremiumKey getPremiumKey(int idx) {
		return collection.get(idx);
	}
	
	public String toString() {
		String result = "";
		if(collection.size() == 0) {
			result = "NO keys.\n";
		}else {
			for(int i = 0; i < collection.size(); i++) {
				result += i + ". " + collection.get(i) + "\n";
			}
		}
		return result;
	}
	
}
