
public class Entry {

	static int vocab=26;
	int[] cArray;
	int count;
	Entry[] child;
	/**
	 * 
	 */
	public Entry() {

		count = 0;
		cArray = new int[vocab];
		child = new Entry[vocab];
	}
	

}
