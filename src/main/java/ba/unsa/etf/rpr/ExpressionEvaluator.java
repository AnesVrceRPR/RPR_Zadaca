package ba.unsa.etf.rpr;

import java.util.Stack;

/**
 * Klasa za racunanje matematickog izraza
 */
public class ExpressionEvaluator {
    /**
     * Metoda koja sluzi za računanje matematickog izraza proslijedjenog kao string
     * @param izraz tipa String - sastoji se od zagrada, operatora i brojeva, medjusobno odvojenih razmacima, u protivnom baca izuzetak
     * @return tipa double - predstavlja rezultat aritmetičkih operacija izvrsenih nad operandima
     */
    public static double evaluate(String izraz){

        int BrojOtvorenihZagrada = 0;

        Stack <String> operatori = new Stack <String> ();
        Stack <Double> brojevi = new Stack <Double> ();


        for (String znak: izraz.split(" "))
        {
            if(znak.equals("(")) BrojOtvorenihZagrada++;
            else if(znak.equals("+"))  operatori.push(znak);
            else if(znak.equals("-"))  operatori.push(znak);
            else if(znak.equals("*"))  operatori.push(znak);
            else if(znak.equals("/"))  operatori.push(znak);
            else if(znak.equals("sqrt"))  operatori.push(znak);
            else if(znak.equals(")"))
            {
                BrojOtvorenihZagrada--;

                String operator = operatori.pop();
                double broj = brojevi.pop();


                if(operator.equals("+"))  broj = broj + brojevi.pop();
                else if(operator.equals("-"))  broj = brojevi.pop() - broj;
                else if(operator.equals("*"))  broj = brojevi.pop() * broj;
                else if(operator.equals("/"))
                {
                    if(broj == 0) throw new RuntimeException("Dijeljenje sa nulom");
                    else broj = brojevi.pop() / broj;
                }

                else if(operator.equals("sqrt"))
                {
                    try {
                        broj = Math.sqrt(broj);
                     }
                    catch (Exception e)
                    {
                        throw new RuntimeException("Funkciji sin() proslijedjen negativan broj.");
                    }
                }
                brojevi.push(broj);
            }
            else {
                try {
                    brojevi.push(Double.parseDouble(znak));
                } catch (Exception e)
                {
                    throw new RuntimeException("Neispravan format izraza");
                }
            }
        }

        if(BrojOtvorenihZagrada != 0) throw new RuntimeException("Neispravan format izraza");

        return brojevi.pop();
    }

}
