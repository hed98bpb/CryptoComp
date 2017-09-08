/**
 * Created by Root on 08-09-2017.
 */
public class Alice {

    private boolean[][] MA;
    private Bloodtype bloodtype;
    private int r, n;
    public int u, v;
    public boolean z, zb;

    public Alice(boolean[][] MA, int r, int n, Bloodtype bloodtype){
        this.bloodtype = bloodtype;
        this.MA = MA;
        this.r = r;
        this.n = n;
    }

    public void calculateValue(){
        u = Math.floorMod(bloodtype.encodingToInt() + r, (int) Math.pow(2, n));
    }

    public boolean calculateOutput(){
        return z = MA[u][v] ^zb;
    }

}
