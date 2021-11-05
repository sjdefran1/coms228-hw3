
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

    private boolean isValid;

    private Scanner scan;

    public InfixExpression(String input)
    {
        expression = input;
        isValid = validCheck();
        scan.close();
    }


    /**
	 * 
	 * @param infix
	 * @return
	 */
	public boolean validCheck()
	{
		scan = new Scanner(expression);

        //empty file, technically valid
        if(expression.length() == 0)
        {
            return true;
        }
        //non empty file
        //check rank
        //final check is that parenthesis are valid
        else
        {
            int rank = 0;
            while(scan.hasNext())
            {
                String curr = scan.next();

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
                    return false;
            }

            //to many operators
            if(rank != 1)
            {
                return false;
                //throw new IllegalStateException("Too many operators")
            }

            Parenthesis finalCheck = new Parenthesis(expression);
		    return finalCheck.parenthMatch();    
        }   
	}


    public boolean isValid()
    {
        return isValid;
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


    public static String getNegative(int i, String givenString)
    {
        while(givenString.charAt(i) != ' ')
        {

        }
        
        return "";
    } 


    @Override
    public String toString()
    {
        return expression;
    }

}
