package Lab1;

public class Matrix implements Algebraic {
    protected float[][] matArr;
    protected int rowCount;
    protected int colCount;
    protected boolean isInvalid;

    /*
     * Constructs a new Matrix from a given two-dimensional float array.
     * The input array is copied.
     */
    Matrix(float[][] mat) {
        if (mat == null || mat.length == 0 || mat[0] == null){
            isInvalid = true;
            rowCount = 0;
            colCount = 0;
            return;
        }

        rowCount = mat.length;
        colCount = mat[0].length;
        for (int row = 1; row < rowCount; row++){
            if (mat[row].length != mat[row-1].length){
                isInvalid = true;
                return;
            }
        }

        matArr = new float[rowCount][colCount];

        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++){
                matArr[row][col] = mat[row][col];
            }
        }
    }

    /*
     * Similar to the Vector class, this method returns a new Matrix with all
     * elements negated. The original matrix must not be modified. 
     * Returns null if the matrix is invalid.
     */
    public Matrix negate() {
        if (isInvalid) return null;
        return (Matrix) multiply(-1);
    }

    /*
     * Returns the elementwise sum of this matrix and other. The
     * matrices must have the same dimensions. Returns null if other is not a Matrix
     * or if the matrices have different dimensions.
     */
    public Matrix add(Algebraic other) {
        if (isInvalid) return null;
        if (!isMatrixAndSameDimension(other))
            return null;

        Matrix otherMat = (Matrix) other;
        float[][] addedArr = new float[this.rowCount][this.colCount];
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                addedArr[row][col] = this.matArr[row][col] + otherMat.matArr[row][col];
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
                subtArr[row][col] = this.matArr[row][col] - otherMat.matArr[row][col];
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
                for (int col = 0; col < otherMat.colCount; col++) {
                    for (int i = 0; i < this.colCount; i++){
                        multArr[row][col] += this.matArr[row][i] * otherMat.matArr[i][col];
                    }
                }
            }
            return new Matrix(multArr);

        } else if (other instanceof Vector && this.colCount == ((Vector)other).getRows()){
            Vector otherVec = (Vector) other;
            float[] multArr = new float[this.rowCount];

            for (int row = 0; row < this.rowCount; row++){
                for (int i = 0; i < this.colCount; i++){
                    multArr[row] += this.matArr[row][i] * otherVec.floatAtIndex(i);
                }
            }
            return new Vector(multArr);
        }

        return null;
    }

    // method overload 
    public Algebraic multiply(float f){
        float[][] arrToPass = new float[rowCount][colCount];
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                arrToPass[row][col] = this.matArr[row][col] * f;
            }
        }
        return new Matrix(arrToPass);  
    }

    /*
     * Returns the determinant of the matrix. This operation is only
     * defined for 2x2 or 3x3 square matrices. Returns a 1D Vector containing the result. 
     * If the matrix is not 2x2 or 3x3, return null.
     */
    public Vector determinant(){
        if (! ((this.rowCount == 2 && this.colCount == 2) || (this.rowCount == 3 && this.colCount == 3)) ){
            return null;
        }

        if (this.rowCount == 2 && this.colCount == 2){
            return determinantOf2x2Matrix(this);
        } else if (this.rowCount == 3 && this.colCount == 3){
            float a = matArr[0][0], b = matArr[0][1], c = matArr[0][2];
            float d = matArr[1][0], e = matArr[1][1], f = matArr[1][2];
            float g = matArr[2][0], h = matArr[2][1], i = matArr[2][2];

            float det = a*(e*i - f*h) - b*(d*i - f*g) + c*(d*h - e*g);
            return new Vector(new float[]{det});
        }

        return null;

    }

    private Vector determinantOf2x2Matrix(Matrix mat){
        float det = (mat.matArr[0][0] * mat.matArr[1][1]) 
            - (mat.matArr[0][1] * mat.matArr[1][0]);
        return new Vector(new float[]{det});
    }

    /*
     * Compares this matrix with another object for equality.
     * Two matrices are equal if they have the same dimensions and all of their
     * corresponding elements are equal within a tolerance of 10^(−6).
     */
    @Override
    public boolean equals(Object other){
        if (!isMatrixAndSameDimension((other))) return false;
        Matrix otherMat = (Matrix) other;

        boolean isEqual = true;
        for (int row = 0; row < this.rowCount; row++) {
            for (int col = 0; col < this.colCount; col++) {
                if (Math.abs(this.matArr[row][col] - otherMat.matArr[row][col]) >= Math.pow(10, -6)){
                    isEqual = false;
                    return isEqual;
                }
            }
        }
        return isEqual;
    }
    
    public boolean isMatrixAndSameDimension(Object other) {
        return other instanceof Matrix
                && this.rowCount == ((Matrix) other).rowCount
                && this.colCount == ((Matrix) other).colCount
                && !this.isInvalid 
                && !((Matrix)other).isInvalid;
    }

    /* Returns a formatted string representation of the matrix. */
    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();

        for (int row = 0; row < this.rowCount; row++) {
            res.append("|");
            for (int col = 0; col < this.colCount; col++) {
                res.append(String.format("%6.2f ", this.matArr[row][col]));
            }
            res.append("|\n");
        } 

        return res.toString();
    }

    public int getRows(){
        return this.rowCount;
    }

    public int getCols(){
        return this.colCount;
    }
}