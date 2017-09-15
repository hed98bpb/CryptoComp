import java.security.SecureRandom;

/**
 * Created by Root on 08-09-2017.
 */
public class Bob {

    public int[] y;
    private Bloodtype b;
    public SecureRandom rand = new SecureRandom(new byte[20]);


    public Bob(int[] wires, Bloodtype bloodtype){
        b = bloodtype;
        y = wires;

        int x0b = getRand();
        y[3] = new Integer(b.encoding.substring(1,1)) ^ x0b;
        int x1b = getRand();
        y[4] = new Integer(b.encoding.substring(2,2)) ^ x1b;
        int x2b = getRand();
        y[5] = new Integer(b.encoding.substring(3,3)) ^ x2b;
    }


    public int getRand() {
        return rand.nextInt(2);
    }

}
