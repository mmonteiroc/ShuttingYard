import java.util.*;
public class Evaluator {
    public static int calculate(String expr) {
        Token[] tokens = Token.getTokens(expr);

        Deque<Token> pila = new LinkedList<>();
        List<Token> output = new ArrayList<>();

        int cont =0;
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].getTokType()==Token.Toktype.NUMBER){
                output.add(tokens[i]);
            }else if (tokens[i].getTkOp()==')'){
                Iterator<Token> ItPila = pila.iterator();
                while (ItPila.hasNext()){
                    Token t = ItPila.next();
                    if (t.getTkOp()=='('){
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
                if (tokens[i].getTkOp()=='+'|tokens[i].getTkOp()=='-'){
                    // Hay que sacar hasta que no quede mas o haya parentesis
                    while (ItPila.hasNext()){
                        Token prova = ItPila.next();
                        if (prova.getTokType()== Token.Toktype.PAREN){
                            break;
                        }
                        output.add(prova);
                        cont++;
                    }
                }else if (tokens[i].getTkOp()=='*'|tokens[i].getTkOp()=='/'){
                    // Hay que sacar hasta que haya un +, - o parentesis
                    while (ItPila.hasNext()){
                        Token prova = ItPila.next();
                        if (prova.getTkOp()=='+'|prova.getTkOp()=='-'|prova.getTkOp()=='('){
                            break;
                        }
                        output.add(prova);
                        cont++;
                    }
                }else if (tokens[i].getTkOp()=='^'){
                    // Hay que sacar hasta que haya un +, -, *, / o parentesis
                    while (ItPila.hasNext()){
                        Token prova = ItPila.next();
                        if (prova.getTkOp()=='+'|prova.getTkOp()=='-'|prova.getTkOp()=='('|prova.getTkOp()=='*'|prova.getTkOp()=='/'){
                            break;
                        }
                        output.add(prova);
                        cont++;
                    }
                }else if (tokens[i].getTkOp()=='_'){
                    // Paramos cuando encontremos cualquier cosa que no es un _
                    while (ItPila.hasNext()){
                        Token temp = ItPila.next();
                        if (temp.getTkOp()!='_') break;
                        output.add(temp);
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
            if (list[i].getTokType()== Token.Toktype.NUMBER){
                pila.push(list[i]);
            }else if (list[i].getTokType()== Token.Toktype.OP){

                if (list[i].getTkOp()=='_'){
                int resultado;
                    int n2 = pila.poll().getValue();
                    resultado = n2*(-1);
                    pila.push(Token.tokNumber(resultado));
                    continue;
                }


                int resultado;
                int n2 = pila.poll().getValue();
                int n1 = pila.poll().getValue();
                char op = list[i].getTkOp();
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
        }else if (op=='^'){
            dev = Math.pow(n,n2);
        }
        return dev;
    }
}