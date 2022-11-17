package ba.unsa.etf.rpr;

import java.util.Stack;

public class ExpressionEvaluator {
    private double rezultat;

    public static double evaluate(String izraz)
    {
        Stack <String> ops = new Stack <String> ();
        Stack <Double> vals = new Stack <Double> ();


        for (String znak: izraz.split(" "))
        {
            if(znak == "(");
            else if(znak == "+")  ops.push(znak);
            else if(znak == "-")  ops.push(znak);
            else if(znak == "*")  ops.push(znak);
            else if(znak == "/")  ops.push(znak);
            else if(znak == "sqrt")  ops.push(znak);
            else if(znak == ")")
            {
                String op = ops.pop();
                double V = vals.pop();

            }
        }

        return 12;
    }

}
