
package edu.iastate.cs228.hw3;



/**
 * @author Sam DeFrancisco
 */

import java.util.Scanner;
import java.io.File;

/**
 * Main class
 * Takes in a file called input.txt
 * Converts infix expression(s) within input.txt
 * Outputs postfix expression(s) to output.txt
 */
public class Infix2Postfix
{

	private static File input = new File("C:\\Users\\sjdef\\Desktop\\CODE\\COM228\\hw3\\coms228-hw3\\228-hw3\\src\\edu\\iastate\\cs228\\hw3\\input.txt");

	public static void main(String[] args) {
		try {
			//scanner created for createString()
			Scanner scan = new Scanner(input);
			//store infixString
			String infixString = createString(scan);
			//validate infixString, will throw an exception if anything is wrong
			InfixExpression infix = new InfixExpression(infixString);

			//convert to postfix
			Converter converter = new Converter(infix.toString());
			System.out.println(converter.toString());

		} catch (Exception e) { //catches error()'s exceptions

			System.out.print(e.getMessage());
		}
	}
	
	/**
	 * Creates String of input.txt contents
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
				ret += curr + " ";
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
