Mini-mathematica
========
Console application that computes mathematical expressions.

Supports:
* arithmetic operations - addition, substraction, negation, multiplication, division;
* trigonometric functions - sine, cosine, tangent, cotangent;
* other functions - exponentiation, nth root, logarithms;
* constants - e, pi.

Uses [shunting yard algorithm](http://en.wikipedia.org/wiki/Shunting-yard_algorithm) and
[reverse polish notation](http://en.wikipedia.org/wiki/Reverse_Polish_notation).

### Example

```
Enter expression: 5 + sin(pi) / pow(2, 10) - log(e, pow(e, sqrt(4)))
Result: 3.0
```