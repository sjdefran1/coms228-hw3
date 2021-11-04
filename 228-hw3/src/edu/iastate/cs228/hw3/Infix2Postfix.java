
package edu.iastate.cs228.hw3;



/**
 * @author Sam DeFrancisco
 */

import java.util.Scanner;
import java.io.File;

/**
 * 
 */
public class Infix2Postfix
{

	private static File input = new File("C:\\Users\\sjdef\\Desktop\\CODE\\COM228\\hw3\\coms228-hw3\\228-hw3\\src\\edu\\iastate\\cs228\\hw3\\input.txt");

	public static void main(String[] args) {
		try {
			//set up input.txt and create scanner
			//File input = 
			Scanner scan = new Scanner(input);
			String infixString = createString(scan);
			InfixExpression infix = new InfixExpression(infixString);
			boolean valid = infix.isValid();
			System.out.println(valid);
			//create instance of Parenthesis, will find if there are valid parenth
			//within in input.txt
			//Parenthesis parenth = new Parenthesis(infixString);
			//boolean validParenth = parenth.parenthMatch();
			//System.out.println(validParenth);
		} catch (Exception e) {

			System.out.print("Invalid file probably");
		}
	}
	
	/**
	 * 
	 * @param scan
	 * @return
	 */
	public static String createString(Scanner scan)
    {	
		String ret = "";

        try 
        {
            //Scanner scan = new Scanner(input);

            //while there is more input, if it is ( or ) add to inputString
            while(scan.hasNext())
            {
                String curr = scan.next();
				ret += curr;
            }

            scan.close();
            
        } 
        catch (Exception e) 
        {
            System.out.print("exception in createString()");
        }
        
		return ret;
    }


}
