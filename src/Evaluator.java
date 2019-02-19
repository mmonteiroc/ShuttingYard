import java.util.*;
public class Evaluator {
    public static int calculate(String expr) {
        Token[] tokens = Token.getTokens(expr);

        Deque<Token> pila = new LinkedList<>();
        List<Token> output = new ArrayList<>();

        int cont =0;
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].getTtype()==Token.Toktype.NUMBER){
                output.add(tokens[i]);
            }else if (tokens[i].getTk()==')'){
                Iterator<Token> ItPila = pila.iterator();
                while (ItPila.hasNext()){
                    Token t = ItPila.next();
                    if (t.getTk()=='('){
                        break;
                    }else {
                        output.add(t);
                        cont++;
                    }
                }
                for (int j = 0; j <= cont; j++) {
                    pila.poll();
                }
                cont=0;
            }else{
                Iterator<Token> ItPila = pila.iterator();
                if (tokens[i].getTk()=='+'|tokens[i].getTk()=='-'){
                    // Hay que sacar hasta que no quede mas o haya parentesis
                    while (ItPila.hasNext()){
                        Token prova = ItPila.next();
                        if (prova.getTtype()== Token.Toktype.PAREN){
                            break;
                        }
                        output.add(prova);
                        cont++;
                    }
                }else if (tokens[i].getTk()=='*'|tokens[i].getTk()=='/'){
                    // Hay que sacar hasta que haya un +, - o parentesis
                    while (ItPila.hasNext()){
                        Token prova = ItPila.next();
                        if (prova.getTk()=='+'|prova.getTk()=='-'|prova.getTk()=='('){
                            break;
                        }
                        output.add(prova);
                        cont++;
                    }
                }else if (tokens[i].getTk()=='^'){
                    // Hay que sacar hasta que haya un +, -, *, / o parentesis
                    while (ItPila.hasNext()){
                        Token prova = ItPila.next();
                        if (prova.getTk()=='+'|prova.getTk()=='-'|prova.getTk()=='('|prova.getTk()=='*'|prova.getTk()=='/'){
                            break;
                        }
                        output.add(prova);
                        cont++;
                    }
                }
                for (int j = 0; j < cont; j++) {
                    pila.poll();
                }
                cont=0;
                pila.push(tokens[i]);
            }
        }
        Iterator<Token> pilaIterator = pila.iterator();
        while (pilaIterator.hasNext()){
            output.add(pilaIterator.next());
        }
        Token[] toks = output.toArray(new Token[0]);
        return calcRPN(toks);
    }

    public static int calcRPN(Token[] list) {
        Deque<Token> pila = new LinkedList<Token> ();
        for (int i = 0; i < list.length; i++) {
            if (list[i].getTtype()== Token.Toktype.NUMBER){
                pila.push(list[i]);
            }else if (list[i].getTtype()== Token.Toktype.OP){
                int resultado;
                int n2 = pila.poll().getValue();
                int n1 = pila.poll().getValue();
                char op = list[i].getTk();
                resultado =(int) operamos(n1,n2,op);
                pila.push(Token.tokNumber(resultado));
            }
        }
        return pila.poll().getValue();
    }
    static private double operamos(int n, int n2, char op){
        double dev =0;
        if (op=='+'){
            dev = n + n2;
        }else if (op=='-'){
            dev = n - n2;
        }else if (op=='*'){
            dev = n * n2;
        }else if (op=='/'){
            dev = n / n2;
        }else  if (op=='^'){
            dev = Math.pow(n,n2);
        }
        return dev;
    }
}