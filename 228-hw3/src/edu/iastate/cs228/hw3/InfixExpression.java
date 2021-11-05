
package edu.iastate.cs228.hw3;

import java.util.Scanner;

/**
 * @author Sam DeFrancisco
 */

/**
 * Stores a infixExpression, also finds out if this expression follows rules and is valid
 * contains plenty of helper methods that determine what a givenString is
 */
public class InfixExpression {


    private String expression;

    private Scanner scan;

    public InfixExpression(String input)
    {
        expression = input;
        validCheck();
        scan.close();
    }


    /**
	 * Basically calculates rank
     * if rank isn't valid throws calls error() accordingly
     * if rank is valid, checks parentheses
     * if parenthesis, are valid execution continues
     * if parenth aren't valid Parenthesis.java calls error() accordingly
	 * @param infix
	 * @return
	 */
	public void validCheck()
	{
		scan = new Scanner(expression);

        //empty file, technically valid
        if(expression.length() == 0)
        {
            return;
        }
        //non empty file
        //check rank
        //final check is that parenthesis are valid
        else
        {
            int rank = 0;
            String curr = "";
            while(scan.hasNext())
            {
                curr = scan.next();

                //letter or digit
                if(isOperand(curr) || isNegative(curr))
                    rank += 1;
                //parenthesis
                else if(Parenthesis.isParenthesis(curr))
                    rank += 0;
                // +, -, *, /, %
                else if(isOperator(curr) || isExoponent(curr))
                    rank -= 1;
                
                //rank bound check
                if(rank > 1 || rank < 0)
                {
                    if(rank > 1)
                        error(0, curr); //to many operrand
                    else //rank < 0
                        error(1, curr); //to many operators
                }
                    
            }

            //to many operators
            if(rank != 1 && onlyParenth())
            {
                error(1, curr); //to many operators
            }

            Parenthesis finalCheck = new Parenthesis(expression);
		    finalCheck.parenthMatch();    
        }   
	}

   /**
	 * determines if givenS is a operand
	 * @param givenChar
	 * @return
	 */
	public static boolean isOperand(String givenS)
	{
        int i =  0;

        //go through operator, if any part of string is not a digit
        //or letter return false
        while(i < givenS.length())
        {
            char curr = givenS.charAt(i);
            if(!Character.isDigit(curr) && !Character.isLetter(curr))
            {
                return false;
            }
            i++;
        }
		return true;
	}

    /**
     * 
     * @return
     */
    public boolean onlyParenth()
    {
        for(int i = 0; i < expression.length(); i++)
        {
            String curr = expression.charAt(i) + "";
            if(!Parenthesis.isParenthesis(curr))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * determines if givenS is a negative operand
     * @param givenS
     * @return
     */
    public static boolean isNegative(String givenS)
    {
        return givenS.charAt(0) == '-' && givenS.length() > 1;
    }

	/**
	 * determines if givenS is a operator
     * 
	 * @param givenChar
	 * @return
	 */
	public static boolean isOperator(String givenS)
	{
		return givenS.equals("+") || givenS.equals("-") || givenS.equals("*") || givenS.equals("/")  || givenS.equals("%");
	}

	/**
	 * determines if givenS is a exponent sign
     * 
	 * @param givenChar
	 * @return
	 */
	public static boolean isExoponent(String givenS)
	{
		return givenS.equals("^");
	}


    /**
     * error handling, throws IllegalStateException according to caseNum
     * 
     * @param caseNum - type of error occuring for switch statement
     *                  0 for to many operands
     *                  1 for to many operators
     *                  2 for no mathcing opening parenth
     *                  3 for no matching closing parenth
     *                  4 for no subexpression inside parenth
     * @param op - op that caused issue
     *             if operand || operator op will contain something
     *             if parenth or subexpression issue op will be empty
     */
    public static void error(int caseNum, String op)
    {

        switch(caseNum)
        {
            case 0:
                throw new IllegalStateException("Error: to many operands" + "(" + op + ")");
            case 1:
                throw new IllegalStateException("Error: to many operators" + "(" + op + ")");
            case 2:
                throw new IllegalStateException("Error: no opening parenthesis detected");
            case 3:
                throw new IllegalStateException("Error: no closing parenthesis detected");
            case 4: //needs implemented
                throw new IllegalStateException("Error: no subexpression detected ()");
        }
    }

    /**
     * returns infixExpression that came from input.txt
     */
    @Override
    public String toString()
    {
        return expression;
    }

}
