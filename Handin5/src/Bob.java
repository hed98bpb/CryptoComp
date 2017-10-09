import java.math.BigInteger;
import java.security.SecureRandom;

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


}
