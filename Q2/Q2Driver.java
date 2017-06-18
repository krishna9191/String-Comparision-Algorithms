/**
 * 
 */

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author RK
 *
 */
public class Q2Driver {

	/**
	 * @param args
	 */
	static boolean print;
	public static void main(String[] args) throws FileNotFoundException {

		Scanner in = new Scanner(System.in);
		Trie t = new Trie();
		System.out.println("Do you want to print status for add ,import and remove [y/n]");
		Q2Driver.print = (in.next().equalsIgnoreCase("y"))?true:false;
		int option;
		String input;
		System.out.println("Select one of the following operations");
		System.out.println(" 0- Exit\n 1-add\n 2-remove\n 3-contains\n 4-prefix \n 5-import");
		while((option=in.nextInt())!=0)
		{in.nextLine();
			switch(option)
			{	
				
				case 1: {
							System.out.println("Enter the word to add to Trie");
							input=in.nextLine();
							t.add(input);
							break;
						}
				case 2: {
					System.out.println("Enter the word to Remove");
					input=in.nextLine();
					t.remove(input);
					break;
				}
				case 3: {
					System.out.println("Enter the word to check contains");
					input=in.nextLine();
					System.out.println(t.contains(input));
					break;
				}
				case 4: {
					System.out.println("Enter the word to check prefix");
					input=in.nextLine();
					System.out.println("There are prefix words= "+t.prefix(input));
					break;
				}
				case 5: {
					System.out.println("Enter the file location");
					input=in.nextLine();
					t.importFromFile(input);
					break;
				}
			}
		}
		
	}

}
