import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author RK
 *
 */
public class Q1Driver {

	/**
	 * @param args[0] - Test String
	 * 		  args[1] - pattern
	 * 		  args[2] - Algorithm selection parameter
	 * 		  args[3] - y/n to print pattern occurrence
	 */
	static boolean Print = false;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String str ;
		String pattern ;
		int alg;
		Timer timer= new Timer();
		if(args.length == 4) {
			 str = args[0];
			 pattern = args[1];
			 alg = Integer.parseInt(args[2]);
			 Print = (args[3].charAt(0)=='y')? true:false;
		}
		else{
			System.out.println("Enter the Test String and Pattern:");
			str = in.next();
			pattern = in.next();
			System.out.println(" Select any one of the following Numbers");
			System.out.println("0 - Run all Algorithms \n1 - Naive \n2 - Rabin Karps \n3 - KMP \n4 - Boyer Moore-Bad Char\n5-Boyer Moore-Good Suffix");
			alg = in.nextInt();
			System.out.println("Enter y/n to print pattern occurrence");
			Print = (in.next().charAt(0)=='y')? true:false;
		}
		 if(str.length()<pattern.length())
			 {
			 	System.out.println("pattern cannot be bigger than Test string");
			 	return;
			 }
		if(alg==1||alg==0)
		{	timer.start();
			ArrayList<Integer> matchPos = new ArrayList<Integer>();
			naive(str,pattern,matchPos,0);
			timer.end();
			if(matchPos.size()>0)
			{
				System.out.println("Number of matches found :"+matchPos.size());
				if(Print)
				{
					for(int i=0;i<matchPos.size()&&i<10;i++)
					{
						System.out.print(matchPos.get(i)+"  ");
					}
					System.out.println();
				}
			}
			else
			{
				System.out.println("Match not found");
			}
			System.out.println("Time for Naive "+timer.elapsedTime);
			System.out.println();
			
		}
		 if(alg==2||alg==0)
		{	
			timer.start();
			ArrayList<Integer> matchPos = new ArrayList<Integer>();
			rabin(str,pattern,matchPos);
			timer.end();
			if(matchPos.size()>0)
			{
				System.out.println("Number of matches found :"+matchPos.size());
				if(Print)
				{
					for(int i=0;i<matchPos.size()&&i<10;i++)
					{
						System.out.print(matchPos.get(i)+"  ");
					}
					System.out.println();
				}
			}
			else
			{
				System.out.println("Match not found");
			}
			System.out.println("Time for Rabin Karps "+timer.elapsedTime);
			System.out.println();
		}
		 if(alg ==3||alg==0)
		{
			timer.start();
			ArrayList matchPos = new ArrayList();
			KMP(str,pattern,matchPos);
			timer.end();
			if(matchPos.size()>0)
			{
				System.out.println("Number of matches found :"+matchPos.size());
				if(Print)
				{
					for(int i=0;i<matchPos.size()&&i<10;i++)
					{
						System.out.print(matchPos.get(i)+"  ");
					}
					System.out.println();
				}
			}
			else
			{
				System.out.println("Match not found");
			}
			System.out.println("Time for KMP "+timer.elapsedTime);
			System.out.println();
		}
		if(alg==4||alg==0)
		{	timer.start();
			ArrayList<Integer> matchPos = new ArrayList<Integer>();
			ArrayList<Integer>[]shiftArr = preProcess(pattern);
			int i,j,k,shift=0,x;
			for( i=0;i<str.length()-pattern.length()+1;i++)
			{
				for( j=pattern.length()-1,k = i+j;j>=0;j--,k--)
				{	
					if(pattern.charAt(j)!=str.charAt(k))
					{	if(shiftArr[(int)str.charAt(k)]==null)
						{
							i+=(pattern.length()-1);
							break;
						}
						x=0;shift=0;
						while(x<shiftArr.length)
						 {
							shift = shiftArr[(int)str.charAt(k)].get(x);
							if(shift<j)
								break;
							x++;
						 }
						if(shift>j)
						{
							i=k-1;
							break;
						}
						else
						{
							i=k-shift-1;
							break;
						}
						
					}
				}
				if(j==-1)
				{
					matchPos.add(i);
				}
			}
			timer.end();
			if(matchPos.size()>0)
			{
				System.out.println("Number of matches found :"+matchPos.size());
				if(Print)
				{
					for( i=0;i<matchPos.size()&&i<10;i++)
					{
						System.out.print(matchPos.get(i)+"  ");
					}
					System.out.println();
				}
			}
			else
			{
				System.out.println("Match not found");
			}
			System.out.println("Time for Boyer Moore -Bad Character "+timer.elapsedTime);
			System.out.println();
			
		}
		if(alg==5||alg==0)
		{	timer.start();
			int[] preArr = preProcessSuffix(pattern);
			ArrayList<Integer> matchPos = new ArrayList<Integer>();
			int i=0,j=0,l=0;
			for( i=pattern.length()-1;i<str.length();i++)
			{
				for( j=pattern.length()-1,l=0;j>=0;j--)
				{	char c1 = pattern.charAt(j),c2=str.charAt(i-l);
					if(c1!=c2)
					{	if(j==pattern.length()-1)
						{
							break;
						}
						int shift = j+1-preArr[j+1];
						i+=(shift-1);
						break;
						}
					else
					{
						l++;
					}
				}
				if(j==-1)
					{
						matchPos.add(i-l+1);
					}
			}

			timer.end();
			if(matchPos.size()>0)
			{
				System.out.println("Number of matches found :"+matchPos.size());
				if(Print)
				{
					for( i=0;i<matchPos.size()&&i<10;i++)
					{
						System.out.print(matchPos.get(i)+"  ");
					}
					System.out.println();
				}
			}
			else
			{
				System.out.println("Match not found");
			}
			System.out.println("Time for Boyer Moore -Good Suffix "+timer.elapsedTime);
			System.out.println();
			
		}
		
		
		
		
		
	}

	private static int[] preProcessSuffix(String pattern) {

		int[] suffix = new int[pattern.length()];
		suffix[pattern.length()-1] =pattern.length()-1;
		for(int j=pattern.length()-2;j>=0;j--)
		{
			if(pattern.charAt(j)==pattern.charAt(pattern.length()-1))
				{
					suffix[pattern.length()-1] = j;
					break;
				}
		}
		for(int i=pattern.length()-2;i>=0;i--)
		{
			char c=pattern.charAt(i);
			for(int j =suffix[i+1]-1;i>=0;i--)
			{
				if(pattern.charAt(j)==c)
					{	if(suffix[i+1]==j+1)
						{	suffix[i] = j;
							break;
						}
						else
						{int k,l;
							for( k=j+1,l=0;j<pattern.length();l++,k++)
							{
								if(pattern.charAt(k)!=pattern.charAt(i+l+1))
								{
									suffix[i]=0;
								}
								
							}
							if(k==pattern.length())
							{
								suffix[i] = j;
							}
						}
					}
			}
		}
		return suffix;
	}

	private static ArrayList<Integer>[] preProcess(String pattern) {
		ArrayList<Integer>[] shift = new ArrayList[256];
		for(int i=0;i<pattern.length();)
		{	if(shift[(int)pattern.charAt(i)]==null)
				shift[(int)pattern.charAt(i)] = new ArrayList<Integer>();
			shift[(int)pattern.charAt(i)].add( 0,i);
			i++;
		}

		return shift;
	}

	private static void KMP(String str, String pattern, ArrayList matchPos) {
		int[] prefix = prefix(pattern);
		boolean matchExists = false;
		int i=0,j=0;
		for( i=0;i<str.length();i++)
		{
			if(str.charAt(i)== pattern.charAt(j))
			{
				j++;
				if(j==pattern.length())
					{	matchPos.add(i-pattern.length()+1);
						matchExists =  true;
						i -=(pattern.length()-1);
						j=0;
					}
			}
			else
			{
				while(str.charAt(i)!= pattern.charAt(j))
				{	if(j==0)
						break;
					j = prefix[j];
				}
				if(str.charAt(i)== pattern.charAt(j))
				{
					j++;
				}
			}
		}
	}

	private static int[] prefix(String pattern) {

		int[] prefix = new int[pattern.length()];
		int j=0;
		prefix[0] = 0;
		for(int i=1;i<pattern.length();i++)
		{
			while(pattern.charAt(i)!=pattern.charAt(j))
			{
				
				
				if(j==0)
					break;
				else
					j = prefix[j-1];
				
				
			}
			if(pattern.charAt(i)==pattern.charAt(j))
			{
				j++;
				prefix[i] = j;
			}
			
		}
		
		return prefix;
	}

	private static  ArrayList<Integer> rabin(String str, String pattern,ArrayList<Integer> matchPos) {
		int pLength = pattern.length();
	 	int sLength=str.length();
		final int prime = 3;
		long multiplier =  1;
		long pHash = 0, sHash=0;
		int i=0;
		boolean matches = false;
		for( i=0;i<pLength;i++)
		{
			pHash += (((int)pattern.charAt(i))-96)*multiplier; 
			sHash += (((int)str.charAt(i))-96)*multiplier; 
			multiplier *= prime;
		}
		
		multiplier /=prime;
		for(i=pLength;i<=str.length();i++)
		{	
			
			if(sHash==pHash)
			{	
				naive(str.substring(i-pLength,i),pattern,matchPos,(i-pLength));
					
	}
			if(i<str.length())
			{	sHash -=(((int)str.charAt(i-pLength))-96);
				sHash /= prime;
				sHash += (((int)str.charAt(i))-96)*multiplier; 
			}
		}
		
		return matchPos;
		 		
	}

	
	private static void naive(String str, String pattern,ArrayList<Integer> matchPos,int a ) {
		char c1,c2;
		int i,j,k;
		boolean matches=false;
		for( i=0;i<str.length()-pattern.length()+1;i++)
		{	k=i;
			for( j=0;j<pattern.length();j++,k++)
			{
				c1 = str.charAt(k);
				c2 = pattern.charAt(j);
				if(c1!=c2)
				{
					break;
				}
			}
			if(j==pattern.length())
			{	
				matches = true;
				matchPos.add(i+a);
			}
		}	
	}

}
