package mathematica;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author Ekaterina Goranova
 */
public class Run {

    public static void main(String[] args) throws IOException {
        
        Console console = new Console();
        console.writeLine("Type 'exit' to exit");
        while(true) {
            String input = console.readLine();
            if (input.equals("exit")) {
                break;
            }
            ShuntingYardParser parser = new ShuntingYardParser(input);
            List<Token> list = parser.parse();
            RPNParser rpn = new RPNParser(list);
            console.writeResult(String.valueOf(rpn.calculate()));
        }
    }   
}
