
package edu.iastate.cs228.hw3;

import java.util.Stack;

import javax.swing.text.Position;


/**
 * @author Sam DeFrancisco
 */


/**
 * 
 * |Operator| |Input precedence| |Stack precedence|  |Rank|
 *   +  -            1                    1            -1
 *   * /  %          2                    2            -1
 *   ^               4                    3            -1
 *   (               5                   -1             0
 *   )               0                    0             0
 */

public class Converter {


    private String expression;
    private String postfixExpression;

    private Stack<Character> stack;

    public Converter(String input)
    {
        expression = input;
        stack = new Stack<Character>();
        postfixExpression = "";
        converter(); //starts process of converting given infix to postfix
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
        
        if(expression.length() == 0)
        {
            return;
        }
        //go through entire infix expression
        for(int i = 0; i < expression.length(); i++)
        {
            char curr = expression.charAt(i);
            
            //operand
            if(Character.isDigit(curr) || Character.isLetter(curr))
            {
                //postfixExpression += curr;
                handleOperand(curr);
            }
            //operator
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
            postfixExpression += stack.pop();
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
    public boolean prec(char givenChar, boolean fromInput)
    {      
        if(stack.isEmpty())
        {
            return false;
        }
        
        //get givenChar's prec based on if it's from input
        int givenCharPrec = getPrec(givenChar, fromInput);       
        char top = stack.peek();
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
    public int getPrec(int givenChar, boolean fromInput)
    {
        int givenElementPrec = 0;
        
        switch(givenChar)
        {
            case '+':
                givenElementPrec = 1;
                break;
            case '-':
                givenElementPrec = 1;
                break;
            case '/':
                givenElementPrec = 2;
                break;
            case '%':
                givenElementPrec = 2;
                break;
            case '*':
                givenElementPrec = 2;
                break;
            case '^':
                if(fromInput)
                    givenElementPrec = 4;
                else
                    givenElementPrec = 3;
                break;
            case '(':
                if(fromInput)
                    givenElementPrec = 5;
                else
                    givenElementPrec = -1;
                
                break;
            case ')':
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
    private void handleOperand(char curr)
    {
        postfixExpression += curr;
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
    private void handleOperator(char curr)
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
                        postfixExpression += stack.pop();
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
    private void handleParenth(char curr)
    {
        //opening parenth gets pushed onto stack
        if(curr == '(')
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


            char popped = stack.pop();
            while(popped != '(')
            {
                if(stack.isEmpty())
                {
                //missing opening parenth, shouldve been caught by InfixExpression
                //we shall see
                break;
                }
                else
                {
                    postfixExpression += popped;
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
