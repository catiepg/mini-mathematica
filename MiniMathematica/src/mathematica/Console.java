package mathematica;

import java.io.*;

/**
 *
 * @author Ekaterina Goranova
 */
public class Console {
    private BufferedReader reader;

    public String readLine() {
    	System.out.print("Enter expression: ");
        try {
            final String result = bufferedReader().readLine();
	    //System.out.println(); //add a new line
	    return result;
	} catch (IOException e) {
	    e.printStackTrace();
	}
	    return null;
    }
	
    public void writeResult(String output){
	System.out.println("Result: " + output);
    }
    
    public void writeLine(String output) {
        System.out.println(output);
    }

    private BufferedReader bufferedReader() {
	return reader == null? (reader = new BufferedReader(new InputStreamReader(System.in))) : reader;
    }
}
