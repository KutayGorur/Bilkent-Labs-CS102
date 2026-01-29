package Lab1;

public class Calculator {
    public static void main (String [] args){
        final Vector v1 = new Vector(new float[]{3.f, 2.f, 5.f});
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
        System.out.printf("v1 x v2 \n%s\n", v1.crossproduct(v2));
    }
}
