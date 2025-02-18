
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;

class ClientHandler implements Runnable {

    private Socket socketClient;

    public ClientHandler(Socket socketClient) {
        this.socketClient = socketClient;
    }

    public void run() {
        
            try {
                
                while(true){ 
                   
                    DataInputStream dis = new DataInputStream(socketClient.getInputStream());
                    DataOutputStream dos = new DataOutputStream(socketClient.getOutputStream());
                    Socket socket = new Socket("localhost", 4456);
                    DataInputStream disSlave1 = new DataInputStream(socket.getInputStream());
                    DataOutputStream dosSlave1 = new DataOutputStream(socket.getOutputStream());
                    //
                    Socket server2 = new Socket("localhost", 4451);
                    DataInputStream disSlave2 = new DataInputStream(server2.getInputStream());
                    DataOutputStream dosSlave2 = new DataOutputStream(server2.getOutputStream());
                    int choix = dis.readInt(); 
                    dosSlave1.writeInt(choix);
                    dosSlave2.writeInt(choix);
                    
                    if(choix==5){
                        
                        BufferedImage receivedImage = image.Receive(dis);
                        BufferedImage[] subImage=image.getSubImage(receivedImage,2);
                        int[][] matrix1 =MatrixUtils.readMatrix(dis);

                        Thread senderThread1 = new Thread(new SenderThread(dosSlave1, subImage[0], matrix1));
                        Thread senderThread2 = new Thread(new SenderThread(dosSlave2, subImage[1], matrix1));
                        ReceiverThread receiverThread1 = new ReceiverThread(disSlave1);
                        ReceiverThread receiverThread2 = new ReceiverThread(disSlave2);
                        Thread thread1 = new Thread(receiverThread1);
                        Thread thread2 = new Thread(receiverThread2);

                        

                        senderThread1.start();
                        senderThread2.start();
                        thread1.start();
                        thread2.start();

                        // Wait for threads to finish
                        senderThread1.join();
                        senderThread2.join();
                        thread1.join();
                        thread2.join();
                        BufferedImage sub1 =receiverThread1.getData();
                        BufferedImage sub2 =receiverThread2.getData();
                        
                        BufferedImage[] subResult1={sub1,sub2};
                        BufferedImage result =image.mergeImage(subResult1);
                        image.send(result, dos);

                        socket.close();
                        server2.close();
                
                    }

                    else{

                        int[][] matrix1 =MatrixUtils.readMatrix(dis);
                        int[][] matrix2 =MatrixUtils.readMatrix(dis);
                        int[][][] partmatrix1=MatrixUtils.divideMatrix(matrix1, 2);
                        int[][][] partmatrix2=MatrixUtils.divideMatrix(matrix2, 2);
                        int[][]matrix1Part1=null,matrix1Part2=null,matrix2Part1=null,matrix2Part2=null;
                        if(choix==2){
                            matrix1Part1=partmatrix1[0];
                            matrix1Part2=partmatrix1[1];
                            matrix2Part1=matrix2;
                            matrix2Part2=matrix2;

                        }
                        else{
                            matrix1Part1=partmatrix1[0];
                            matrix1Part2=partmatrix1[1];
                            matrix2Part1=partmatrix2[0];
                            matrix2Part2=partmatrix2[1];

                        }
                

                        Thread senderThread1 = new Thread(new SenderThreadM(dosSlave1, matrix1Part1, matrix2Part1));
                        Thread senderThread2 = new Thread(new SenderThreadM(dosSlave2, matrix1Part2, matrix2Part2));                        
                        ReceiverThreadM receiverThread1 = new ReceiverThreadM(disSlave1);
                        ReceiverThreadM receiverThread2 = new ReceiverThreadM(disSlave2);
                        Thread thread1 = new Thread(receiverThread1);
                        Thread thread2 = new Thread(receiverThread2);

                        senderThread1.start();
                        senderThread2.start();
                        thread1.start();
                        thread2.start();

                        // Wait for threads to finish
                        senderThread1.join();
                        senderThread2.join();
                        thread1.join();
                        thread2.join();
                        int[][] sub1 =receiverThread1.getData();
                        int[][] sub2 =receiverThread2.getData();
                        int[][] result= MatrixUtils.mergeMatrices(sub1, sub2);
                        MatrixUtils.sendMatrix(dos,result);
                     }
                    

            
                // Fermer la socket
                
                System.out.println("Client disconnected.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    socketClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                }
    }
}