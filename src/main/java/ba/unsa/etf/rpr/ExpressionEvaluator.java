package ba.unsa.etf.rpr;

import java.util.Stack;

/**
 * Klasa za racunanje matematickog izraza
 *
 */
public class ExpressionEvaluator {
    /**
     * Metoda koja sluzi za računanje matematickog izraza proslijedjenog kao string
     * Moguće koristiti sljedeće operacije:
     * sabriranje,
     * oduzimanje,
     * mnozenje,
     * dijeljenje,
     * korijen broja
     * @param izraz tipa String - sastoji se od zagrada, operatora i brojeva, medjusobno odvojenih razmacima, u protivnom baca izuzetak
     * @return tipa double - predstavlja rezultat aritmetičkih operacija izvrsenih nad operandima
     *
     */
    public static double evaluate(String izraz){

        int BrojOperanadaIzmedjuZagrada = 0;

        int BrojOtvorenihZagradaDoSqrt = 0;

        int BrojOtvorenihZagrada = 0;

        Stack <String> operatori = new Stack <String> ();
        Stack <Double> brojevi = new Stack <Double> ();

        try {
            for (String znak: izraz.split(" "))
            {
                if(!znak.equals(")") && BrojOperanadaIzmedjuZagrada == 2) throw new RuntimeException("Neispravan format izraza");
                if(znak.equals("("))
                {
                    BrojOperanadaIzmedjuZagrada = 0;
                    BrojOtvorenihZagrada++;
                }
                else if(znak.equals("+"))  operatori.push(znak);
                else if(znak.equals("-"))  operatori.push(znak);
                else if(znak.equals("*"))  operatori.push(znak);
                else if(znak.equals("/"))  operatori.push(znak);
                else if(znak.equals("sqrt"))
                {
                    BrojOtvorenihZagradaDoSqrt = BrojOtvorenihZagrada;
                    operatori.push(znak);
                }
                else if(znak.equals(")"))
                {
                    BrojOperanadaIzmedjuZagrada = 0;

                    BrojOtvorenihZagrada--;

                    String operator = operatori.pop();

                    double broj = brojevi.pop();

                    if(operator.equals("+"))  broj = broj + brojevi.pop();
                    else if(operator.equals("-"))  broj = brojevi.pop() - broj;
                    else if(operator.equals("*"))  broj = brojevi.pop() * broj;
                    else if(operator.equals("/"))
                    {
                        if(broj == 0) throw new RuntimeException("djeljenje s nulom");
                        else broj = brojevi.pop() / broj;
                    }

                    else if(operator.equals("sqrt"))
                    {
                        if(BrojOtvorenihZagrada != BrojOtvorenihZagradaDoSqrt) throw new RuntimeException("Neispravan format izraza");
                        BrojOtvorenihZagradaDoSqrt--;
                        if (broj < 0) throw new RuntimeException("sqrt-u proslijedjen negativan broj.");
                        else broj = Math.sqrt(broj);
                    }
                    brojevi.push(broj);
                }
                else
                {
                    BrojOperanadaIzmedjuZagrada++;
                    brojevi.push(Double.parseDouble(znak));
                }
            }
        } catch(Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }

        if(BrojOtvorenihZagrada != 0) throw new RuntimeException("Neispravan format izraza");
        if(!operatori.isEmpty()) throw new RuntimeException("Neispravan format izraza");
        if(brojevi.size()>1)throw new RuntimeException("Neispravan format izraza");

        return brojevi.pop();
    }

}
