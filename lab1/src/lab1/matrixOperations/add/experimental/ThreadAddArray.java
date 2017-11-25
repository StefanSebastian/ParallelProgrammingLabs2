package lab1.matrixOperations.add.experimental;

/**
 * Created by Sebi on 06-Oct-17.
 */
public class ThreadAddArray implements Runnable {

    private double[] a;
    private double[] b;
    private double[] res;
    private int start;
    private int end;

    public ThreadAddArray(double[] a, double[] b, double[] res, int start, int end){
        this.a = a;
        this.b = b;
        this.res = res;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++){
            res[i] = a[i] + b[i];
        }
    }
}
