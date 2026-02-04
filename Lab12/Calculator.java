package Lab1;
import java.util.Scanner;
import java.util.ArrayList;

public class Calculator {
    public static Scanner sc = new Scanner(System.in);
    public static Algebraic a;
    public static Algebraic b;
    public static int operatorLen;
    public static void main (String [] args){
        algebraicHandler(true);
        boolean exited = false;

        while (!exited){
            exited = operationHandler();
        }
    }

    public static void algebraicHandler(boolean isPrimary){
        if (isPrimary){
            System.out.println("Enter a vector or a matrix");    
        } else {
            System.out.println("Enter the second vector or matrix");
        }
        System.out.print("Enter number of rows and columns (n x m): ");
        String rowAndColumn = sc.nextLine().trim();
        int blankIndex = rowAndColumn.indexOf(' ');
        int rowSize = Integer.valueOf(rowAndColumn.substring(0, blankIndex));
        int colSize = Integer.valueOf(rowAndColumn.substring(blankIndex + 1));

        if (rowSize == 1){
            float[] vecArr = new float[colSize];
            vectorHandler(vecArr, isPrimary);
        } else if (rowSize >= 1){
            float[][] matArr = new float[rowSize][colSize];
            matrixHandler(matArr, isPrimary);
        }
    }

    public static void vectorHandler(float[] vecArr, boolean isPrimary){
        System.out.print("Enter vector elements separated by spaces: ");
        for (int i = 0; i < vecArr.length; i++){
            vecArr[i] = sc.nextFloat();
        }
        sc.nextLine();
        if (isPrimary){
            a = new Vector(vecArr);
            System.out.println(a);
        } else {
            b = new Vector(vecArr);
        }
    }

    public static void matrixHandler(float[][] matArr, boolean isPrimary){
        System.out.print("Enter matrix elements separated by spaces: ");
        for (int row = 0; row < matArr.length; row++){
            for (int col = 0; col < matArr[0].length; col++){
                matArr[row][col] = sc.nextFloat();
            }
        } // TODO: check for ltmatrix 
        sc.nextLine();
        if (isPrimary){
            a = new Matrix(matArr);
            if (LTMatrix.checkLTMatrix((Matrix)a)){
                System.out.println("Constructed the LTMatrix");
            }
            System.out.println(a);
        } else {
            b = new Matrix(matArr);
            if (LTMatrix.checkLTMatrix((Matrix)b)){
                System.out.println("Constructed the LTMatrix");
            }
        }    
    }

    public static boolean operationHandler(){
        boolean exited = false;
        System.out.printf("""

                Select an operation:
                1: Negate
                2: Add
                3: Subtract
                4: Multiply
                5: %s
                6: Compare
                7: Exit
                \n""", (a instanceof Vector) ? "Cross Product" : "Determinant");

        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice){
            case 1:
                Algebraic negated = a.negate();
                if (negated == null){
                    System.out.println("Invalid Operation, null");
                } else {
                    operatorLen = 1;
                    printNotation(false, "-", negated, "=", false);
                    a = negated;    
                }
                break;
            case 2:
                algebraicHandler(false);
                Algebraic added = a.add(b);
                if (added == null){
                    System.out.println("Invalid Operation, null");
                } else {
                    operatorLen = 1;
                    printNotation(true, "+", added, "=", false);
                    a = added;
                }
                break;
            case 3:
                algebraicHandler(false);
                Algebraic subtracted = a.subtract(b);
                if (subtracted == null){
                    System.out.println("Invalid Operation, null");
                } else {
                    operatorLen = 1;
                    printNotation(true, "-", subtracted, "=", false);
                    a = subtracted;
                }               
                break;
            case 4:
                algebraicHandler(false);
                Algebraic multiplied = a.multiply(b);
                if (multiplied == null){
                    System.out.println("Invalid Operation, null");
                } else {
                    operatorLen = 1;
                    printNotation(true, "*", multiplied, "=", false);
                    a = multiplied;   
                }     
                break;
            case 5:
                if (a instanceof Vector){
                    algebraicHandler(false);
                    Vector crossprod = ((Vector)a).crossProduct((Vector) b);
                    if (crossprod == null){
                        System.out.println("Invalid Operation, null");
                    } else {
                        operatorLen = 1;
                        printNotation(true, "x", crossprod, "=", false);
                        a = crossprod;   
                    }                       
                } else if (a instanceof Matrix){
                    Algebraic determinant = ((Matrix)a).determinant();
                    if (determinant == null){
                        System.out.println("Invalid Operation, null");
                    } else {;
                        a = determinant;
                        System.out.println(a); 
                    }                         
                }
                break;
            case 6:
                algebraicHandler(false);
                boolean isEqual = a.equals(b);
                operatorLen = 2;
                printNotation(true, "==", null, "==>", true);
                // printNotation(true, "+");  // compare ?? 
                break;
            case 7:
                exited = true;
                System.out.println("Exiting...");
                break;
            default:
                break;
        }

        return exited;
    }

    public static void printNotation(boolean dependantOnB, String operator, Algebraic result, String secondOp, boolean compareMode){
        ArrayList<StringBuilder> allLines = new ArrayList<StringBuilder>();
        if (compareMode){
            for (int i = 0; i < Math.max(a.getRows(), b.getRows()); i++){
                allLines.add(new StringBuilder());
            } 
            add(allLines, a); // print a
            add(allLines, operator); // print operator
            add(allLines, b); // print b
            add(allLines, secondOp); // print = 
            add(allLines, String.valueOf(a.equals(b))); // print result                               
        } else {
            if (dependantOnB){
                for (int i = 0; i < Math.max(a.getRows(), b.getRows()); i++){
                    allLines.add(new StringBuilder());
                }
                add(allLines, a); // print a
                add(allLines, operator); // print operator
                add(allLines, b); // print b
                add(allLines, "="); // print = 
                add(allLines, result); // print result
            } else {
                for (int i = 0; i < a.getRows(); i++){
                    allLines.add(new StringBuilder());
                }            
                add(allLines, operator); // print operator
                add(allLines, a); // print a
                add(allLines, "="); // print = 
                add(allLines, result); // print result
            }
        }

        for (StringBuilder sb: allLines){
            System.out.printf("%s\n", sb.toString());
        }
    }

    public static void add(ArrayList<StringBuilder> allLines, Algebraic other){
        for (int i = 0; i < allLines.size(); i++){
            allLines.set(i, allLines.get(i).append(  getLine(other.toString(), i + 1)  ));
        }
    }
    
    public static void add(ArrayList<StringBuilder> allLines, String op){
        for (int i = 0; i < allLines.size(); i++){
            if (i == a.getRows() / 2){
                allLines.set(i, allLines.get(i).append(" " + op + " "));
            } else {
                allLines.set(i, allLines.get(i).append(" ".repeat(operatorLen + 2)));                
            }
        }        
    }

    public static String getLine(String multilineStr, int line){
        String[] lineArray = multilineStr.split("\n");

        if (lineArray.length >= line){
            return lineArray[line - 1];
        }

        return " ".repeat((a.getCols() * 6) + 2 + 2 + operatorLen + 1);
    }

}
