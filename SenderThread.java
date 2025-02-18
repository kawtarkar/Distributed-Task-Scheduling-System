import java.io.DataOutputStream;
import java.io.IOException;
import java.awt.image.BufferedImage;

class SenderThread implements Runnable {
    private DataOutputStream dosSlave1;
    private BufferedImage subImage;
    private int [][] matrix;

    public SenderThread(DataOutputStream dosSlave1, BufferedImage subImage,int [][] matrix) {
        this.dosSlave1 = dosSlave1;
        this.subImage = subImage;
        this.matrix =matrix;


    }

    @Override
    public void run() {
        try {
            synchronized (dosSlave1) {
                
                image.send(subImage, dosSlave1);
                MatrixUtils.sendMatrix(dosSlave1, matrix);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}