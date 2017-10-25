import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;

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

    private GarbledCircuit gb;

    private String[] X = new String[3];
    private String Z;

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

    public boolean calculateOutput2() {
        // if c2*c1^-sk == 416 aka. true
        if(EncryptedMessages[b.encodingToInt()][0].modPow(p.subtract(BigInteger.ONE).subtract(sk), p).multiply(EncryptedMessages[b.encodingToInt()][1]).mod(p).compareTo(new BigInteger("173056")) == 0){
            return true;
        } else if(EncryptedMessages[b.encodingToInt()][0].modPow(p.subtract(BigInteger.ONE).subtract(sk), p).multiply(EncryptedMessages[b.encodingToInt()][1]).mod(p).compareTo(new BigInteger("247009")) == 0){
            return false;
        }
        try {
            throw new Exception("Sum ting wong, invalid truthtable mapping");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean calculateOutput() throws Exception {
        System.out.println("Z:\n"+Z+"\n");
        System.out.println(gb.d.k(0));
        System.out.println(gb.d.k(1));
        if(Z.equals(gb.d.k(1))){
            return true;
        }
        if(Z.equals(gb.d.k(0))){
            return false;
        }
        throw new Exception("We dun goofed");
    }

    public void makeGarbledCurcuit() {
        gb = new GarbledCircuit();
    }

    public GarbledCircuit getGb() {
        return gb;
    }

    public String[] makeEncodingX() {
        ArrayList<Wire> temp = new ArrayList<>();
        temp.add(gb.enc.get(0));
        temp.add(gb.enc.get(2));
        temp.add(gb.enc.get(4));
        for (int i = 0; i<b.encoding.toCharArray().length; i++){
            if(Character.compare(b.encoding.toCharArray()[i], '1') == 0) {
                X[i] = temp.get(i).k(1);
            } else
                X[i] = temp.get(i).k(0);
        }
        return X;
    }

    public void receiveZ(String s) {
        Z = s;
    }
}
