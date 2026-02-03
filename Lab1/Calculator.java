package Lab1;
import java.util.Scanner;
import java.util.ArrayList;

public class Calculator {
    public static Scanner sc = new Scanner(System.in);
    public static Algebraic a;
    public static Algebraic b;
    public static void main (String [] args){

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
            vectorMenuHandler(vecArr);
        } else if (rowSize >= 1){
            float[][] matArr = new float[rowSize][colSize];
            matrixMenuHandler(matArr);
        }
    }

    public static void vectorMenuHandler(float[] vecArr){
        System.out.print("Enter vector elements separated by spaces: ");
        for (int i = 0; i < vecArr.length; i++){
            vecArr[i] = sc.nextFloat();
        }
        sc.nextLine();
        a = new Vector(vecArr);
        System.out.println(a);
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
                """);

        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1){

            System.out.println("");
        } else if (choice == 7){
            exited = true;
        } else if (choice >= 2 && choice <= 6){

        } else {
            // default
        }

        switch (choice){
            case 1:

                break;
            case 2:
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

}
