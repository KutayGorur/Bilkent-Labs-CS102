package Lab1;
import java.util.Arrays;

public class Matrix implements Algebraic {
    private float[][] matArr;
    private int rowCount;
    private int colCount;

    /*
     * Constructs a new Matrix from a given two-dimensional float array.
     * The input array is copied.
     */
    Matrix(float[][] mat) {
        for (int row = 0; row < mat[0].length; row++) {
            matArr[row] = mat[row];
        }
        rowCount = mat[0].length;
        colCount = mat.length;
    }

    /*
     * Similar to the Vector class, this method returns a new Matrix with all
     * elements negated. The original matrix must not be modified. 
     * Returns null if the matrix is invalid.
     */
    public Matrix negate() {
        float[][] arrToPass = new float[rowCount][colCount];
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                arrToPass[row][col] = this.getFloatAtPosition(row, col) * -1;
            }
        }
        return new Matrix(arrToPass);

        // TODO: wtf is an invalid matrix???
    }

    /*
     * Returns the elementwise sum of this matrix and other. The
     * matrices must have the same dimensions. Returns null if other is not a Matrix
     * or if the matrices have different dimensions.
     */
    public Matrix add(Algebraic other) {
        if (!isMatrixAndSameDimension(other))
            return null;

        Matrix otherMat = (Matrix) other;
        float[][] addedArr = new float[this.rowCount][this.colCount];
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                addedArr[row][col] = this.getFloatAtPosition(row, col) + otherMat.getFloatAtPosition(row, col);
            }
        }
        return new Matrix(addedArr);
    }

    /*
     * Returns the elementwise difference between this matrix and other.
     * The matrices must have the same dimensions. Returns null if other is
     * not a Matrix or if the matrices have different dimensions.
     */
    public Matrix subtract(Algebraic other) {
        if (!isMatrixAndSameDimension(other))
            return null;

        Matrix otherMat = (Matrix) other;
        float[][] subtArr = new float[this.rowCount][this.colCount];
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                subtArr[row][col] = this.getFloatAtPosition(row, col) - otherMat.getFloatAtPosition(row, col);
            }
        }
        return new Matrix(subtArr);
    }

    /*
     * Returns the product of this matrix and the other object.
     *  o If other is a Matrix: Performs matrix multiplication and returns a new Matrix.
     *  o If other is a Vector: Performs matrix-vector multiplication and returns a new Vector.
     *  o Returns null if the dimensions are incompatible for the specific operation.
     */
    public Algebraic multiply(Algebraic other){
        if (other instanceof Matrix && this.colCount == ((Matrix)other).rowCount){
            Matrix otherMat = (Matrix) other;
            float[][] multArr = new float[this.rowCount][otherMat.colCount];

            for (int row = 0; row < rowCount; row++) {
                for (int col = 0; col < colCount; col++) {
                    for (int i = 0; i < this.colCount; i++){
                        multArr[row][col] += this.matArr[row][i] * otherMat.matArr[i][col];
                    }
                }
            }
            return new Matrix(multArr);

        } else if (other instanceof Vector && this.colCount == ((Vector)other).getLength()){
            Vector otherVec = (Vector) other;
            float[] multArr = new float[this.colCount];

            for (int row = 0; row < this.colCount; row++){
                for (int i = 0; i < this.colCount; i++){
                    multArr[row] += this.matArr[row][i] * otherVec.floatAtIndex(i);
                }
            }
            return new Vector(multArr);
        }

        return null;
    }

    /*
     * determinant(): Returns the determinant of the matrix. This operation is only
     * defined for 2x2 or 3x3 square matrices. Returns a 1D Vector containing the result. 
     * If the matrix is not 2x2 or 3x3, return null.
     */
    public Vector determinant(){
        if (! (this.rowCount == 2 && this.colCount == 2) || (this.rowCount == 3 && this.colCount == 3) ){
            return null;
        }

        if (this.rowCount == 2 && this.colCount == 2){
            return determinantOf2x2Matrix(this);
        } else if (this.rowCount == 3 && this.colCount == 3){
            float result = 0;

            for (int i = 0; i < 3; i++){
                float a = this.getFloatAtPosition(0, i);
                
            }
            result += 
            // a.d1 - b.d2 + c.d3
        }

        return null;

    }

    public Vector determinantOf2x2Matrix(Matrix mat){
        float det = (mat.getFloatAtPosition(0, 0) * mat.getFloatAtPosition(1, 1)) 
            - (mat.getFloatAtPosition(0, 1) * mat.getFloatAtPosition(1, 0));
        return new Vector(new float[]{det});
    }

    /*
     * Compares this matrix with another object for equality.
     * Two matrices are equal if they have the same dimensions and all of their
     * corresponding elements are equal within a tolerance of 10−6.
     */
    @Override
    public boolean equals(Object other){
        if (!isMatrixAndSameDimension((other))) return false;
        Matrix otherMat = (Matrix) other;

        boolean isEqual = true;
        for (int row = 0; row < this.rowCount; row++) {
            for (int col = 0; col < this.colCount; col++) {
                if (Math.abs(this.getFloatAtPosition(row, col) - otherMat.getFloatAtPosition(row, col)) >= Math.pow(10, -6)){
                    isEqual = false;
                    return isEqual;
                }
            }
        }
        return isEqual;
    }
    

    public float getFloatAtPosition(int row, int col) {
        return this.matArr[row][col];
    }

    public boolean isMatrixAndSameDimension(Object other) {
        return other instanceof Matrix
                && this.rowCount == ((Matrix) other).rowCount
                && this.colCount == ((Matrix) other).colCount;
    }

    /* Returns a formatted string representation of the matrix. */
    @Override
    public String toString(){
        String result = "";
        float[][] copiedArr = new float[this.rowCount][this.colCount];

        for (int row = 0; row < this.rowCount; row++) {
            for (int col = 0; col < this.colCount; col++) {
                copiedArr[row][col] = Float.parseFloat(String.format("%.2f", this.getFloatAtPosition(row, col)));
            }
        } 

        for (int row = 0; row < this.rowCount; row++){
            String addToResult = Arrays.toString(copiedArr[row]).replace(',', '\u0000');
            result += String.format("|%s|\n", addToResult.substring(1, addToResult.length()));
        }

        return result;
    }
}
