
package edu.iastate.cs228.hw3;

import java.util.Stack;



/**
 * @author Sam DeFrancisco
 */

import java.util.Scanner;
/**
 * 
 * |Operator| |Input precedence| |Stack precedence|  |Rank|
 *   +  -            1                    1            -1
 *   * /  %          2                    2            -1
 *   ^               4                    3            -1
 *   (               5                   -1             0
 *   )               0                    0             0
 * 
 * 
 * This class takes in the infixExpression from input.txt and converts it to postFix
 */
public class Converter {


    /**
     * Stores infix expression from input.txt
     */
    private String expression;

    /**
     * used to store created postfixExpression
     */
    private String postfixExpression;

    /**
     * used to scan expression throughout class
     * initialized within converter()
     * closed after call to converter() within constructor
     */
    Scanner scan;

    /**
     * Stack used for conversion to postFix, stores operators in correct
     * precedence order
     */
    private Stack<String> stack;


    /**
     * Constructor
     * @param input - infix expression to convert to postfix
     */
    public Converter(String input)
    {
        expression = input;
        stack = new Stack<String>();
        postfixExpression = "";
        converter(); //starts process of converting given infix to postfix
        scan.close();
    }



    /**
     * Calls helper methods to convert infix input to postfix
     * 
     * Goes through expression string calling the handler method for different types
     * calls handleOperand() when we are dealing with operand
     * calls handleOperator() when dealing with operator 
     * calls handleParenth() when handling ()'s
     * Emptys stack onto postfixExpression when done going through infix expression string
     */
    public void converter()
    {
        scan = new Scanner(expression);
        
        //empty String has no work to be done
        if(expression.length() == 0)
        {
            return;
        }

        while(scan.hasNext())
        {
            String curr = scan.next();

             //operand
             if(InfixExpression.isOperand(curr) || InfixExpression.isNegative(curr))
             {
                 //postfixExpression += curr;
                 handleOperand(curr);
             }
             //operator, current added "" to fix break make sure this doesnt end badly 
             else if(InfixExpression.isOperator(curr) || InfixExpression.isExoponent(curr))
             {
                 handleOperator(curr);
             }
 
             //parenth
             else if(Parenthesis.isParenthesis(curr))
             {   
                 handleParenth(curr);
             }
        }


        //empty rest of operators to end of string
        while(!stack.isEmpty())
        {
            postfixExpression += stack.pop() + " ";
        }
    }



    /**
     * Refer to table above class definiton, this method will return
     * true if givenChar has high precedence than the top of the 
     * stack
     * 
     * 
     * if given char has higher precendence then top of stack, pop() stack,
     * and push givenChar to check against next top of stack
     * @param givenChar
     * @param fromInput
     * @return
     * return true if you should just push givenChar 
     * return false if you should pop stack, add pop() to postfix, then compare givenChar to next top
     * 
     */
    public boolean prec(String givenS, boolean fromInput)
    {      
        if(stack.isEmpty())
        {
            return false;
        }
        
        //get givenChar's prec based on if it's from input
        int givenCharPrec = getPrec(givenS, fromInput);       
        String top = stack.peek();
        int topPrec = getPrec(top, false);
        
        
        if(givenCharPrec < topPrec)
        {
            return false;
        }
        if(givenCharPrec > topPrec)
        {
            return true;
        }

        //equal precedence, still pop top
        return false;  
    }




    /**
     * 
     * Returns prec int of givenChar based on if its from input or not
     * 
     * @param givenChar
     * @param fromInput
     * @return
     */
    public int getPrec(String givenS, boolean fromInput)
    {
        int givenElementPrec = 0;
        
        switch(givenS)
        {
            case "+":
                givenElementPrec = 1;
                break;
            case "-":
                givenElementPrec = 1;
                break;
            case "/":
                givenElementPrec = 2;
                break;
            case "%":
                givenElementPrec = 2;
                break;
            case "*":
                givenElementPrec = 2;
                break;
            case "^":
                if(fromInput)
                    givenElementPrec = 4;
                else
                    givenElementPrec = 3;
                break;
            case "(":
                if(fromInput)
                    givenElementPrec = 5;
                else
                    givenElementPrec = -1;
                
                break;
            case ")":
                givenElementPrec = 5;
                break;
        } 

        return givenElementPrec;
    }

    /**
     * When we have an operand all we need to do is add it to output string
     * 
     * @param curr - refers to last read element of input infix expression
     */
    private void handleOperand(String curr)
    {
        postfixExpression += curr + " ";
    }


    /**
     * When we have an operator we use the prec() method to figure out what we need to do with curr
     * if prec() returns false while our curr element doesn't have lower prec then top, we pop top and add it
     * to postfix string
     * 
     * if currPrec > topPrec we just push it to top of stack
     * 
     * @param curr refers to last read element of input infix expression
     */
    private void handleOperator(String curr)
    {
        boolean prec = prec(curr, true);
            
                //empty stack just push operator
                //need to pop and give to postexpression
                //then check against next top
                if(!prec)
                {
                    while(!prec)
                    {
                        if(stack.isEmpty())
                        {
                            break;
                        }
                        postfixExpression += stack.pop() + " ";
                        prec = prec(curr, true);
                    }
                    stack.push(curr);
                }
                //currPrec > topPrec
                else
                {
                    stack.push(curr);
                }
    }


    /**
     * Called when needed to handle parenthesis for subexpressions
     * 
     * If(opening parenth) -> push it onto stack
     * 
     * if(closing parenth) -> pop everything and add it to postfix string untill we find the matching
     *  opening parenthesis
     * 
     * @param curr refers to last read element of input infix expression
     */
    private void handleParenth(String curr)
    {
        //opening parenth gets pushed onto stack
        if(curr.equals("("))
        {
            stack.push(curr);
        }
        //closing parenth, pop's until finds opening parenth
        else
        {   
            if(stack.isEmpty())
            {
                return;
            }


            String popped = stack.pop();
            if(popped.equals("("))
            {
                //no subexpression, top of stack was opening parenth
                InfixExpression.error(4, "");
            }

            while(!popped.equals("("))
            {
                if(stack.isEmpty())
                {
                //missing opening parenth, shouldve been caught by InfixExpression
                break;
                }
                else
                {
                    postfixExpression += popped + " ";
                    popped = stack.pop();
                }
            }
            //found opening parenth, go to next iteration to discard of both w/out adding
        }
    }

    /**
     * outputs postfix expression
     */
    @Override
    public String toString()
    {
        return postfixExpression;
    }

    
}
