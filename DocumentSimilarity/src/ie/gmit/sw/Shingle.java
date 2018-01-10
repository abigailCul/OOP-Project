package ie.gmit.sw;

public class Shingle {

	private int docId;
	private int hashcode;
	//Constructor + Getters/Setters
	
	public Shingle(int docId, int hashcode) {
		super();
		this.docId = docId;
		this.hashcode = hashcode;
		
}
	
	public int getDocID() {
		return docId;
	}
	public void setDocID(int docID) {
		this.docId = docID;
	}
	
	public int getHashCode() {
		return hashcode;
	}
	public void setHashCode(int hashCode) {
		this.hashcode = hashCode;
	}
	
	
}
