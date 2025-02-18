
import java.io.*;
import java.net.*;
public class serveur3 {
    public static void main(String[] args) throws Exception {
        try {
            ServerSocket serveurSocket = new ServerSocket(6615);
            System.out.println("Serveur en attente de connexion");

            while (true) {
                Socket socketClient = serveurSocket.accept();
                System.out.println("Client connecté : " + socketClient);

                // Création d'un nouveau thread pour chaque client
                ClientHandler handler = new ClientHandler(socketClient);
                Thread thread = new Thread(handler);
                thread.start();
                
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



