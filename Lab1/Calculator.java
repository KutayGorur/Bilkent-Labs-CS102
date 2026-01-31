package Lab1;

public class Calculator {
    public static void main (String [] args){
        /*final Vector v1 = new Vector(new float[]{3.f, 2.f, 5.f});
        final Vector v2 = new Vector(new float[]{1.32f, 3.15f, 1.5f});
        final Vector v3 = new Vector(new float[]{1.32f, 3.15f, 1.5f, 32.f});
        System.out.printf("v1 \n%s\n\n", v1);
        System.out.printf("v2 \n%s\n\n", v2);
        System.out.println();
        System.out.printf("-v1 \n%s\n\n", v1.negate());
        System.out.printf("v1 + v2 \n%s\n\n", v1.add(v2));
        System.out.printf("v2 + v1 \n%s\n\n", v2.add(v1));
        System.out.printf("v1 - v2 \n%s\n\n", v1.subtract(v2));
        System.out.printf("v2 - v1 \n%s\n\n", v2.subtract(v1));
        System.out.println();
        System.out.printf("v1 == null => %s\n", v1.equals(null));
        System.out.printf("v1 == v3 => %s\n", v1.equals(v3));
        System.out.printf("v1 == -v1 => %s\n", v1.equals(v1.negate()));
        System.out.printf("v1 == -(-v1) => %s\n",
        v1.equals(v1.negate().negate()));
        System.out.printf("v1 + v2 == v2 + v1 => %s\n",
        (v1.add(v2)).equals(v2.add(v1)));
        System.out.println();
        System.out.printf("v1 * v2 = %s\n", v1.multiply(v2));
        System.out.printf("v1 * v3 = %s\n", v1.multiply(v3));
        System.out.printf("v1 x v3 = %s\n", v1.crossproduct(v3));
        System.out.printf("v1 x v2 \n%s\n", v1.crossproduct(v2));*/

        final Matrix m1 = new Matrix(new float[][]{{1, 3.2f, 0}, {0, 2, 1}, {0, 2.16f,
        1}});
        final Matrix m2 = new Matrix(new float[][]{{0, 1.f, 0}, {0, 1, 0}, {3.02f, 0, 1}});
        final Matrix m3 = new Matrix(new float[][]{{1.f, 3.f}, {1.f, 4.f}, {5.12f,
        2.31f}});
        final Vector v = new Vector(new float[]{1, 2, 3});
        System.out.printf("m1 \n%s\n\n", m1);
        System.out.printf("m2 \n%s\n\n", m2);
        System.out.printf("m3 \n%s\n\n", m3);
        System.out.printf("v \n%s\n\n", v);
        // Arithmetic operations
        System.out.println();
        System.out.printf("-m1\n%s\n\n", m1.negate());
        System.out.printf("m1 + m2 \n%s\n\n", m1.add(m2));
        System.out.printf("m2 + m3 %s\n\n", m2.add(m3));
        System.out.printf("m1 - m2 \n%s\n\n", m1.subtract(m2));
        System.out.printf("m1 * m2 \n%s\n\n", m1.multiply(m2));
        System.out.printf("m2 * m1 \n%s\n\n", m2.multiply(m1));
        System.out.printf("m1 * m3 \n%s\n\n", m1.multiply(m3));
        System.out.printf("m3 * m1 \n%s\n\n", m3.multiply(m1));
        // Matrix-Vector operations
        System.out.println();
        System.out.printf("m1 + v \n%s\n\n", m1.add(v));
        System.out.printf("v * m1 \n%s\n\n", v.multiply(m1));
        System.out.printf("m3 * v \n%s\n\n", m3.multiply(v));
        System.out.printf("m1 * v \n%s\n\n", m1.multiply(v));
        // Determinant
        System.out.println();
        System.out.printf("|m1| = %s\n", m1.determinant());
        System.out.printf("|m3| = %s\n", m3.determinant());
        // Equality
        System.out.println();
        System.out.printf("m1 == null => %s\n", m1.equals(null));
        System.out.printf("m1 == m2 => %s\n", m1.equals(m2));
        System.out.printf("m1 == m3 => %s\n", m1.equals(m3));
        System.out.printf("m1 == -(-m1) => %s\n", m1.equals(m1.negate().negate()));
        final Algebraic m13 = m1.multiply(m3);
        final Algebraic m23 = m2.multiply(m3);
        final Algebraic m123 = m1.add(m2).multiply(m3);
        System.out.printf("(m1 + m2) * m3 == (m1 * m3) + (m2 * m3) => %s\n",
        m123.equals(m13.add(m23)));
    }
}
