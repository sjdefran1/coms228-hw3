
package edu.iastate.cs228.hw3;

/**
 * @author Sam DeFrancisco
 */

/**
 * Stores a infixExpression, also finds out if this expression follows rules and is valid
 */
public class InfixExpression {


    private String expression;

    private boolean isValid;

    public InfixExpression(String input)
    {
        expression = input;

        isValid = validCheck();
    }


    /**
	 * 
	 * @param infix
	 * @return
	 */
	public boolean validCheck()
	{
		
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
            for(int i = 0; i < expression.length(); i++)
            {
                char curr = expression.charAt(i);
                
                //letter or digit
                if(isOperand(curr))
                    rank += 1;
                //parenthesis
                else if(Parenthesis.isParenthesis(curr))
                    rank += 0;
                // +, -, *, /, %
                else if(isOperator(curr) || isExoponent(curr))
                    rank -= 1;
                //rank bound check
                if(rank > 1 || rank < 0)
                    //what is curr if(operand) to many operands, 
                    //if(operator) to many operators
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
	public boolean isOperand(char givenChar)
	{
		return Character.isDigit(givenChar) || Character.isLetter(givenChar);
	}

	/**
	 * 
	 * @param givenChar
	 * @return
	 */
	public static boolean isOperator(char givenChar)
	{
		if(givenChar == '+' || givenChar == '-' || givenChar == '*' || givenChar == '/' || givenChar == '%')
		{
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param givenChar
	 * @return
	 */
	public static boolean isExoponent(char givenChar)
	{
		return givenChar == '^';
	}


    @Override
    public String toString()
    {
        return expression;
    }

}
