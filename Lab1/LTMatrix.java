package Lab1;

public class LTMatrix extends Matrix {

    /*
     * Constructs a new lower triangular matrix from the given
     * two-dimensional float array. The input array must represent a square matrix
     * with all entries above the main diagonal equal to zero. If the input array does not represent
     * a valid lower triangular matrix, print an appropriate error message and construct a 0x0 matrix.
     */
    LTMatrix(float[][] mat) {
        super(ltmatrixArrayValidifier(mat));
    }

    private static float[][] ltmatrixArrayValidifier(float[][] matArr) {
        if (matArr[0].length != matArr.length) {
            System.out.println("The LTMatrix array is not a square!");
            return new float[0][0]; // 0x0 and errormsg
        }

        float[][] ltmatArr = new float[matArr.length][matArr.length];
        for (int row = 0; row < matArr.length; row++) {
            for (int col = 0; col < matArr[0].length; col++) {
                if (row <= matArr.length - 2 && col > row && matArr[row][col] != 0) {
                    System.out.println("The LTMatrix array has an invalid lower triangle!");
                    return new float[0][0]; // 0x0 and errormsg
                }
                ltmatArr[row][col] = matArr[row][col];
            }
        }
        return ltmatArr;
    }

    /*
     * Returns a new LTMatrix with all lower triangular entries negated. This
     * method should be implemented efficiently by processing only the lower
     * triangular entries.
     */
    @Override
    public LTMatrix negate() {
        float[][] arrToPass = new float[this.rowCount][this.colCount];
        for (int row = 0; row < matArr.length; row++) {
            for (int col = 0; col <= row; col++) {
                arrToPass[row][col] = this.matArr[row][col] * -1;
            }
        }
        return new LTMatrix(arrToPass);
    }

    /*
     * Returns a new Matrix whose entries are the elementwise sum
     * of this matrix and other. If other is an LTMatrix, this method should perform
     * the addition efficiently and return a new LTMatrix; otherwise, it should perform regular
     * matrix addition and return a new Matrix. Similar to matrix addition, this method
     * should return null if the matrices have different dimensions or other is not a Matrix.
     */
    @Override
    public Matrix add(Algebraic other) {
        if (!super.isMatrixAndSameDimension(other) || this.isInvalid)
            return null;
        if (other instanceof LTMatrix) {
            return ltmatAddSubt((LTMatrix) other, true);
        }

        return super.add(other);
    }

    /*
     * Returns a new Matrix whose entries are the elementwise
     * difference of this matrix and other. If other is an LTMatrix, this method
     * should perform the subtraction efficiently and return a new LTMatrix; 
     * otherwise, it should perform regular matrix subtraction and return a new Matrix. 
     * Similar to matrix subtraction, this method should return null if the matrices have 
     * different dimensions or other is not a Matrix.
     */
    @Override
    public Matrix subtract(Algebraic other) {
        if (!super.isMatrixAndSameDimension(other) || this.isInvalid)
            return null;
        if (other instanceof LTMatrix) {
            return ltmatAddSubt((LTMatrix) other, false);
        }

        return super.subtract(other);
    }

    public LTMatrix ltmatAddSubt(LTMatrix otherMat, boolean add) {
        float[][] addSubtArr = new float[this.rowCount][this.colCount];
        for (int row = 0; row < matArr.length; row++) {
            for (int col = 0; col <= row; col++) {
                addSubtArr[row][col] = (add) ? this.matArr[row][col] + otherMat.matArr[row][col]
                        : this.matArr[row][col] - otherMat.matArr[row][col];
            }
        }
        return new LTMatrix(addSubtArr);
    }

    /*
     * Returns the product of this matrix and the other object.
     * o If other is a LTMatrix: Performs efficient matrix multiplication and returns a new LTMatrix.
     * o If other is a Matrix: Performs matrix multiplication and returns a new Matrix.
     * o If other is a Vector: Performs matrix-vector multiplication (applying the matrix
     * transformation to the vector) and returns a new Vector.
     * o Returns null if the dimensions are incompatible for the specific operation.
     */
    @Override
    public Algebraic multiply(Algebraic other) {
        if (this.colCount != ((Matrix) other).rowCount)
            return null;
        if (other instanceof LTMatrix) {
            return ltmatrixMultiplication((LTMatrix) other);
        }

        return super.multiply(other);
    }

    public LTMatrix ltmatrixMultiplication(LTMatrix otherMat) {
        float[][] multArr = new float[this.rowCount][otherMat.colCount];

        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < otherMat.colCount; col++) {
                for (int i = 0; i < this.colCount; i++) {
                    multArr[row][col] += this.matArr[row][i] * otherMat.matArr[i][col];
                    // TODO: Optimize multiplication for LTMatrix
                }
            }
        }
        return new LTMatrix(multArr);
    }

    /*
     * Returns the determinant of the matrix.
     */
    @Override
    public Vector determinant() {
        return new Vector(new float[0]);
        // TODO: Figure out a way to use a recursive algorithm to break matrices down
        // and calculate them with determinants of smaller 2x2 matrices.
    }

    /*
     * Compares this matrix with another object for equality. Similar to
     * the Matrix class, two matrices are considered equal if they have the same
     * dimensions and all corresponding elements are equal within a tolerance of 10−6. 
     * If the other is an LTMatrix, only the lower triangular entries need to be compared. 
     * If the other is a Matrix, all matrix entries must be compared.
     */
    @Override
    public boolean equals(Object other) {
        if (!super.isMatrixAndSameDimension((other)))
            return false;

        if (other instanceof LTMatrix) {
            LTMatrix otherMat = (LTMatrix) other;
            boolean isEqual = true;
            for (int row = 0; row < this.rowCount; row++) {
                for (int col = 0; col <= row; col++) {
                    if (Math.abs(this.matArr[row][col] - otherMat.matArr[row][col]) >= Math.pow(10, -6)) {
                        isEqual = false;
                        return isEqual;
                    }
                }
            }
            return isEqual;
        }
        return super.equals(other);
    }
}
