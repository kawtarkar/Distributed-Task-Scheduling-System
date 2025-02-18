
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class slave1 {
    
            public static void main(String[] args) throws Exception {
                try {
                int port =11326;
                ServerSocket serverSocket = new ServerSocket(port);
                while (true) {
                Socket socket = serverSocket.accept();
                ClientHandlerSlave1 handler = new ClientHandlerSlave1(socket);
                Thread thread = new Thread(handler);
                thread.start();
                
               
                
            }
                }catch (IOException e) {
                    e.printStackTrace();}
                }
        }
   
        

