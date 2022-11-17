package ba.unsa.etf.rpr;

import java.util.Stack;

/**
 * Klasa za racunanje
 */
public class ExpressionEvaluator {
    private double rezultat;

    public static double evaluate(String izraz)
    {
// ( 2 + ( 3 + 5 ) )
        //(i == ( i+1
        // ((
        //string-split(" ");
        // (( 3 + g )
        // /0, sqrt(-4)
        //( 2 + sqrt ( 5 ) )
        // ( 6 + 7 (


        Stack <String> operatori = new Stack <String> ();
        Stack <Double> brojevi = new Stack <Double> ();


        for (String znak: izraz.split(" "))
        {
            if(znak.equals("("));
            else if(znak.equals("+"))  operatori.push(znak);
            else if(znak.equals("-"))  operatori.push(znak);
            else if(znak.equals("*"))  operatori.push(znak);
            else if(znak.equals("/"))  operatori.push(znak);
            else if(znak.equals("sqrt"))  operatori.push(znak);
            else if(znak.equals(")"))
            {
                String operator = operatori.pop();
                double broj = brojevi.pop();


                if(operator.equals("+"))  broj = broj + brojevi.pop();
                else if(operator.equals("-"))  broj = brojevi.pop() - broj;
                else if(operator.equals("*"))  broj = brojevi.pop() * broj;
                else if(operator.equals("/"))  broj = brojevi.pop() / broj;
                else if(operator.equals("sqrt"))  broj = Math.sqrt(broj);
                brojevi.push(broj);
            }
            else brojevi.push(Double.parseDouble(znak));
        }

        return brojevi.pop();
    }

}
