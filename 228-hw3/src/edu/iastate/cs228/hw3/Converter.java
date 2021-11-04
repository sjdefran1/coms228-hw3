
package edu.iastate.cs228.hw3;

import java.util.Stack;


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

    private Stack<Character> stack;

    public Converter(String input)
    {
        expression = input;
    }


    /**
     * Refer to table above class definiton, this method will return
     * true if givenChar has high precedence than the top of the 
     * stack
     * @param givenChar
     * @param fromInput
     * @return
     */
    public boolean prec(char givenChar, boolean fromInput)
    {      
        return false;
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

    
}
