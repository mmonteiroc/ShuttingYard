import java.util.ArrayList;
import java.util.Arrays;
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
        return " "+tk+" "+value;
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
        String[]  partes = espaciado(expr).split("\\s+");

        boolean encontradoOp = true;

        for (int i = 0; i < partes.length; i++) {
            String part = partes[i];
            if (part.length()!=0){
                if (part.contains("-") && encontradoOp){
                    dev.add(tokNumber(-1));
                    dev.add(tokOp('*'));
                    encontradoOp=false;
                }else if (esOp(part.charAt(0))){
                    encontradoOp = true;
                    dev.add(tokOp(part.charAt(0)));
                }else if (esParenIZQ(part.charAt(0))) {
                    dev.add(tokParen(part.charAt(0)));
                    encontradoOp=true;
                } else if (esParenDCH(part.charAt(0))){
                    dev.add(tokParen(part.charAt(0)));
                    encontradoOp=false;
                }else if (Character.isDigit(part.charAt(0))){
                    dev.add(tokNumber(Integer.parseInt(part)));
                    encontradoOp=false;
                }
            }
        }

        Token[] toks = dev.toArray(new Token[0]);




        return toks;
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
    static private boolean esParenDCH(char c){
        switch (c){
            case ')':
                return true;
            default:
                return false;
        }
    }
    static private boolean esParenIZQ(char c){
        if (c == '('){
            return true;
        }
        return false;
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


    static private String espaciado(String s){
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
