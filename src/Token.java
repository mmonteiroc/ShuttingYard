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
        String expresion = expr;
        Token[]devolver = new Token[0];
        for (int i = 0; i < expresion.length(); i++) {
            if (esNumero(expresion.charAt(i))){
                int n = sacarNumero(expresion, i);
                devolver = añadirArray(devolver,tokNumber(n));
                StringBuilder s = new StringBuilder();
                s.append(n);
                i+=s.length();
                i--;
                continue;
            }else if (esOp(expresion.charAt(i))){
                devolver = añadirArray(devolver, tokOp(expresion.charAt(i)));
            }else if (esParen(expresion.charAt(i))){
                devolver = añadirArray(devolver,tokParen(expresion.charAt(i)));
            }
        }
        return devolver;
    }

    /**
     * @param t Array actual de tokens
     * @param s Token a añadir
     * @return Array con todos los token
     *
     * Esta funcion simple lo que hace es generar
     * un nuevo array de tokens para añadir el nuevo
     */
    static private Token[] añadirArray(Token[] t, Token s){
        Token[] dev = new Token[t.length+1];
        for (int i = 0; i < t.length; i++) {
            dev[i] = t[i];
        }
        dev[t.length]= s;
        return dev;
    }

    static private int sacarNumero(String s, int i){
        int dev =0;
        String num="";
        for (int j = i; j < s.length(); j++) {
            num += s.charAt(j);
            if (j+1==s.length())break;
            if (esNumero(s.charAt(j+1))) continue;
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
    static private boolean esNumero(char c){
        switch (c){
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
            case '0':
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
}
