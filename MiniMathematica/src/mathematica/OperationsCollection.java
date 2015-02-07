package mathematica;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ekaterina Goranova
 */
public class OperationsCollection {
    private static OperationsCollection instance = null;
    private Map<Notation, Operation> operations;

    private OperationsCollection() {
        operations = new HashMap<Notation, Operation>();
        generateOperations();
    }
    
    public static OperationsCollection getOperations() {
        if(instance == null) {
            instance = new OperationsCollection();
        }
        return instance;
    }

    private void generateOperations() {
        // Operation(notation, precedence, args_count)
        operations.put(Notation.LEFT_PAREN, new Operation("(", Notation.LEFT_PAREN, 0, 0));
        operations.put(Notation.RIGHT_PAREN, new Operation(")", Notation.RIGHT_PAREN, 0, 0));
        
        operations.put(Notation.PI_NUMB, new Operation("pi", Notation.PI_NUMB, 5, 0));
        operations.put(Notation.E_NUMB, new Operation("e", Notation.E_NUMB, 5, 0));
        
        operations.put(Notation.ADD_B, new Operation("+", Notation.ADD_B, 1, 2));
        operations.put(Notation.SUB_B, new Operation("-", Notation.SUB_B, 1, 2));
        operations.put(Notation.MULT_B, new Operation("*", Notation.MULT_B, 2, 2));
        operations.put(Notation.DIV_B, new Operation("/", Notation.DIV_B, 2, 2));
        operations.put(Notation.MINUS_U, new Operation("-", Notation.MINUS_U, 4, 1));
        operations.put(Notation.SQRT_U, new Operation("sqrt", Notation.SQRT_U, 4, 1));
        operations.put(Notation.POW_B, new Operation("pow", Notation.POW_B, 4, 2));
        operations.put(Notation.LOG_B, new Operation("log", Notation.LOG_B, 4, 2));
        operations.put(Notation.SIN_U, new Operation("sin", Notation.SIN_U, 4, 1));
        operations.put(Notation.COS_U, new Operation("cos", Notation.COS_U, 4, 1));
        operations.put(Notation.TG_U, new Operation("tg", Notation.TG_U, 4, 1));
        operations.put(Notation.COTG_U, new Operation("cotg", Notation.COTG_U, 4, 1));
    }
        
    public Operation getOperation(Notation notation) {
        return operations.get(notation);
    }
    
    public Notation getNotation(String input) {
        for(Map.Entry<Notation, Operation> op : operations.entrySet()) {
            if(op.getValue().getSign().equals(input)) {
                return op.getKey();
            }
        }
        return null;
    }
    
    public boolean containsOperation(String notation) {
        for(Map.Entry<Notation, Operation> op : operations.entrySet()) {
            if(op.getValue().getSign().equals(notation)) {
                return true;
            }
        }
        return false;
    }
}
