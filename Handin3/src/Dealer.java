import java.security.SecureRandom;

/**
 * Created by Root on 08-09-2017.
 */
public class Dealer {

    public SecureRandom rand = new SecureRandom(String.valueOf(System.currentTimeMillis()).getBytes());

    public Dealer() {

    }

    public boolean getRand() {
        return rand.nextInt(2) == 0 ? false : true;
    }

    public boolean[] generateuvwSixtuplet() {

        boolean ua = getRand();
        boolean ub = getRand();
        boolean va = getRand();
        boolean vb = getRand();
        boolean wa = getRand();
        boolean wb = ((ua ^ ub) & (va ^ vb)) ^ wa;

        boolean[] triple = new boolean[6];

        triple[0] = ua;
        triple[1] = ub;
        triple[2] = va;
        triple[3] = vb;
        triple[4] = wa;
        triple[5] = wb;

        return triple;

    }
}

