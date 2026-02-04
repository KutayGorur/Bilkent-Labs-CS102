package Lab1;
import java.util.Scanner;
import java.util.ArrayList;

public class Calculator {
    public static Scanner sc = new Scanner(System.in);
    public static Algebraic a;
    public static Algebraic b;
    public static int operatorIndex;
    public static void main (String [] args){
        displayMenu();
        boolean exited = false;
        if (a instanceof Vector){
            while (!exited){
                exited = vectorOperationHandler();
            }
        }
    }

    public static void displayMenu(){
        System.out.println("Enter a vector or a matrix:");
        System.out.print("Enter number of rows and columns (n x m): ");
        String rowAndColumn = sc.nextLine().trim();
        int blankIndex = rowAndColumn.indexOf('\u0000');
        int rowSize = Integer.valueOf(rowAndColumn.substring(0, blankIndex));
        int colSize = Integer.valueOf(rowAndColumn.substring(blankIndex + 1));

        if (rowSize == 1){
            float[] vecArr = new float[colSize];
            vectorMenuHandler(vecArr, true);
        } else if (rowSize >= 1){
            float[][] matArr = new float[rowSize][colSize];
            matrixMenuHandler(matArr, true);
        }
    }

    public static void vectorMenuHandler(float[] vecArr, boolean isPrimary){
        System.out.print("Enter vector elements separated by spaces: ");
        for (int i = 0; i < vecArr.length; i++){
            vecArr[i] = sc.nextFloat();
        }
        sc.nextLine();
        if (isPrimary){
            a = new Vector(vecArr);
            operatorIndex = ((Vector)a).getLength() / 2;
            System.out.println(a);
        } else {
            b = new Vector(vecArr);
            System.out.println(b);
        }
    }

    public static void matrixMenuHandler(float[][] matArr, boolean isPrimary){
        System.out.print("Enter matrix elements separated by spaces: ");
        for (int row = 0; row < matArr.length; row++){
            for (int col = 0; col < matArr[0].length; col++){
                matArr[row][col] = sc.nextFloat();
            }
        } // TODO: check for ltmatrix 
        sc.nextLine();
        if (isPrimary){
            a = new Matrix(matArr);
            operatorIndex = ((Vector)a).getLength() / 2;
            System.out.println(a);
        } else {
            b = new Matrix(matArr);
            System.out.println(b);
        }    
    }

    public static boolean vectorOperationHandler(){
        boolean exited = false;
        System.out.println("""
                Select an operation:
                1: Negate
                2: Add
                3: Subtract
                4: Multiply
                5: Cross Product
                6: Compare
                7: Exit
                """); // could possibly merge vector and matrix operation handlers

        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice){
            case 1:
                printNotation(false, "-=");
                break;
            case 2:
                printNotation(true, );
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                exited = true;
                break;
            default:
                break;
        }

        return exited;
    }

    public static void negate(){
        ArrayList<StringBuilder> allLines = new ArrayList<StringBuilder>();
        // insert and append in a for loop to add individual operations 

        if (a instanceof Vector){
            
        } else if (a instanceof Matrix){

        }
    }

    public static void printNotation(boolean dependantOnB, String operators){
        if (dependantOnB){
            // print a, operator, b, =, result
        } else {
            // operator, a, =, result
        }
    }


}
