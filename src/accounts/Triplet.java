package accounts;

public class Triplet<K extends Comparable<K>, V, I> 
	implements Comparable<Triplet<K, ?, ?>>{
	
	// Data
	private K key;
	private V value;
	private I information;
	
	// Constructor
	public Triplet(K key, V value, I information){
		this.key = key;
		this.value = value;
		this.information = information;
	}
	
	// Getters
	public K getKey() {
		return this.key;
	}
	
	public V getValue() {
		return this.value;
	}
	
	public I getInformation() {
		return this.information;
	}
	
	public void modifyKey(K newKey) {
		this.key = newKey;
	}
	
	// Modifiers
	public void modifyValue(V newValue) {
		this.value = newValue;
	}
	
	public void modifyInformation(I newInformation) {
		this.information = newInformation;
	}
	
	@Override
	public int compareTo(Triplet<K, ?, ?> o) {
		return this.key.compareTo(o.getKey());
	}
	
	public String toString() {
		return "[" + this.key + ", " + this.value + ", " + this.information +
				"]";
	}
	
}
