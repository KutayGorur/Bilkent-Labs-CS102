package Lab1;

public class Vector implements Algebraic {

    private float[] vecArr;
    private int len;

    /*
     * Constructs a new Vector from a given array of floats. The array is
     * copied, so modifying the original array does not affect the vector.
     */
    Vector(float[] vec) {
        len = vec.length;
        vecArr = new float[len];
        for (int i = 0; i < len; i++) {
            vecArr[i] = vec[i];
        }
    }

    // Returns a new Vector with all elements negated.
    public Vector negate() {
        return multiply(-1);
    }

    /*
     * Returns a new Vector whose elements are the sum of this
     * vector and other. Returns null if the vectors have different lengths or other
     * object is not a vector.
     */
    public Vector add(Algebraic other) {
        if (!isVectorAndSameLength(other))
            return null;

        Vector otherVec = (Vector) other;
        float[] addedArr = new float[len];
        for (int i = 0; i < len; i++) {
            addedArr[i] = this.floatAtIndex(i) + otherVec.floatAtIndex(i);
        }
        return new Vector(addedArr);

    }

    /*
     * Returns a new Vector whose elements are the difference
     * between this vector and other. Returns null if the vectors have different
     * lengths or other object is not a vector.
     */
    public Vector subtract(Algebraic other) {
        if (!isVectorAndSameLength(other))
            return null;

        Vector otherVec = (Vector) other;
        float[] subtArr = new float[len];
        for (int i = 0; i < len; i++) {
            subtArr[i] = this.floatAtIndex(i) - otherVec.floatAtIndex(i);
        }
        return new Vector(subtArr);
    }

    /*
     * Returns a new one-dimensional Vector containing the dot
     * product of this vector and other. Returns null if the vectors have different
     * lengths or if the other object is not a Vector.
     * formula: a1.a2 + b1.b2 + c1.c2 ...
     */
    public Vector multiply(Algebraic other) {
        if (!isVectorAndSameLength(other))
            return null;

        Vector otherVec = (Vector) other;
        float total = 0;
        for (int i = 0; i < len; i++) {
            total += this.floatAtIndex(i) * otherVec.floatAtIndex(i);
        }
        return new Vector(new float[] { total });
    }

    public Vector multiply(float f) {
        float[] arrToPass = new float[len];
        for (int i = 0; i < vecArr.length; i++) {
            arrToPass[i] = vecArr[i] * f;
        }
        return new Vector(arrToPass);    
    }

    /*
     * Returns a new Vector representing the cross product of this vector
     * and other. Only defined for 3-dimensional vectors; returns null otherwise.
     * formula: x: (b1.c2 - b2.c1), y: -(a1.c2−a2.c1), z: (a1.b2−a2.b1)
     */
    public Vector crossProduct(Vector other) {
        if (!(isVectorAndSameLength(other) && this.len == 3))
            return null;

        Vector otherVec = (Vector) other;
        float[] crossProdArr = new float[3];
        crossProdArr[0] = (this.floatAtIndex(1) * otherVec.floatAtIndex(2))
                - (otherVec.floatAtIndex(1) * this.floatAtIndex(2));
        crossProdArr[1] = -((this.floatAtIndex(0) * otherVec.floatAtIndex(2))
                - (otherVec.floatAtIndex(0) * this.floatAtIndex(2)));
        crossProdArr[2] = (this.floatAtIndex(0) * otherVec.floatAtIndex(1))
                - (otherVec.floatAtIndex(0) * this.floatAtIndex(1));
        return new Vector(crossProdArr);
    }

    /*
     * Returns true if other is a Vector of the same length with all
     * corresponding elements equal within a tolerance of 10^(−6).
     */
    @Override
    public boolean equals(Object other) {
        boolean isEqual = true;
        if (other instanceof Vector && this.len == ((Vector) other).getRows()) {
            Vector otherVec = (Vector) other;
            for (int i = 0; i < len; i++) {
                if (!(Math.abs(this.floatAtIndex(i) - otherVec.floatAtIndex(i)) <= Math.pow(10, -6))) {
                    isEqual = false;
                    break;
                }
            }
        } else {
            isEqual = false;
        }
        return isEqual;
    }

    public float floatAtIndex(int index) {
        return this.vecArr[index];
    }

    public int getRows() {
        return this.len;
    }

    public int getCols(){
        return 1;
    }

    public boolean isVectorAndSameLength(Algebraic other) {
        return other instanceof Vector && this.len == ((Vector) other).getRows();
    }

    /*
     * Returns a nicely formatted string representing the vector in square brackets,
     * with each value shown to two decimal places.
     */
    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < this.len; i++) {
            result += String.format("|%6.2f|\n", this.floatAtIndex(i));
        }
        return result;
    }
}