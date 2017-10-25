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
    private String[] Y = new String[3];
    private String Z;

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
        System.out.println(wires.get(0)+"\n");
        System.out.println(wires.get(1)+"\n");
        System.out.println(wires.get(2)+"\n");
        System.out.println(wires.get(3)+"\n");
        System.out.println(wires.get(4)+"\n");
        System.out.println(wires.get(5)+"\n");
        ArrayList<Wire> temp = new ArrayList<>();
        temp.add(wires.get(1));
        temp.add(wires.get(3));
        temp.add(wires.get(5));
        for (int i = 0; i<b.encoding.toCharArray().length; i++){
            if(Character.compare(b.encoding.toCharArray()[i], '1') == 0) {
                Y[i] = temp.get(i).k(1);
            } else
                Y[i] = temp.get(i).k(0);
        }
    }

    public void evaluateCircuit(ArrayList<GarbledGate> gates) {
        System.out.println("X:\n"+X[0]+"\n"+X[1]+"\n"+X[2]);
        System.out.println("Y:\n"+Y[0]+"\n"+Y[1]+"\n"+Y[2]);
        //Evaluate NOT-gates
        String w6 = gates.get(0).evaluate(X[0]);
        String w8 = gates.get(1).evaluate(X[1]);
        String w10 = gates.get(2).evaluate(X[2]);

        //Evaluate AND-gates
        String w12 = gates.get(3).evaluate(w6+Y[0]);
        String w13 = gates.get(4).evaluate(w8+Y[1]);
        String w14 = gates.get(5).evaluate(w10+Y[2]);

        //Evaluate NOT-gates
        String w15 = gates.get(6).evaluate(w12);
        String w16 = gates.get(7).evaluate(w13);
        String w17 = gates.get(8).evaluate(w14);

        //Evaluate AND-gate and NOT-gate
        String w18 = gates.get(9).evaluate(w15+w16);
        String w19 = gates.get(10).evaluate(w17);

        //Evaluate final AND-gate
        String w20 = gates.get(11).evaluate(w18+w19);

        //Set Z to the output of last gate
        Z=w20;
    }

    public String sendZ() {
        return Z;
    }
}
