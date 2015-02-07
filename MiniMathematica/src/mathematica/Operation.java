package mathematica;

/**
 *
 * @author Ekaterina Goranova
 */
public class Operation implements Token, Comparable<Operation> {
    private String sign;
    private Notation notation;
    private int precedence;
    private int argsCount;
    
    public Operation(String sign, Notation notation, int precedence, int argsCount) {
        this.sign = sign;
        this.notation = notation;
        this.precedence = precedence;
        this.argsCount = argsCount;
    }
    
    public int getPrecedence() {
        return precedence;
    }
    
    public String getSign() {
        return sign;
    }
    
    public Notation getNotation() {
        return notation;
    }
    
    public int getArgCount() {
        return argsCount;
    }
        
    @Override
    public int compareTo(Operation t) {
        return precedence < t.getPrecedence() ? -1 : precedence > t.getPrecedence() ? 1 : 0;
    }
    
    @Override
    public String toString() {
        return sign;
    }
}
