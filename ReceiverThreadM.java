import java.io.DataInputStream;
import java.io.IOException;


class ReceiverThreadM implements Runnable {
    private DataInputStream disSlave;
    private int [][] subMatrixResult1;

    public ReceiverThreadM(DataInputStream disSlave) {
        this.disSlave = disSlave;
        this.subMatrixResult1 = null ;
    }

    @Override
    public void run() {
        try {
            synchronized (this) {
                subMatrixResult1=MatrixUtils.readMatrix(disSlave);
            
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public synchronized int[][] getData() {
        return subMatrixResult1 ;
    }
}
