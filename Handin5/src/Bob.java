import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Root on 08-09-2017.
 */
public class Bob {

    private Bloodtype b;
    private Utility util = new Utility();
    private boolean[] truthtable = new boolean[8];
    private SecureRandom sec = new SecureRandom();
    private BigInteger G;
    private BigInteger q;

    private BigInteger p;

    private BigInteger[] pks = new BigInteger[8];
    private String[] X;
    private String[] Y;

    public Bob(Bloodtype bloodtype){
        b = bloodtype;
        for(int i = 0; i<truthtable.length; i++){
            truthtable[i] = util.booleanFormula(bloodtype, Bloodtype.values()[i]);
        }
    }

    public void setP(BigInteger p) {
        this.p = p;
    }

    public void setG(BigInteger g) {
        G = g;
    }


    public void setQ(BigInteger q) {
        this.q = q;
    }

    public void setPks(BigInteger[] pks) {
        this.pks = pks;
    }

    public BigInteger[][] getEncryptedMessages(){
        // True = 416, False = 497
        BigInteger[][] result = new BigInteger[8][2];
        for(int i = 0; i<truthtable.length; i++){
            BigInteger r = new BigInteger(q.bitLength(), sec);
            while (r.compareTo(q) >= 0){
                //Needs to be [1...q-1]
                r = new BigInteger(q.bitLength(), sec);
            }
            if(truthtable[i]){
                result[i][0] = G.modPow(r, p);
                result[i][1] = pks[i].modPow(r, p).multiply(new BigInteger("173056"));
            } else {
                result[i][0] = G.modPow(r, p);
                result[i][1] = pks[i].modPow(r, p).multiply(new BigInteger("247009"));
            }
        }
        return result;
    }


    public void receiveX(String[] strings) {
        X = strings;
    }

    public void fakeOT(List<Wire> wires) {
        for (int i = 0; i<b.encoding.toCharArray().length; i++){
            if(Character.compare(b.encoding.toCharArray()[i], '1') == 0) {
                Y[i] = wires.get(i).k(1);
            } else
                Y[i] = wires.get(i).k(0);
        }
    }

    public void evaluateCurcuit(ArrayList<GarbledGate> gates) {
        
    }
}
