import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.awt.image.BufferedImage;

class ClientHandlerSlave1 implements Runnable {

    private Socket socketClient;

    public ClientHandlerSlave1(Socket socketClient) {
        this.socketClient = socketClient;
    }

    public void run() {
        
        try {
            
            while(true){ 


                DataInputStream dis = new DataInputStream(socketClient .getInputStream());
                DataOutputStream dos = new DataOutputStream(socketClient .getOutputStream());
                int choix = dis.readInt(); 
                if(choix==5){
                    BufferedImage receivedImage = image.Receive(dis);
                    int[][] matrix1 = MatrixUtils.readMatrix(dis);
                    BufferedImage result= image.applyConvolution(receivedImage, matrix1 );
                    image.send(result, dos);
                }
                else{
                    
                    int[][] subMatrix1 = MatrixUtils.readMatrix(dis);
                    int[][] subMatrix2 = MatrixUtils.readMatrix(dis);
                    int[][] subMatrixResult1=MatrixAddition.sendResult(subMatrix1, subMatrix2,choix);
                    MatrixUtils.sendMatrix(dos, subMatrixResult1);
                }
            
           }
        }catch (IOException e) {
        e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
         }
        //  finally {
        //     try {
        //         socketClient.close();
        //     } catch (IOException e) {
        //         e.printStackTrace();
        //     }
        // }
    }
}

