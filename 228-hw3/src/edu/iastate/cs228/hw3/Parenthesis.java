package edu.iastate.cs228.hw3;

/**
 * @author Sam DeFrancisco
 */

 import java.io.File;
 import java.util.Stack;
 import java.util.Scanner;
/**
 * This class acts as a parenthsis scanner matcher
 * finds if () {} [] within the file are valid
 */
public class Parenthesis {
    
    /**
     * file to be scanned
     */
    File input; 

    /**
     * String consiting of only the () {} [] within the file
     */
    String inputString = "";

    public Parenthesis(File input)
    {
        this.input = input;
        createString();
    }


    public void createString()
    {
        
        try 
        {
            Scanner scan = new Scanner(input);

            while(scan.hasNext())
            {
                String curr = scan.next();
                if(curr.equals("(") || curr.equals(")"))
                {
                    inputString += curr;
                }
            }

            System.out.println(inputString);
            
        } 
        catch (Exception e) 
        {
            System.out.print("exception in createString()");
        }
        

    }

    public boolean parenthMatch()
    {
        Stack<Character> stack = new Stack<Character>();
        for(int i = 0; i < inputString.length(); i++)
        {
            char curr = inputString.charAt(i);
            if(curr == '(')
            {
                stack.push(curr);
            }
            else if(curr == ')')
            {
                try{
                    char open = stack.pop();
                    char close = inputString.charAt(i);
                    if((open == '(' && close != ')'))
                    {
                        return false;
                    }
                }
                catch(Exception e)
                {
                    System.out.println("Invalid parenthesis");
                    return false; // uneven amount 
                }
            }
        }
        
        if(stack.isEmpty())
            return true;
        else
            return false;
    }
}
