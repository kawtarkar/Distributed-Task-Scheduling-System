import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class MatrixUtils {




    public static int[][] sum(int[][] a, int[][] b) {
        
        int m = a.length;
        int n = a[0].length;
        
        int[][] result = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }
        return result;
        
    }



    // public static int[][] product(int[][] a, int[][] b) {
    //     int m = a.length;
    //     int p = b[0].length;
    //     int[][] result = new int[m][p];
    //     for (int i = 0; i < m; i++) {
    //         for (int j = 0; j < p; j++) {
    //             for (int k = 0; k < b.length; k++) {
    //                 result[i][j] += a[i][k] * b[k][j];
    //             }
    //         }
    //     }
    //     return result;
    // }
    public static int[][] product(int[][] a, int[][] b) {
        int aRows = a.length;
        int aCols = a[0].length;
        int bCols = b[0].length;
    
        int[][] result = new int[aRows][bCols];
    
        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bCols; j++) {
                for (int k = 0; k < b.length; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
    
        return result;
    }

    public static int[][] subtract(int[][] a, int[][] b) {
        int m = a.length;
        int n = a[0].length;
        int[][] result = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = a[i][j] - b[i][j];
            }
        }
        return result;
    }

    public static int[][] divide(int[][] a, int[][] b) {
        int m = a.length;
        int n = a[0].length;
        int[][] result = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = a[i][j] / b[i][j];
            }
        }
        return result;
    }
    public static int[][][] divideMatrix(int[][] mat) {
        int m = mat.length; // number of rows in matrix
        int n = mat[0].length; // number of columns in matrix
        int mid = n / 2; // midpoint of columns
    
        int[][] leftSubmatrix = new int[m][mid];
        int[][] rightSubmatrix = new int[m][n - mid];
    
        for (int i = 0; i < m; i++) {
            leftSubmatrix[i] = Arrays.copyOfRange(mat[i], 0, mid);
            rightSubmatrix[i] = Arrays.copyOfRange(mat[i], mid, n);
        }
    
        int[][][] submatrices = { leftSubmatrix, rightSubmatrix };
        return submatrices;
    }
    public static int[][][] divideMatrix(int[][] matrix,int n) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        // Calculate the sizes of the two submatrices
        int subRows = rows /n;
        int subCols = cols ;
        
        // Create the two submatrices
        int[][] subMatrix1 = new int[subRows][subCols];
        int[][] subMatrix2 = new int[rows - subRows][subCols];
        
        // Fill the submatrices
        for (int i = 0; i < subRows; i++) {
            for (int j = 0; j < subCols; j++) {
                subMatrix1[i][j] = matrix[i][j];
                subMatrix2[i][j] = matrix[i + subRows][j];
            }
        }
        for (int i = subRows; i < rows; i++) {
            for (int j = 0; j < subCols; j++) {
                subMatrix2[i - subRows][j] = matrix[i][j];
            }
        }
        
        // Combine the submatrices into a single 3D array and return it
        int[][][] result = new int[2][][];
        result[0] = subMatrix1;
        result[1] = subMatrix2;
        return result;
    }
    public static int[][] mergeMatrices(int[][] matrix1, int[][] matrix2) {
        int numRows1 = matrix1.length;
        int numRows2 = matrix2.length;
        int numCols = matrix1[0].length;
        int[][] mergedMatrix = new int[numRows1 + numRows2][numCols];
        
        // Copy elements from matrix1 to the merged matrix
        for (int i = 0; i < numRows1; i++) {
            for (int j = 0; j < numCols; j++) {
                mergedMatrix[i][j] = matrix1[i][j];
            }
        }
        
        // Copy elements from matrix2 to the merged matrix
        for (int i = 0; i < numRows2; i++) {
            for (int j = 0; j < numCols; j++) {
                mergedMatrix[numRows1 + i][j] = matrix2[i][j];
            }
        }
        
        return mergedMatrix;
    }
    
    
    
    
    public static int[][]  readMatrix(DataInputStream dataInputStream) throws IOException{
        int rows=  dataInputStream.readInt();
        int cols =  dataInputStream.readInt();
        int[][] matrix =new int[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = dataInputStream.readInt();
            }
        }
        return matrix;
      }
      public static   void sendMatrix(DataOutputStream dataOutputStream,int[][] matrix) throws IOException{
        int rows =matrix.length;
        int cols =matrix[0].length;
        dataOutputStream.writeInt(rows);
        dataOutputStream.writeInt(cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                dataOutputStream.writeInt(matrix[i][j]);
            }
        }

        }
        public static int[][] taperMatrix(int rows,int cols,String message,Scanner scanner){
            int[][] matrix =new int[rows][cols];
            System.out.println(message);
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    System.out.print("M["+i+"]["+j+"] = ");
                    matrix[i][j] = scanner.nextInt();
                }
            }
            return matrix;
        }
    
}