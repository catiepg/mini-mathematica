package mathematica;

import java.util.Iterator;

/**
 *
 * @author Ekaterina Goranova
 */
public class Tokenizer implements Iterator<String> {
    private int pos = 0;
    private String input;
    private String previousToken;
    private OperationsCollection operations;

    public Tokenizer(String input) {
        this.input = input;
        this.operations = OperationsCollection.getOperations();
    }

    @Override
    public boolean hasNext() {
        return (pos < input.length());
    }

    private char peekNextChar() {
        if (pos < (input.length() - 1)) {
            return input.charAt(pos + 1);
        } else {
            return 0;
        }
    }
    
    @Override
    public String next() {
        StringBuilder token = new StringBuilder();
        if(pos >= input.length()) {
            return previousToken = null;
        }
        char ch = input.charAt(pos);
        while(Character.isWhitespace(ch) && pos < input.length()) {
            ch = input.charAt(++pos);
        }
        
        if(Character.isDigit(ch)) {
            while ((Character.isDigit(ch) || ch == '.') && (pos < input.length())) {
                token.append(input.charAt(pos++));
                ch = pos == input.length() ? 0 : input.charAt(pos);
            }
        } else if(ch == '-' && Character.isDigit(peekNextChar())
                && ("(".equals(previousToken) || ",".equals(previousToken)
                || previousToken == null || operations.containsOperation(previousToken))) {
            token.append('-');
            pos++;
            token.append(next());
        } else if(ch == '(' || ch == ')' || ch == ',' || operations.containsOperation(Character.toString(ch))) {
            token.append(ch);
            pos++;
        } else {
            while(Character.isLetter(ch) && (pos < input.length())) {
                token.append(input.charAt(pos));
                pos++;
                ch = pos == input.length() ? 0 : input.charAt(pos);
                if (ch == '-') {
                    break;
                }
            }
            if (!operations.containsOperation(token.toString())) {
                throw new UnsupportedOperationException(token.toString() + " not supported.");
            }
        }
        return previousToken = token.toString();
    }
}
