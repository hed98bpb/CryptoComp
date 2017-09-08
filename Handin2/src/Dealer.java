import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * Created by Root on 08-09-2017.
 */
public class Dealer {
    private boolean[][] T;
    public boolean[][] Ma,Mb;
    public int n,r,s,arraySize;

    public Dealer(boolean[][] T, int n) {
        this.n = n;
        arraySize = (int) Math.pow(2,n);
        this.T = new boolean[arraySize][arraySize];

        for (int i = 0; i < T.length; i++) {
            for (int k = 0; k < T[i].length; k++) {
                if (T[i][k]) {
                    this.T[i][k] = true;
                } else
                    this.T[i][k] = false;
            }
        }

        // Step 1
        r = new Random(System.currentTimeMillis()+42).nextInt(arraySize);
        s = new Random(System.currentTimeMillis()+1337).nextInt(arraySize);

        Ma = new boolean[arraySize][arraySize];
        Mb = new boolean[arraySize][arraySize];

        // Step 2
        fillTableMB();

        // Step 3
        fillTableMA(T, arraySize);

        // Debug prints
        //System.out.println(Arrays.deepToString(Mb).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
        //System.out.println(" ");
        //System.out.println(Arrays.deepToString(Ma).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
    }

    private void fillTableMA(boolean[][] T, int arraySize) {
        for (int i = 0; i < Mb.length; i++) {
            for (int j = 0; j < Mb[i].length; j++) {
                if(T[Math.floorMod((i-r),(arraySize))][Math.floorMod((j-s),(arraySize))]) {
                    Ma[i][j] = (!Mb[i][j]);
                } else {
                    Ma[i][j] = (Mb[i][j]);
                }
            }
        }
    }

    private void fillTableMB() {
        Random rand  = new Random(System.currentTimeMillis()+666);
        for (int i = 0; i < Mb.length; i++) {
            for (int j = 0; j < Mb[i].length; j++) {
                if (rand.nextInt(2) % 2 == 0) Mb[i][j] = false;
                else Mb[i][j] = true;
            }
        }
    }

}

