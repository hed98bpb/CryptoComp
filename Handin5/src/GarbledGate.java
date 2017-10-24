import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Root on 24-10-2017.
 */
public class GarbledGate {

    private ArrayList<String> Cs = new ArrayList<>();

    private MessageDigest hash;

    public GarbledGate(Wire inputWire, Wire outputWire) {
        //NOT gate
        try {
            hash = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        Cs.add(makeC(inputWire.k(0), outputWire.k(1)));
        Cs.add(makeC(inputWire.k(1), outputWire.k(0)));
        Collections.shuffle(Cs);
    }

    public ArrayList<String> getCs() {
        return Cs;
    }

    public GarbledGate(Wire left, Wire right, Wire outputWire) {
        //AND gate
        try {
            hash = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        Cs.add(makeC(left.k(0)+right.k(0), outputWire.k(0)));
        Cs.add(makeC(left.k(1)+right.k(0), outputWire.k(0)));
        Cs.add(makeC(left.k(0)+right.k(1), outputWire.k(0)));
        Cs.add(makeC(left.k(1)+right.k(1), outputWire.k(1)));
        Collections.shuffle(Cs);
    }

    private String makeC(String input, String output){
        String res;
        String inputHashedBinary = toBinary(hash.digest(input.getBytes(StandardCharsets.UTF_8)));
        BigInteger i = new BigInteger(inputHashedBinary, 2);
        BigInteger o = new BigInteger(pad(output), 2);
        BigInteger x = i.xor(o);
        res = x.toString(2);
        return res;
    }

    private String pad(String u) {
        String p = "00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        return u+p;
    }

    private String toBinary(byte[] b){
        String res = "";
        for (int i=0; i<b.length; i++){
            res = res +Integer.toBinaryString((b[i] & 0xFF) + 0x100).substring(1);
        }
        return res;
    }
}
