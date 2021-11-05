
package edu.iastate.cs228.hw3;

import java.util.Scanner;

/**
 * @author Sam DeFrancisco
 */

/**
 * Stores a infixExpression, also finds out if this expression follows rules and is valid
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
	 * 
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
            if(rank != 1)
            {
                error(1, curr); //to many operators
            }

            Parenthesis finalCheck = new Parenthesis(expression);
		    finalCheck.parenthMatch();    
        }   
	}

   /**
	 * 
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

    public static boolean isNegative(String givenS)
    {
        return givenS.charAt(0) == '-' && givenS.length() > 1;
    }

	/**
	 * 
	 * @param givenChar
	 * @return
	 */
	public static boolean isOperator(String givenS)
	{
		return givenS.equals("+") || givenS.equals("-") || givenS.equals("*") || givenS.equals("/")  || givenS.equals("%");
	}

	/**
	 * 
	 * @param givenChar
	 * @return
	 */
	public static boolean isExoponent(String givenS)
	{
		return givenS.equals("^");
	}


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


    @Override
    public String toString()
    {
        return expression;
    }

}
