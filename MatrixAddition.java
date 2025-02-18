
import java.io.IOException;

public class MatrixAddition {

   
    public static int [][] sendResult( int[][] matrix1, int[][] matrix2,int chose) throws IOException {
        int [][] result=new int[matrix1.length][matrix1[0].length];
        switch (chose) {
            case 1:
                
             result =  MatrixUtils.sum(matrix1, matrix2);
                break;            
                case 2:
                   result = MatrixUtils.product(matrix1, matrix2);
                
                break;            
                case 3:
                   result = MatrixUtils.subtract(matrix1, matrix2);
                
                break;            
                case 4:
                   result = MatrixUtils.divide(matrix1, matrix2);
                
                break;
                
        
            default:
                break;
        }
    

        return result;
       
    }
    
}
