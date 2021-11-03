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

        //create inputString
        createString();
    }


    public void createString()
    {
        
        try 
        {
            Scanner scan = new Scanner(input);

            //while there is more input, if it is ( or ) add to inputString
            while(scan.hasNext())
            {
                String curr = scan.next();
                if(curr.equals("(") || curr.equals(")"))
                {
                    inputString += curr;
                }
            }

            scan.close();
            
        } 
        catch (Exception e) 
        {
            System.out.print("exception in createString()");
        }
        

    }

    public boolean parenthMatch()
    {
        //stack to hold ('s
        Stack<Character> stack = new Stack<Character>();

        //incerment thoruhg inputString
        //if opening bracket push to stack
        //once found closing bracket pop and make sure there 
        //is an opening to go with it
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
                    stack.pop();
                }
                //if exception is thrown are stack is empty
                //therefore parenthesis are not valid
                catch(Exception e)
                {
                    System.out.println("Invalid parenthesis");
                    return false; // uneven amount 
                }
            }
        }

        //we got through the loop, parenthesis are valid
        return true;
    }
}
