
import java.io.*;


import java.net.ServerSocket;
import java.net.Socket;

public class slave2 {
    
            public static void main(String[] args) throws Exception {
                try {
                int port =11217;
                ServerSocket serverSocket = new ServerSocket(port);
                while (true) {
                Socket socket = serverSocket.accept();
                ClientHandlerSlave2 handler = new ClientHandlerSlave2(socket);
                Thread thread = new Thread(handler);
                thread.start();  
                }
                }catch (IOException e) {
                    e.printStackTrace();}
                }  
                
        
        }
   
        

