import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Root on 03-09-2017.
 */

public class Bloodtypetest {

    private Utility util = new Utility();
    private boolean slave = true;

    private boolean test(Bloodtype donor, Bloodtype recipient){
        if (slave == false) {
            return util.runProtocol(donor, recipient);
        } else {
            return testSlave(donor, recipient);
        }
    }

    private boolean testSlave(Bloodtype donor, Bloodtype recipient){
        //Test each part of the logic and see if the truth table matches
        return andTestpart1(donor,recipient) & andTestpart2(donor,recipient) & andTestpart3(donor,recipient);
    }

    private boolean andTestpart1(Bloodtype donor, Bloodtype recipient){

        Alice alice = new Alice(4,6, recipient);
        Bob bob = new Bob(4, 6, donor);
        Dealer dealer = new Dealer();

        Utility util = new Utility();

        util.initializeInputWires(alice, bob);
        alice.not(1,0);
        alice.identity(1,1);
        bob.not(1,0);
        bob.identity(1,1);
        util.and(alice, bob, dealer, 2,0);
        alice.not(3,0);
        bob.not(3,0);

        alice.calculateOutput(bob.sendOutput());

        return alice.output;
    }

    @Test
    public void checkAndGatepart1() {
        //Jeg ændrede andTesten til at kigge på første udtryk NOT( AND(NOT(rA),dA) )
        assertEquals(false, andTestpart1(Bloodtype.ABPOSITIVE, Bloodtype.BPOSITIVE)); //111->011
        assertEquals(true, andTestpart1(Bloodtype.ABPOSITIVE, Bloodtype.ABPOSITIVE)); //111->111
        assertEquals(true, andTestpart1(Bloodtype.BPOSITIVE, Bloodtype.ABPOSITIVE)); //011->111
        assertEquals(true, andTestpart1(Bloodtype.ZERONEGATIVE, Bloodtype.ZERONEGATIVE)); //000->000
    }

    private boolean andTestpart2(Bloodtype donor, Bloodtype recipient){

        Alice alice = new Alice(5,6, recipient);
        Bob bob = new Bob(5, 6, donor);
        Dealer dealer = new Dealer();

        Utility util = new Utility();

        util.initializeInputWires(alice, bob);
        alice.not(1,2);
        alice.identity(1,3);
        bob.not(1,2);
        bob.identity(1,3);
        util.and(alice, bob, dealer, 2,2);
        alice.not(3,2);
        bob.not(3,2);

        //put output into first position
        alice.identity(4,2,0);
        bob.identity(4,2,0);

        alice.calculateOutput(bob.sendOutput());

        return alice.output;
    }

    @Test
    public void checkAndGatepart2() {
        //NOT( AND(NOT(rB),db) )
        assertEquals(true, andTestpart2(Bloodtype.APOSITIVE, Bloodtype.BPOSITIVE)); //101 -> 011
        assertEquals(false, andTestpart2(Bloodtype.ABPOSITIVE, Bloodtype.APOSITIVE)); //111 -> 101
        assertEquals(true, andTestpart2(Bloodtype.BPOSITIVE, Bloodtype.ABPOSITIVE)); //011 -> 111
        assertEquals(true, andTestpart2(Bloodtype.ZERONEGATIVE, Bloodtype.ZERONEGATIVE)); //000 -> 000
    }

    private boolean andTestpart3(Bloodtype donor, Bloodtype recipient){

        Alice alice = new Alice(5,6, recipient);
        Bob bob = new Bob(5, 6, donor);
        Dealer dealer = new Dealer();

        Utility util = new Utility();

        util.initializeInputWires(alice, bob);
        alice.not(1,4);
        alice.identity(1,5);
        bob.not(1,4);
        bob.identity(1,5);
        util.and(alice, bob, dealer, 2,4);
        alice.not(3,4);
        bob.not(3,4);

        //put output into first position
        alice.identity(4,4,0);
        bob.identity(4,4,0);

        alice.calculateOutput(bob.sendOutput());

        return alice.output;
    }

    @Test
    public void checkAndGatepart3() {
        //NOT( AND(NOT(rR),dR) )
        assertEquals(true, andTestpart3(Bloodtype.APOSITIVE, Bloodtype.BPOSITIVE)); //101 -> 011
        assertEquals(true, andTestpart3(Bloodtype.ABNEGATIVE, Bloodtype.APOSITIVE)); //110 -> 101
        assertEquals(false, andTestpart3(Bloodtype.BPOSITIVE, Bloodtype.ABNEGATIVE)); //011 -> 110
        assertEquals(true, andTestpart3(Bloodtype.ZERONEGATIVE, Bloodtype.ZERONEGATIVE)); //000 -> 000
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
