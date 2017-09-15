import java.security.SecureRandom;

/**
 * Created by Root on 08-09-2017.
 */
public class Alice {

    public int[] x;
    private Bloodtype b;
    public SecureRandom rand = new SecureRandom(new byte[20]);


    public Alice(int[] wires, Bloodtype bloodtype){
        b = bloodtype;
        x = wires;

        int x0b = getRand();
        x[0] = new Integer(b.encoding.substring(1,1)) ^ x0b;
        int x1b = getRand();
        x[1] = new Integer(b.encoding.substring(2,2)) ^ x1b;
        int x2b = getRand();
        x[2] = new Integer(b.encoding.substring(3,3)) ^ x2b;
    }


    public int getRand() {
        return rand.nextInt(2);
    }

}
