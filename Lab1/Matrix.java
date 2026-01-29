package Lab1;

public class Matrix {
    /*You will implement a Matrix class that represents a mathematical matrix whose elements are
of type float. The class will implement the Algebraic interface. A matrix is stored internally as a
two-dimensional array of floats, where each row represents a row of the matrix. */

    /*Matrix(float[][] mat): Constructs a new Matrix from a given two-dimensional float array.
The input array is copied.

    negate(): Similar to the Vector class, this method returns a new Matrix with all elements
negated. The original matrix must not be modified. Returns null if the matrix is invalid.

    add(Algebraic other): Returns the elementwise sum of this matrix and other. The
matrices must have the same dimensions. Returns null if other is not a Matrix or if the
matrices have different dimensions.

    subtract(Algebraic other): Returns the elementwise difference between this matrix and
other. The matrices must have the same dimensions. Returns null if other is not a
Matrix or if the matrices have different dimensions.

    multiply(Algebraic other): Returns the product of this matrix and the other object.
o If other is a Matrix: Performs matrix multiplication (see definition below) and
returns a new Matrix.
o If other is a Vector: Performs matrix-vector multiplication (applying the matrix
transformation to the vector) and returns a new Vector.
o Returns null if the dimensions are incompatible for the specific operation.

    determinant(): Returns the determinant of the matrix. This operation is only defined for
2x2 or 3x3 square matrices. Returns a 1D Vector containing the result. If the matrix is
not 2x2 or 3x3, return null.

    equals(Object other): Compares this matrix with another object for equality. Two
matrices are equal if they have the same dimensions and all of their corresponding
elements are equal within a tolerance of 10−6.

    toString(): Returns a formatted string representation of the matrix.*/
}
