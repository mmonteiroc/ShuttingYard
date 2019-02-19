import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Token {
    enum Toktype {
        NUMBER, OP, PAREN
    }

    // Pensa a implementar els "getters" d'aquests atributs
    private Toktype ttype;
    private int value;
    private char tk;

    // Constructor privat. Evita que es puguin construir objectes Token externament
    private Token() {
    }

    // Torna un token de tipus "NUMBER"
    static Token tokNumber(int value) {
        Token dev = new Token();
        dev.ttype = Toktype.NUMBER;
        dev.value = value;
        return dev;
    }

    // Torna un token de tipus "OP"
    static Token tokOp(char c) {
        Token dev = new Token();
        dev.ttype = Toktype.OP;
        dev.tk = c;
        return dev;
    }

    // Torna un token de tipus "PAREN"
    static Token tokParen(char c) {
        Token dev = new Token();
        dev.ttype = Toktype.PAREN;
        dev.tk = c;
        return dev;
    }

    // Mostra un token (conversió a String)
    public String toString() {
        return "token de tipo : "+ ttype +"\n"+
                "Token value : " + value +"\n"+
                "Token tk : " + tk;
    }

    // Mètode equals. Comprova si dos objectes Token són iguals
    public boolean equals(Object o) {
        Token tok = (Token) o;
        if (this.tk == ((Token) o).tk){
            if(this.value == ((Token) o).value){
                if (this.ttype == ((Token) o).ttype){
                    return true;
                }
            }
        }
        return false;
    }

    // A partir d'un String, torna una llista de tokens
    public static Token[] getTokens(String expr) {
        List<Token> dev = new ArrayList<>();

        for (int i = 0; i < expr.length(); i++) {
            if (Character.isDigit(expr.charAt(i))){
                int n = sacarNumero(expr, i);
                dev.add(tokNumber(n));
                StringBuilder s = new StringBuilder();
                s.append(n);
                i+=s.length();
                i--;
                continue;
            }else if (esOp(expr.charAt(i))){
                dev.add(tokOp(expr.charAt(i)));

            }else if (esParen(expr.charAt(i))){
                dev.add(tokParen(expr.charAt(i)));
            }
        }


        return dev.toArray(new Token[0]);
    }



    static private int sacarNumero(String s, int i){
        int dev =0;
        String num="";
        for (int j = i; j < s.length(); j++) {
            num += s.charAt(j);
            if (j+1==s.length())break;
            if (Character.isDigit(s.charAt(j+1))) continue;
            else if (esOp(s.charAt(j+1))|esParen(s.charAt(j+1)) | s.charAt(j+1)==' ') break;
        }

        dev = Integer.parseInt(num);
        return dev;
    }


    static private boolean esOp(char c){
        switch (c){
            case '+':
            case '-':
            case '*':
            case '/':
            case '^':
                return true;
            default:
                return false;
        }
    }
    static private boolean esParen(char c){
        switch (c){
            case '(':
            case ')':
                return true;
            default:
                return false;
        }
    }

    public Toktype getTtype() {
        return ttype;
    }
    public int getValue() {
        return value;
    }
    public char getTk() { return tk; }

    public void setTtype(Toktype ttype) {
        //this.ttype = ttype;
    }
    public void setValue(int value) {
        //this.value = value;
    }
    public void setTk(char tk) {
        //this.tk = tk;
    }


    private String espaciado(String s){
        StringBuilder dev = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))){
                dev.append(s.charAt(i));
            }else {
                dev.append(" " + s.charAt(i) + " ");
            }
        }
        return dev.toString();
    }
}
