import java.util.Deque;
import java.util.LinkedList;


public class Evaluator {


    public static int calculate(String expr) {
        // Convertim l'string d'entrada en una llista de tokens
        Token[] tokens = Token.getTokens(expr);
        // Efectua el procediment per convertir la llista de tokens en notació RPN
        // Finalment, crida a calcRPN amb la nova llista de tokens i torna el resultat




        return 0;
    }


    //Polaca inversa recibimos
    public static int calcRPN(Token[] list) {
        // Calcula el valor resultant d'avaluar la llista de tokens
        Deque<Token> pila = new LinkedList<Token> ();
        for (int i = 0; i < list.length; i++) {
            if (list[i].getTtype()== Token.Toktype.NUMBER){
                pila.push(list[i]);
            }else if (list[i].getTtype()== Token.Toktype.OP){
                int n2 = pila.poll().getValue();
                int n1 = pila.poll().getValue();
                char op = list[i].getTk();
                int resultado = operamos(n1,n2,op);
                pila.push(Token.tokNumber(resultado));
            }

        }

        return pila.poll().getValue();
    }

    static private int operamos(int n, int n2, char op){
        int dev =0;
        if (op=='+'){
            dev = n + n2;
        }else if (op=='-'){
            dev = n - n2;
        }else if (op=='*'){
            dev = n * n2;
        }else if (op=='/'){
            dev = n / n2;
        }
        return dev;
    }


}
