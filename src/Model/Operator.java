package Model;

public enum Operator {
    //operation allowed: +, -, * ,/, ^
    ADDITION("+"),
    SUBTRACTION("-"),
    MULTIPLICATION("*"),
    DIVISION("/"),
    EXPONENT("^"),
    EQUALS("="),
    NANI("???"); //no actual use, just add another line to LOC, maybe not
    
    private String symbol;

    Operator(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
