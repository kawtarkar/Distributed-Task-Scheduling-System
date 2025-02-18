import java.io.DataInputStream;
import java.io.IOException;
import java.awt.image.BufferedImage;

class ReceiverThread implements Runnable {
    private DataInputStream disSlave;
    private BufferedImage subResult;

    public ReceiverThread(DataInputStream disSlave) {
        this.disSlave = disSlave;
        this.subResult = null ;
    }

    @Override
    public void run() {
        try {
            synchronized (this) {
                subResult = image.Receive(disSlave);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized BufferedImage getData() {
        return subResult;
    }
}