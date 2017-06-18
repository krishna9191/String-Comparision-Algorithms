/**
 * 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * @author RK
 *
 */
public class Trie {

	Entry root;

	/**
	 * 
	 */
	public Trie() {
		
		root = new Entry();
	}
	void remove(String word)
	{
		if(contains(word))
		{
			Entry traverse = root;
			int index;
			for(int i=0;;i++)
			{	 index = toNum(word.charAt(i));
			if(traverse.cArray[index]==0)
				{
						System.out.println(word+" Doesn't exist in Trie");
				}
				traverse.cArray[index]--;
				traverse.count--;
				if(i<word.length()-1)
					{	if(traverse.child[index]==null)
							{
								break;
							}
						traverse = traverse.child[index];
					}
				else
				{	if(Q2Driver.print)
						System.out.println(word+" successfully removed");
					break;
				}
			}
		}
		else
		{	if(Q2Driver.print)
				System.out.println(word+" doesn't Exist");
		}
	}
	
	void add(String word)
	{
		Entry traverse = root;
		int index;
		for(int i=0;;i++)
		{	 index = toNum(word.charAt(i));
			traverse.cArray[index]++;
			traverse.count++;
			if(i<word.length()-1)
				{	if(traverse.child[index]==null)
						{
							traverse.child[index] = new Entry();
						}
					traverse = traverse.child[index];
				}
			else
			{
				break;
			}
		}
		if(Q2Driver.print)
			System.out.println(word+" successfully added");
	}

	private int toNum(char c) {

		return ((int)Character.toLowerCase(c))-97;
	}
	
	boolean contains(String word)
	{
		Entry traverse = root;
		int index;
		for(int i=0;;i++)
		{	index = toNum(word.charAt(i));	
		if(traverse.cArray[index]==0)
			return false;
			if(i<word.length()-1)
				{	if(traverse.child[index]==null)
						{
							return false;
						}
					traverse = traverse.child[index];
				}
			else
			{
				break;
			}
		}
		return true;
		
	}
	
	
	void importFromFile(String file) throws FileNotFoundException
	{
		Scanner in = new Scanner(new File(file));
		while(in.hasNext())
		{
			add(in.next());
		}
		if(Q2Driver.print)
			System.out.println("Import Successful");
		in.close();
	}
	int prefix(String word)
	{
		Entry traverse = root;
		int index;
		for(int i=0;;i++)
		{	 index = toNum(word.charAt(i));
			if(traverse.cArray[index]==0)
				return 0;
			if(i<word.length()-1)
				{	if(traverse.child[index]==null)
						{
							return 0;						
						}
					traverse = traverse.child[index];
					
				}
			else
			{
				break;
			}
		}
		return traverse.cArray[index];
	}
}
