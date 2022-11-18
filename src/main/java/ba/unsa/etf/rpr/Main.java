package ba.unsa.etf.rpr;

import static ba.unsa.etf.rpr.ExpressionEvaluator. *;

/**
 * Main klasa za primanje stringa iz cmd-a, te prosljedjivanje tog stringa metodi evaluate()
 *
 */
public class Main
{
    public static void main( String[] args )
    {
        System.out.println(evaluate( "( 2 + sqrt ( 2 ) )" ));
    }
}
