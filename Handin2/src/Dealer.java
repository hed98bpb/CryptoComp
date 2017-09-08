import java.util.Arrays;
import java.util.Random;

/**
 * Created by Root on 08-09-2017.
 */
public class Dealer {
    private int[][] T;
    private int[][] Ma,Mb;
    private int r,s;

    public Dealer(boolean[][] T, int n) {
        this.T = new int[(int) Math.pow(2,n)][(int) Math.pow(2,n)];
        for (int i = 0; i < T.length; i++) {
            for (int k = 0; k < T[i].length; k++) {
                if (T[i][k]) {
                    this.T[i][k] = 1;
                } else
                    this.T[i][k] = 0;
            }
        }
        r = new Random(98765679304829L).nextInt((int) Math.pow(2,n));
        s = new Random(98765379304829L).nextInt((int) Math.pow(2,n));

        Mb = new int[(int) Math.pow(2,n)][(int) Math.pow(2,n)];
        Ma = new int[(int) Math.pow(2,n)][(int) Math.pow(2,n)];

        Random rand  = new Random(3426834987349258L);
        for (int i = 0; i < Mb.length; i++) {
            for (int k = 0; k < Mb[i].length; k++) {
                Mb[i][k] = rand.nextInt(2);
            }
        }

        for (int i = 0; i < Mb.length; i++) {
            for (int j = 0; j < Mb[i].length; j++) {
                boolean b = T[Math.floorMod((i-r),((int) Math.pow(2,n)))][Math.floorMod((j-s),((int) Math.pow(2,n)))];
                if(b) {
                    Ma[i][j] = Utility.XORgateforint(Mb[i][j], 1);
                } else {
                    Ma[i][j] = Utility.XORgateforint(Mb[i][j], 0);
                }
                }
            }

        System.out.println(Arrays.deepToString(Mb).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
        System.out.println(" ");
        System.out.println(Arrays.deepToString(Ma).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
    }
}

