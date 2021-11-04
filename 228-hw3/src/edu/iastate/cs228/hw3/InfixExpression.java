
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

        isValid = isExpressionValid();
    }





    public boolean isExpressionValid()
    {
        if(expression.length() == 0)
        {
            return true;
        }
        else
        {
            
        }
        
        
        return true;
    }

    /**
	 * 
	 * @param infix
	 * @return
	 */
	public boolean rankCheck()
	{
		int rank = 0;

		for(int i = 0; i < expression.length(); i++)
		{
			char curr = expression.charAt(i);
			
			//letter or digit
			if(isOperand(curr))
			{

			}

			//parenthesis
			if(Parenthesis.isParenthesis(curr))
			{

			}
		}
		
		return true;
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
	public boolean isOperator(char givenChar)
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
	public boolean isExoponent(char givenChar)
	{
		return givenChar == '^';
	}

}
