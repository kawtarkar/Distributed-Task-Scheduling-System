import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.util.Scanner;
public class Client {
    public static void main(String[] args) throws Exception {
        try {

            // Establish connection with the server
            Socket socket = new Socket("169.254.29.7", 6615);

            // Create input and output streams for the socket
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            // Create scanner to read user input from the console
            Scanner scanner = new Scanner(System.in);
          
            // Ask the user for the operation choice
            System.out.println("Choose an operation:");
            System.out.println("1. Addition");
            System.out.println("2. Multiplication");
            System.out.println("3. Subtraction");
            System.out.println("4. Division");
            System.out.println("5. Convolution");
            int choix = scanner.nextInt();
            if (choix==5){
                dos.writeInt(choix);
                System.out.println("Entrer le chemin d'une image ");
                String imagepath = scanner.next();
                image.sent(dos,imagepath);
                System.out.println("Entrer les dimensions de la matrix:");
                System.out.print("Rows: ");
                int rows1 = scanner.nextInt();
                System.out.print("Columns: ");
                int cols1 = scanner.nextInt();
                int[][] matrix1 = new int[rows1][cols1];
                System.out.println("Entrer les elements de la matrix:");
                for (int i = 0; i < rows1; i++) {
                    for (int j = 0; j < cols1; j++) {
                        System.out.print("M["+i+"]["+j+"] = ");
                        matrix1[i][j] = scanner.nextInt();
                    }
                }
                MatrixUtils.sendMatrix(dos, matrix1);
                BufferedImage result = image.Receive(dis);
                image.displayImage(result);
               

            }



            else{ 
            
            // Ask the user to input the first matrix
            System.out.println("Enter the dimensions of the first matrix:");
            System.out.print("Rows: ");
            int rows1 = scanner.nextInt();
            System.out.print("Columns: ");
            int cols1 = scanner.nextInt();


            int[][] matrix1 = new int[rows1][cols1];
            System.out.println("Enter the elements of the first matrix:");
            for (int i = 0; i < rows1; i++) {
                for (int j = 0; j < cols1; j++) {
                    System.out.print("M["+i+"]["+j+"] = ");
                    matrix1[i][j] = scanner.nextInt();
                }
            }
            int cols2=0,rows2=0;
            if(choix==2){
                do{
            System.out.println("Enter the dimensions of the second matrix:");
            System.out.print("Rows: ");
            rows2 = scanner.nextInt();
            System.out.print("Columns: ");
            cols2 = scanner.nextInt();
            if(cols1!=rows2)
            System.out.println("the number of columns in the first matrix must be equal to the number of rows in the second matrix.");
            }
            while(cols1!=rows2);
            }
            else{
                do{
                    System.out.println("Enter the dimensions of the second matrix:");
                    System.out.print("Rows: ");
                    rows2 = scanner.nextInt();
                    System.out.print("Columns: ");
                    cols2 = scanner.nextInt();
                    if(rows1!=rows2&&cols1!=cols2)
                    System.out.println("the two matrices must have the same dimensions");
                }
                    while(rows1!=rows2&&cols1!=cols2);

            }
            // Ask the user to input the second matrix
            
            
                int[][] matrix2 = new int[rows2][cols2];
                System.out.println("Enter the elements of the second matrix:");
                for (int i = 0; i < rows2; i++) {
                    for (int j = 0; j < cols2; j++) {
                        System.out.print("M["+i+"]["+j+"] = ");
                        matrix2[i][j] = scanner.nextInt();
                    }
                }
            
            // Send the choice and matrices to the server
            
            dos.writeInt(choix);
            MatrixUtils.sendMatrix(dos, matrix1);
            MatrixUtils.sendMatrix(dos, matrix2);

            int rowsResult = dis.readInt();
            int colsResult = dis.readInt();
            int[][] result = new int[rowsResult][colsResult];
            for (int i = 0; i < rowsResult; i++) {
                for (int j = 0; j < colsResult; j++) {
                    result[i][j] = dis.readInt();
                }
            }
        
            // Print the result
            System.out.println("Result:");
            for (int i = 0; i < rowsResult; i++) {
                for (int j = 0; j < colsResult; j++) {
                System.out.print(result[i][j] + " ");
                }
            System.out.println();
            }
       
                    

    
            // Close the socket
                   // Close the socket and input/output streams
        
        dis.close();
        dos.close();
        
    }

    } catch (IOException e) {
        System.out.println("Error: " + e.getMessage());
    }
}

}

