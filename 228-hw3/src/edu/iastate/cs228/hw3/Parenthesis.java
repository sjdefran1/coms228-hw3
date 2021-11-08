package edu.iastate.cs228.hw3;

/**
 * @author Sam DeFrancisco
 */

 import java.util.Stack;
 
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


    /**
     * 
     * @param input - infix expression for () to be evaulated
     */
    public Parenthesis(String input)
    {
        this.input = input;

        //create inputString
        createString();
    }


    /**
     * Creates String of only ()'s gets rid of rest of noise
     */
    public void createString()
    {
            for(int i = 0; i < input.length(); i++)
            {
                char curr = input.charAt(i);
                if(curr == '(' || curr == ')')
                {
                    parenthString += curr;
                }
            }
    }

    /**
     * Validates ()'s within infixExpression 
     * @return true if parenthesis are valid
     *         false if parenthis are invalid   
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
                    InfixExpression.error(2, "");
                    //System.out.println("Invalid parenthesis");
                    return false; // uneven amount 
                }
            }
        }


        if(!stack.isEmpty())
        {
            InfixExpression.error(3, "");
            return false; //to many ('s
        }
        //we got through the loop, parenthesis are valid
        return true;
    }

    /**
     * determines if givenS is a opening or closing parenthesis
     * @param givenS
     * @return
     */
    public static boolean isParenthesis(String givenS)
    {
        return (givenS.equals("(") || givenS.equals(")"));
    }
}
