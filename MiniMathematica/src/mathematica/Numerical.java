package mathematica;

/**
 *
 * @author Ekaterina Goranova
 */
public class Numerical implements Token {
    private double value;
    
    public Numerical(double value) {
        this.value = value;
    }
    
    public Double getValue() {
        return value;
    }
        
    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
