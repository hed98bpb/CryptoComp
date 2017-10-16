import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

public class Alice {

    private Bloodtype bloodtype;
    private Random randomGenerator;

    private int psize = 2000;
    private BigInteger p;

    private int n = 2000;
    private ArrayList<Integer> s = new ArrayList<Integer>();

    // qi's are only 128 bit for simplicity
    private int qisize = 128;
    private ArrayList<BigInteger> qis = new ArrayList<BigInteger>();

    private int risize = 60;
    private ArrayList<BigInteger> ris = new ArrayList<BigInteger>();


    private BigInteger[] m;

    public Alice(Bloodtype bloodtype) {

        randomGenerator = new Random();

        this.bloodtype = bloodtype;
        p = new BigInteger("FFFFFFFFFFFFFFFFC90FDAA22168C234C4C6628B80DC1CD1" +
                                "29024E088A67CC74020BBEA63B139B22514A08798E3404DD" +
                                "EF9519B3CD3A431B302B0A6DF25F14374FE1356D6D51C245" +
                                "E485B576625E7EC6F44C42E9A637ED6B0BFF5CB6F406B7ED" +
                                "EE386BFB5A899FA5AE9F24117C4B1FE649286651ECE45B3D" +
                                "C2007CB8A163BF0598DA48361C55D39A69163FA8FD24CF5F" +
                                "83655D23DCA3AD961C62F356208552BB9ED529077096966D" +
                                "670C354E4ABC9804F1746C08CA18217C32905E462E36CE3B" +
                                "E39E772C180E86039B2783A2EC07A28FB5C55DF06F4C52C9" +
                                "DE2BCBF6955817183995497CEA956AE515D2261898FA0510" +
                                "15728E5A8AACAA68FFFFFFFFFFFFFFFF", 16);
        for (int i = 0; i < n; i++) {
            qis.add(findSafePrime(qisize));
            ris.add(new BigInteger(risize, randomGenerator));
        }

    }

    private BigInteger enc(int message) {

        generateS();

        BigInteger yiSum = BigInteger.ZERO;
        BigInteger yi;

        for (Integer i : s) {
            yi = p.multiply(qis.get(i)).add(ris.get(i).add(new BigInteger("2")));
            yiSum = yiSum.add(yi);
        }

        BigInteger r = new BigInteger(risize, randomGenerator);
        BigInteger m = new BigInteger(message+"");

        BigInteger c = m.add(r.multiply(new BigInteger("2"))).add(yiSum);

        return c;
    }

    private void generateS() {
        for (int i = 0; i < n; i++) {
            if (randomGenerator.nextBoolean()) s.add(i);
        }
    }

    public BigInteger[] sendMessage() {

        return generateMessage();

    }

    private BigInteger[] generateMessage() {

        m = new BigInteger[3];

        for (int i = 0; i < 3; i++) {
            m[i] = enc(Character.getNumericValue(bloodtype.encoding.charAt(i)));
        }

        return m;
    }

    public boolean decryptResult(BigInteger c) {
        return c.mod(p).mod(new BigInteger("2")).equals(new BigInteger("1"));
    }

    public BigInteger findSafePrime(int bitsize) {
        // if bitsize = 1024 finding such a prime takes about 1 minute with and i7
        // or with 2048 bits about 11 minutes
        SecureRandom sec = new SecureRandom(SecureRandom.getSeed(256));
        BigInteger p = BigInteger.probablePrime(bitsize, sec);
        // check if (p-1)/2 is a prime, e.g p=2q+1
        while (!p.subtract(BigInteger.ONE).shiftRight(1).isProbablePrime(100)) {
            p = BigInteger.probablePrime(bitsize, sec);
        }
        return p;
    }
}
