package mathematica;

import static java.lang.Math.*;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Ekaterina Goranova
 */
public class RPNParser {
    private List<Token> rpn;
    
    public RPNParser(List<Token> rpn) {
        this.rpn = rpn;
    }
    
    public Double calculate() {
        Stack<Double> s = new Stack<Double>();
        Double a, b;
        for(Token token : rpn) {
            if(token.getClass() == Numerical.class) {
                Numerical n = (Numerical) token;
                s.push(n.getValue());
            } else {
                Operation op = (Operation) token;
                switch(op.getNotation()) {
                    case ADD_B:
                        s.push(s.pop() + s.pop());
                        break;
                    case SUB_B:
                        b = s.pop();
                        a = s.pop();
                        s.push(a - b);
                        break;
                    case MULT_B:
                        s.push(s.pop() * s.pop());
                        break;
                    case DIV_B:
                        b = s.pop();
                        a = s.pop();
                        s.push(a / b);
                        break;
                    case MINUS_U:
                        s.push((-1)*s.pop());
                        break;
                    case SQRT_U:
                        s.push(sqrt(s.pop()));
                        break;
                    case POW_B:
                        b = s.pop();
                        a = s.pop();
                        s.push(pow(a, b));
                        break;
                    case LOG_B:
                        b = s.pop();
                        a = s.pop();
                        s.push(log(b)/log(a));
                        break;
                    case SIN_U:
                        a = s.pop();
                        if(a == PI) {
                            s.push(0.0);
                        } else {
                            s.push(sin(a));
                        }
                        break;
                    case COS_U:
                        a = s.pop();
                        if(a == PI/2) {
                            s.push(0.0);
                        } else {
                            s.push(cos(a));
                        }
                        break;
                    case TG_U:
                        s.push(tan(s.pop()));
                        break;
                    case COTG_U:
                        s.push(1.0/tan(s.pop()));
                        break;
                    case PI_NUMB:
                        s.push(PI);
                        break;
                    case E_NUMB:
                        s.push(E);
                        break;
                }
            }
        }
        return s.pop();
    }
}
