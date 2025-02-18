import java.io.DataOutputStream;
import java.io.IOException;


class SenderThreadM implements Runnable {
    private DataOutputStream dosSlave1;
    private int [][] subMatrix1;
    private int [][] subMatrix2;

    public SenderThreadM(DataOutputStream dosSlave1,int [][] subMatrix1,int [][] subMatrix2) {
        this.dosSlave1 = dosSlave1;
        this.subMatrix1 =subMatrix1;
        this.subMatrix2 =subMatrix2;
        
    }

    @Override
    public void run() {
        try {
            synchronized (dosSlave1) {
                
                MatrixUtils.sendMatrix(dosSlave1, subMatrix1);
                MatrixUtils.sendMatrix(dosSlave1, subMatrix2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
           
            e.printStackTrace();
        }
    }
}
