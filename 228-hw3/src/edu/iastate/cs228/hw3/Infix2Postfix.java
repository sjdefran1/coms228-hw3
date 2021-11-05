
package edu.iastate.cs228.hw3;



/**
 * @author Sam DeFrancisco
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * Main class
 * Takes in a file called input.txt
 * Converts infix expression(s) within input.txt
 * Outputs postfix expression(s) to output.txt
 */
public class Infix2Postfix
{
	public static void main(String[] args) {
		
		boolean running = true;
		while(running)
		{
			try 
			{
				File input = new File("C:\\Users\\sjdef\\Desktop\\CODE\\COM228\\hw3\\input.txt");
				//scanner created for createString()
				Scanner scan = new Scanner(input);
				
				ArrayList<String> expressions = new ArrayList<String>();
	
				while(scan.hasNextLine())
				{
					expressions.add(scan.nextLine());
				}
			
				scan.close();

				//cahnge i back
				for(int i = 0; i < expressions.size(); i++)
				{
					try 
					{
						InfixExpression curInfix = new InfixExpression(expressions.get(i));
						Converter curConverter = new Converter(curInfix.toString());
						expressions.set(i, curConverter.toString());
						System.out.println(curConverter.toString());	
					} 
					catch (Exception e) 
					{
						System.out.println(e.getMessage());
					}
					
				}

				createOutput(expressions);
				//gotten through all expressions, can break
				running = false;
	
			} catch (Exception e) { //catches error()'s exceptions
	
				System.out.print("Invalid file");
				running = false;
			}
		}
		
		
	}
	
	/**
	 * 
	 * @param list
	 */
	public static void createOutput(ArrayList<String> list)
	{
		File output = new File("output.txt");
		try 
		{
			FileWriter fw = new FileWriter(output);
			for(int i = 0; i < list.size(); i++)
			{
				fw.write(list.get(i) + "\n");
			}
			fw.close();
		} 
		catch (Exception e) 
		{
			System.out.print(e);
		}
	}
}
