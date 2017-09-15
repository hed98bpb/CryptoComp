import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * Created by Root on 08-09-2017.
 */
public class Dealer {

    public SecureRandom rand = new SecureRandom(new byte[20]);

    public Dealer() {

        // Debug prints
        //System.out.println(Arrays.deepToString(Mb).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
        //System.out.println(" ");
        //System.out.println(Arrays.deepToString(Ma).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
    }

    public int getRand() {
        return rand.nextInt(2);
    }

}

