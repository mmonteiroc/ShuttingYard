import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Token {
    enum Toktype {
        NUMBER, OP, PAREN
    }
    private Toktype ttype;
    private int value;
    private char tk;

    // Constructor privado para evitar
    // poder instanciar un nuevo token fuera de esta clase
    private Token() {
    }

    /**
     * @param value valor del token que crearemos
     * @return devolvemos el token ya creado
     *
     * Este metodo lo que hace es recivir un valor el cual sera un numero integro.
     * Despues instancia un nuevo token, y asignamos un tipo de token "Number"
     * Asignamos el valor que le corresponde y retornamos dicho token
     */
    static Token tokNumber(int value) {
        Token dev = new Token();
        dev.ttype = Toktype.NUMBER;
        dev.value = value;
        return dev;
    }

    /**
     * @param c Operador de dicho token
     * @return Retornamos un token con sus cracteristicas que le corresponden
     *
     * Lo que hacemos es recibir el caracter del token que le corresponde
     * Ex: + - * / ^
     * Despues instanciamos un nuevo token y le asignamos los
     * valores que le pertañen
     */
    static Token tokOp(char c) {
        Token dev = new Token();
        dev.ttype = Toktype.OP;
        dev.tk = c;
        return dev;
    }

    /**
     * @param c Parentesis de dicho operador
     * @return Devolvemos el token una vez creado
     *
     * Recibimos el parentesis que queremos crear en token
     * Añadimos a dicho token todos
     * sus valores necesarios y lo retornamos
     */
    static Token tokParen(char c) {
        Token devolver = new Token();
        devolver.ttype = Toktype.PAREN;
        devolver.tk = c;
        return devolver;
    }

    // Mostra un token (conversió a String)
    public String toString() {
        return " "+tk+" "+value;
    }

    // Mètode equals. Comprova si dos objectes Token són iguals
    public boolean equals(Object o) {
        if (this.tk == ((Token) o).tk){
            if(this.value == ((Token) o).value){
                return this.ttype == ((Token) o).ttype;
            }
        }
        return false;
    }

    // A partir d'un String, torna una llista de tokens
    public static Token[] getTokens(String expr) {
        List<Token> dev = new ArrayList<>();
        String[]  partes = espaciado(expr).split("\\s+");

        boolean encontradoOp = true;
        for (String part : partes) {
            if (part.length() != 0) {
                if (part.contains("-") && encontradoOp) {
                    dev.add(tokNumber(-1));
                    dev.add(tokOp('*'));
                    encontradoOp = false;
                } else if (esOp(part.charAt(0))) {
                    encontradoOp = true;
                    dev.add(tokOp(part.charAt(0)));
                } else if (esParenIZQ(part.charAt(0))) {
                    dev.add(tokParen(part.charAt(0)));
                    encontradoOp = true;
                } else if (esParenDCH(part.charAt(0))) {
                    dev.add(tokParen(part.charAt(0)));
                    encontradoOp = false;
                } else if (Character.isDigit(part.charAt(0))) {
                    dev.add(tokNumber(Integer.parseInt(part)));
                    encontradoOp = false;
                }
            }
        }
        Token[] toks = dev.toArray(new Token[0]);
        System.out.println(Arrays.toString(toks));
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
    static private boolean esParenDCH(char c){ return c == ')'; }
    static private boolean esParenIZQ(char c){ return c == '('; }

    public Toktype getTtype() {
        return ttype;
    }
    public int getValue() {
        return value;
    }
    public char getTk() { return tk; }

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
