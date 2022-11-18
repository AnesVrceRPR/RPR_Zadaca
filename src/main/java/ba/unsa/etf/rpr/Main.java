package ba.unsa.etf.rpr;

import static ba.unsa.etf.rpr.ExpressionEvaluator. *;

/**
 * Klasa koja prima matematicki izraz tipa String, prosljedjuje ga odgovarajucoj klasi odgovornoj za njegovo racunjanje,
 * te ispisuje konacni rezultat na ekran
 *
 */
public class Main
{
    /**
     * Metoda za parsiranje ulaza s cmd-a, te prosljedjivanje istog metodi evaluate()
     * @param args ulazni argumenti iz konzole
     */
    public static void main( String[] args )
    {
        System.out.println(evaluate( "( 2 + sqrt ( 2 ) )" ));
    }
}
