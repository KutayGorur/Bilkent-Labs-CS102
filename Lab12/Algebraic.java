package Lab1;

public interface Algebraic {
    Algebraic negate(); 

    Algebraic add(Algebraic other); 

    Algebraic subtract(Algebraic other);

    Algebraic multiply(Algebraic other);
}