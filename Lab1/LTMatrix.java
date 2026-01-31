package Lab1;

public class LTMatrix {
    /*Derive a new LTMatrix, which is a square, lower triangular matrix, by extending the Matrix
    class using inheritance. It has zero entries over the diagonal. An example 5 x 5 lower triangular
    matrix (LTM) is given below:

    The constructor LTMatrix(float[][] mat) constructs a new lower triangular matrix from the given
    two-dimensional float array. The input array must represent a square matrix with all entries
    above the main diagonal equal to zero. If the input array does not represent a valid lower
    triangular matrix, print an appropriate error message and construct a 0x0 matrix.
    
    Since an LTMatrix has zero entries above the main diagonal, its matrix operations can be
    implemented more efficiently. You must avoid iterating over the zero elements by limiting your
    loops to the lower triangular region. Override the following LTMatrix methods and implement
    them efficiently:
    •
    negate(): Returns a new LTMatrix with all lower triangular entries negated. This
    method should be implemented efficiently by processing only the lower triangular
    entries.
    •
    add(Algebraic other): Returns a new Matrix whose entries are the elementwise sum
    of this matrix and other. If other is an LTMatrix, this method should perform the addition
    efficiently and return a new LTMatrix; otherwise, it should perform regular matrix
    addition and return a new Matrix. Similar to matrix addition, this method should return
    null if the matrices have different dimensions or other is not a Matrix.
    •
    subtract(Algebraic other): Returns a new Matrix whose entries are the elementwise
    difference of this matrix and other. If other is an LTMatrix, this method should perform
    the subtraction efficiently and return a new LTMatrix; otherwise, it should perform
    regular matrix subtraction and return a new Matrix. Similar to matrix subtraction, this
    method should return null if the matrices have different dimensions or other is not a
    Matrix.
    •
    multiply(Algebraic other): Returns the product of this matrix and the other object.
    o If other is a LTMatrix: Performs efficient matrix multiplication and returns a new
    LTMatrix.
    o If other is a Matrix: Performs matrix multiplication and returns a new Matrix.
    o If other is a Vector: Performs matrix-vector multiplication (applying the matrix
    transformation to the vector) and returns a new Vector.
    o Returns null if the dimensions are incompatible for the specific operation.
    •
    determinant(): Returns the determinant of the matrix.
    •
    equals(Object other): Compares this matrix with another object for equality. Similar to
    the Matrix class, two matrices are considered equal if they have the same dimensions
    and all corresponding elements are equal within a tolerance of 10−6. If the other is an
    LTMatrix, only the lower triangular entries need to be compared. If the other is a
    Matrix, all matrix entries must be compared. */
}
