package edu.iastate.cs228.hw3;

/**
 * @author Sam DeFrancisco
 */

 import java.io.File;
 import java.util.Stack;
 import java.util.Scanner;
/**
 * This class acts as a parenthsis scanner matcher
 * finds if ()  within the file are valid
 */
public class Parenthesis {
    
    /**
     * file to be scanned
     */
    String input; 

    /**
     * String consiting of only the () {} [] within the file
     */
    String parenthString = "";

    public Parenthesis(String input)
    {
        this.input = input;

        //create inputString
        createString();
    }


    /**
     * 
     */
    public void createString()
    {
            for(int i = 0; i < input.length(); i++)
            {
                char curr = input.charAt(i);
                if(curr == '(' || curr == '(')
                {
                    parenthString += curr;
                }
            }
    }

    /**
     * 
     * @return
     */
    public boolean parenthMatch()
    {
        //stack to hold ('s
        Stack<Character> stack = new Stack<Character>();

        //incerment thoruhg inputString
        //if opening bracket push to stack
        //once found closing bracket pop and make sure there 
        //is an opening to go with it
        for(int i = 0; i < parenthString.length(); i++)
        {
            char curr = parenthString.charAt(i);
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


    public static boolean isParenthesis(char givenChar)
    {
        return (givenChar == '(' || givenChar == ')');
    }
}