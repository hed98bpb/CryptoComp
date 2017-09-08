/**
 * Created by Root on 08-09-2017.
 */
public class Bob {

    private boolean[][] MB;
    private int s, n;
    private Bloodtype bloodtype;
    public int u, v;
    public boolean zb;

    public Bob(boolean[][] MB, int s, int n, Bloodtype bloodtype){
        this.bloodtype = bloodtype;
        this.MB = MB;
        this.s = s;
        this.n = n;
    }

    public void calculateValue(){
        v = Math.floorMod(bloodtype.encodingToInt() + s, (int) Math.pow(2, n));
        zb = MB[u][v];
    }

}
