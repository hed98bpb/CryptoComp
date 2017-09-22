import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by Root on 08-09-2017.
 */
public class Alice {

    private Bloodtype b;
    private Utility util = new Utility();
    private BigInteger[] pks = new BigInteger[8];
    private SecureRandom sec = new SecureRandom();
    private BigInteger sk;
    private BigInteger[][] EncryptedMessages = null;
    private BigInteger G;
    private BigInteger p;
    private BigInteger q;

    public Alice(Bloodtype bloodtype){
        b = bloodtype;
        SecureRandom sec = new SecureRandom();
        p = util.findSafePrime(256);
        q = p.subtract(BigInteger.ONE).shiftRight(1);
        G = util.getGenerator(p, sec);
        sk = q;
        while(sk.compareTo(q) >= 0){
            sk = new BigInteger(q.bitLength(), sec);
        }
    }
    public void setupPks(){
        //Fill arrays with OGen
        for(int i = 0; i<pks.length; i++){
            BigInteger k = p;
            while(k.compareTo(p) >= 0){
                k = new BigInteger(p.bitLength(), sec);
            }
            pks[i] = k.modPow(new BigInteger("2"), p);
        }
        //Overwrite insert real Gen on index corresponding to own bloodtype
        pks[b.encodingToInt()] = G.modPow(sk, p);
    }

    public void setEncryptedMessages(BigInteger[][] encryptedMessages) {
        EncryptedMessages = encryptedMessages;
    }

    public BigInteger[] getPks() {
        return pks;
    }

    public BigInteger getP() {
        return p;
    }

    public BigInteger getG() {
        return G;
    }

    public BigInteger getQ() {
        return q;
    }

    public boolean calculateOutput() {
        // if c2*c1^-sk == 416 aka. true
        if(EncryptedMessages[b.encodingToInt()][0].modPow(p.subtract(BigInteger.ONE).subtract(sk), p).multiply(EncryptedMessages[b.encodingToInt()][1]).mod(p).compareTo(new BigInteger("416")) == 0){
            return true;
        } else if(EncryptedMessages[b.encodingToInt()][0].modPow(p.subtract(BigInteger.ONE).subtract(sk), p).multiply(EncryptedMessages[b.encodingToInt()][1]).mod(p).compareTo(new BigInteger("497")) == 0){
            return false;
        }
        try {
            throw new Exception("Sum ting wong, invalid truthtable mapping");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
