package mathematica;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Ekaterina Goranova
 */
public class ShuntingYardParser {
    private String input;
    private Tokenizer tokenizer;
    private Stack<Operation> operationStack = new Stack<Operation>();
    private List<Token> output = new ArrayList<Token>();
    private OperationsCollection operations;
    
    public ShuntingYardParser(String input) throws IOException {
        this.input = input;
        if(!matchingParenthesis()) {
            throw new IOException("Parenthesis in expression do not match.");
        }
        tokenizer = new Tokenizer(input);
        operations = OperationsCollection.getOperations();
    }
    
    public List<Token> parse() {
        String prevToken = null;
        String token;
        while(tokenizer.hasNext()) {
            token = tokenizer.next();
            if(token.equals("(")) {
                operationStack.push(operations.getOperation(Notation.LEFT_PAREN));
            } else if(token.equals(")")) {
                while(!operationStack.empty() && !operationStack.peek().getSign().equals("(")) {
                    output.add(operationStack.pop());
                }
                operationStack.pop();
            } else if(token.equals(",")) {
                while(!operationStack.empty() && !operationStack.peek().getSign().equals("(")) {
                    output.add(operationStack.pop());
                }
            } else if(token.equals("-")) {
                if((operations.containsOperation(prevToken) && !")".equals(prevToken)) || prevToken == null) {
                    Operation operation = operations.getOperation(Notation.MINUS_U);
                    while(!operationStack.empty() && !(operationStack.peek().getArgCount() <= 1)) {
                        output.add(operationStack.pop());
                    }
                    operationStack.push(operation);
                } else {
                    Operation operation = operations.getOperation(Notation.SUB_B);
                    while(!operationStack.empty()
                            && operation.getPrecedence() < operationStack.peek().getPrecedence()) {
                        output.add(operationStack.pop());
                    }
                    operationStack.push(operation);
                }
            } else if(operations.containsOperation(token)) {
                Notation notation = operations.getNotation(token);
                Operation operation = operations.getOperation(notation);
                while(!operationStack.empty()
                        && operation.getPrecedence() < operationStack.peek().getPrecedence()) {
                    output.add(operationStack.pop());
                }
                operationStack.push(operation);
            } else {
                output.add(new Numerical(Double.parseDouble(token)));
            }
            prevToken = token;
        }
        while(!operationStack.isEmpty()) {
            output.add(operationStack.pop());
        }
        return output; 
    }
    
    private boolean matchingParenthesis() {
        Stack s = new Stack();
        for(int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == '(') {
                s.push(ch);
            } else if(ch == ')') {
                if (s.size() == 0) {
                    return false;
                } else {
                    s.pop();
                }
            }
        }
        return s.size() == 0;
    }
    
    private boolean isNumeric(String input) {
       return input.matches("\\d+(\\.\\d+)?"); 
    }
}
