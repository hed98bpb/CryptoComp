import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Root on 03-09-2017.
 */

public class Bloodtypetest {

    private Utility util = new Utility();

    private boolean test(Bloodtype donor, Bloodtype recipient){
        return util.runProtocol(donor, recipient);
    }

    @Test
    public void checkZeros(){
        GarbledCircuit gb = new GarbledCircuit();
        System.out.println(gb.getGates().get(0).evaluate(gb.wires.get(0).k(0)));

    }

    @Test
    public void checkEncoding(){
        Alice alice = new Alice(Bloodtype.APOSITIVE);
        alice.makeGarbledCurcuit();
        System.out.println(alice.getGb().enc.get(1));
        System.out.println(alice.makeEncodingX()[1]);
    }

    @Test
    public void checkPrime(){
        BigInteger p = util.findSafePrime(256); //128 bit is fine for testing, NIST recommends 2048 bit p in practice
        BigInteger q = p.subtract(BigInteger.ONE).shiftRight(1); // check if (p-1)/2 is a prime, e.g p=2q+1
        assertTrue(q.isProbablePrime(1000)); //certainty of 14 is required for 128 bit primes
        System.out.println("p: "+p.toString()+" \n length: "+p.bitLength());
        System.out.println("q: "+q.toString()+" \n length: "+q.bitLength());
        BigInteger g = util.getGenerator(p, new SecureRandom());
        System.out.println("G: "+g.toString()+" \n length: "+g.bitLength());
    }


    @Test
    public void RecieverisOneg(){
        assertEquals(true, test(Bloodtype.ZERONEGATIVE,Bloodtype.ZERONEGATIVE));
        assertEquals(false, test(Bloodtype.ZEROPOSITIVE,Bloodtype.ZERONEGATIVE));
        assertEquals(false, test(Bloodtype.ANEGATIVE,Bloodtype.ZERONEGATIVE));
        assertEquals(false, test(Bloodtype.APOSITIVE,Bloodtype.ZERONEGATIVE));
        assertEquals(false, test(Bloodtype.BNEGATIVE,Bloodtype.ZERONEGATIVE));
        assertEquals(false, test(Bloodtype.BPOSITIVE,Bloodtype.ZERONEGATIVE));
        assertEquals(false, test(Bloodtype.ABNEGATIVE,Bloodtype.ZERONEGATIVE));
        assertEquals(false, test(Bloodtype.ABPOSITIVE,Bloodtype.ZERONEGATIVE));
    }
    @Test
    public void RecieverisOpos(){
        assertEquals(true, test(Bloodtype.ZERONEGATIVE,Bloodtype.ZEROPOSITIVE));
        assertEquals(true, test(Bloodtype.ZEROPOSITIVE,Bloodtype.ZEROPOSITIVE));
        assertEquals(false, test(Bloodtype.ANEGATIVE,Bloodtype.ZEROPOSITIVE));
        assertEquals(false, test(Bloodtype.APOSITIVE,Bloodtype.ZEROPOSITIVE));
        assertEquals(false, test(Bloodtype.BNEGATIVE,Bloodtype.ZEROPOSITIVE));
        assertEquals(false, test(Bloodtype.BPOSITIVE,Bloodtype.ZEROPOSITIVE));
        assertEquals(false, test(Bloodtype.ABNEGATIVE,Bloodtype.ZEROPOSITIVE));
        assertEquals(false, test(Bloodtype.ABPOSITIVE,Bloodtype.ZEROPOSITIVE));
    }
    @Test
    public void RecieverisAneg(){
        assertEquals(true, test(Bloodtype.ZERONEGATIVE,Bloodtype.ANEGATIVE));
        assertEquals(false, test(Bloodtype.ZEROPOSITIVE,Bloodtype.ANEGATIVE));
        assertEquals(true, test(Bloodtype.ANEGATIVE,Bloodtype.ANEGATIVE));
        assertEquals(false, test(Bloodtype.APOSITIVE,Bloodtype.ANEGATIVE));
        assertEquals(false, test(Bloodtype.BNEGATIVE,Bloodtype.ANEGATIVE));
        assertEquals(false, test(Bloodtype.BPOSITIVE,Bloodtype.ANEGATIVE));
        assertEquals(false, test(Bloodtype.ABNEGATIVE,Bloodtype.ANEGATIVE));
        assertEquals(false, test(Bloodtype.ABPOSITIVE,Bloodtype.ANEGATIVE));
    }
    @Test
    public void RecieverisApos(){
        assertEquals(true, test(Bloodtype.ZERONEGATIVE,Bloodtype.APOSITIVE));
        assertEquals(true, test(Bloodtype.ZEROPOSITIVE,Bloodtype.APOSITIVE));
        assertEquals(true, test(Bloodtype.ANEGATIVE,Bloodtype.APOSITIVE));
        assertEquals(true, test(Bloodtype.APOSITIVE,Bloodtype.APOSITIVE));
        assertEquals(false, test(Bloodtype.BNEGATIVE,Bloodtype.APOSITIVE));
        assertEquals(false, test(Bloodtype.BPOSITIVE,Bloodtype.APOSITIVE));
        assertEquals(false, test(Bloodtype.ABNEGATIVE,Bloodtype.APOSITIVE));
        assertEquals(false, test(Bloodtype.ABPOSITIVE,Bloodtype.APOSITIVE));
    }

    @Test
    public void RecieverisBneg(){
        assertEquals(true, test(Bloodtype.ZERONEGATIVE,Bloodtype.BNEGATIVE));
        assertEquals(false, test(Bloodtype.ZEROPOSITIVE,Bloodtype.BNEGATIVE));
        assertEquals(false, test(Bloodtype.ANEGATIVE,Bloodtype.BNEGATIVE));
        assertEquals(false, test(Bloodtype.APOSITIVE,Bloodtype.BNEGATIVE));
        assertEquals(true, test(Bloodtype.BNEGATIVE,Bloodtype.BNEGATIVE));
        assertEquals(false, test(Bloodtype.BPOSITIVE,Bloodtype.BNEGATIVE));
        assertEquals(false, test(Bloodtype.ABNEGATIVE,Bloodtype.BNEGATIVE));
        assertEquals(false, test(Bloodtype.ABPOSITIVE,Bloodtype.BNEGATIVE));
    }
    @Test
    public void RecieverisBpos(){
        assertEquals(true, test(Bloodtype.ZERONEGATIVE,Bloodtype.BPOSITIVE));
        assertEquals(true, test(Bloodtype.ZEROPOSITIVE,Bloodtype.BPOSITIVE));
        assertEquals(false, test(Bloodtype.ANEGATIVE,Bloodtype.BPOSITIVE));
        assertEquals(false, test(Bloodtype.APOSITIVE,Bloodtype.BPOSITIVE));
        assertEquals(true, test(Bloodtype.BNEGATIVE,Bloodtype.BPOSITIVE));
        assertEquals(true, test(Bloodtype.BPOSITIVE,Bloodtype.BPOSITIVE));
        assertEquals(false, test(Bloodtype.ABNEGATIVE,Bloodtype.BPOSITIVE));
        assertEquals(false, test(Bloodtype.ABPOSITIVE,Bloodtype.BPOSITIVE));
    }
    @Test
    public void RecieverisABneg(){
        assertEquals(true, test(Bloodtype.ZERONEGATIVE,Bloodtype.ABNEGATIVE));
        assertEquals(false, test(Bloodtype.ZEROPOSITIVE,Bloodtype.ABNEGATIVE));
        assertEquals(true, test(Bloodtype.ANEGATIVE,Bloodtype.ABNEGATIVE));
        assertEquals(false, test(Bloodtype.APOSITIVE,Bloodtype.ABNEGATIVE));
        assertEquals(true, test(Bloodtype.BNEGATIVE,Bloodtype.ABNEGATIVE));
        assertEquals(false, test(Bloodtype.BPOSITIVE,Bloodtype.ABNEGATIVE));
        assertEquals(true, test(Bloodtype.ABNEGATIVE,Bloodtype.ABNEGATIVE));
        assertEquals(false, test(Bloodtype.ABPOSITIVE,Bloodtype.ABNEGATIVE));
    }
    @Test
    public void RecieverisABpos(){
        assertEquals(true, test(Bloodtype.ZERONEGATIVE,Bloodtype.ABPOSITIVE));
        assertEquals(true, test(Bloodtype.ZEROPOSITIVE,Bloodtype.ABPOSITIVE));
        assertEquals(true, test(Bloodtype.ANEGATIVE,Bloodtype.ABPOSITIVE));
        assertEquals(true, test(Bloodtype.APOSITIVE,Bloodtype.ABPOSITIVE));
        assertEquals(true, test(Bloodtype.BNEGATIVE,Bloodtype.ABPOSITIVE));
        assertEquals(true, test(Bloodtype.BPOSITIVE,Bloodtype.ABPOSITIVE));
        assertEquals(true, test(Bloodtype.ABNEGATIVE,Bloodtype.ABPOSITIVE));
        assertEquals(true, test(Bloodtype.ABPOSITIVE,Bloodtype.ABPOSITIVE));
    }

}
