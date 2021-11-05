
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
        converter();
    }



    /**
     * 
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
                postfixExpression += curr;
            }
            //operator
            else if(InfixExpression.isOperator(curr))
            {
                //if top of stack has higher prec then curr
                // *  |+|
                if(prec(curr, true))
                {
                    //while top of stack has higher prec then curr
                    while(prec(curr, true))
                    {
                        postfixExpression += stack.pop();
                    }
                    //we are where we need to be in stack
                    stack.push(curr);
                }
                // + |*|
                else
                {
                    //postfixExpression += stack.pop();
                   // stack.push(curr);
                   //pop multi, push +
                   //of stack is empty tho we  just push
                   if(stack.isEmpty())
                   {
                       stack.push(curr);
                   }
                   else
                   {
                       postfixExpression += stack.pop();
                       
                       stack.push(curr);
                   }
                }
                
                
                
            }

            //parenth
            else if(Parenthesis.isParenthesis(curr))
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
                        //missing opening parenth, shouldve been caught by InfixExpression
                        //we shall see
                        break;
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
     * //return true if you should pop stack, and push givenChar
     * //return false if you should just push givenChar 
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
        
        
        if(topPrec < givenCharPrec)
        {
            return true;
        }
        if(topPrec > givenCharPrec)
        {
            return false;
        }

        //equal precedence, still pop top
        return true;
        
        
    }




    /**
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
            case ')':
                givenElementPrec = 5;
        } 

        return givenElementPrec;
    }


    @Override
    public String toString()
    {
        return postfixExpression;
    }

    
}
