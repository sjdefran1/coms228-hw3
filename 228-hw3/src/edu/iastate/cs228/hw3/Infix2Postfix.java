
package edu.iastate.cs228.hw3;



/**
 * @author Sam DeFrancisco
 */

import java.util.Scanner;
import java.io.File;


public class Infix2Postfix
{
	public static void main(String[] args) {
		try {
			File input = new File("C:\\Users\\sjdef\\Desktop\\CODE\\COM228\\hw3\\coms228-hw3\\228-hw3\\src\\edu\\iastate\\cs228\\hw3\\input.txt");
			Scanner scan = new Scanner(input);

			Parenthesis parenth = new Parenthesis(input);
			boolean validParenth = parenth.parenthMatch();
			System.out.println(validParenth);
		} catch (Exception e) {

		}
	}

}
