import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Root on 03-09-2017.
 */
public class Bloodtypetest {

    private Utility util = new Utility();

    @Test
    public void RecieverisOneg(){
        assertEquals(true, util.booleanFormula(Bloodtype.ZERONEGATIVE,Bloodtype.ZERONEGATIVE));
        assertEquals(false, util.booleanFormula(Bloodtype.ZEROPOSITIVE,Bloodtype.ZERONEGATIVE));
        assertEquals(false, util.booleanFormula(Bloodtype.ANEGATIVE,Bloodtype.ZERONEGATIVE));
        assertEquals(false, util.booleanFormula(Bloodtype.APOSITIVE,Bloodtype.ZERONEGATIVE));
        assertEquals(false, util.booleanFormula(Bloodtype.BNEGATIVE,Bloodtype.ZERONEGATIVE));
        assertEquals(false, util.booleanFormula(Bloodtype.BPOSITIVE,Bloodtype.ZERONEGATIVE));
        assertEquals(false, util.booleanFormula(Bloodtype.ABNEGATIVE,Bloodtype.ZERONEGATIVE));
        assertEquals(false, util.booleanFormula(Bloodtype.ABPOSITIVE,Bloodtype.ZERONEGATIVE));
    }
    @Test
    public void RecieverisOpos(){
        assertEquals(true, util.booleanFormula(Bloodtype.ZERONEGATIVE,Bloodtype.ZEROPOSITIVE));
        assertEquals(true, util.booleanFormula(Bloodtype.ZEROPOSITIVE,Bloodtype.ZEROPOSITIVE));
        assertEquals(false, util.booleanFormula(Bloodtype.ANEGATIVE,Bloodtype.ZEROPOSITIVE));
        assertEquals(false, util.booleanFormula(Bloodtype.APOSITIVE,Bloodtype.ZEROPOSITIVE));
        assertEquals(false, util.booleanFormula(Bloodtype.BNEGATIVE,Bloodtype.ZEROPOSITIVE));
        assertEquals(false, util.booleanFormula(Bloodtype.BPOSITIVE,Bloodtype.ZEROPOSITIVE));
        assertEquals(false, util.booleanFormula(Bloodtype.ABNEGATIVE,Bloodtype.ZEROPOSITIVE));
        assertEquals(false, util.booleanFormula(Bloodtype.ABPOSITIVE,Bloodtype.ZEROPOSITIVE));
    }
    @Test
    public void RecieverisAneg(){
        assertEquals(true, util.booleanFormula(Bloodtype.ZERONEGATIVE,Bloodtype.ANEGATIVE));
        assertEquals(false, util.booleanFormula(Bloodtype.ZEROPOSITIVE,Bloodtype.ANEGATIVE));
        assertEquals(true, util.booleanFormula(Bloodtype.ANEGATIVE,Bloodtype.ANEGATIVE));
        assertEquals(false, util.booleanFormula(Bloodtype.APOSITIVE,Bloodtype.ANEGATIVE));
        assertEquals(false, util.booleanFormula(Bloodtype.BNEGATIVE,Bloodtype.ANEGATIVE));
        assertEquals(false, util.booleanFormula(Bloodtype.BPOSITIVE,Bloodtype.ANEGATIVE));
        assertEquals(false, util.booleanFormula(Bloodtype.ABNEGATIVE,Bloodtype.ANEGATIVE));
        assertEquals(false, util.booleanFormula(Bloodtype.ABPOSITIVE,Bloodtype.ANEGATIVE));
    }
    @Test
    public void RecieverisApos(){
        assertEquals(true, util.booleanFormula(Bloodtype.ZERONEGATIVE,Bloodtype.APOSITIVE));
        assertEquals(true, util.booleanFormula(Bloodtype.ZEROPOSITIVE,Bloodtype.APOSITIVE));
        assertEquals(true, util.booleanFormula(Bloodtype.ANEGATIVE,Bloodtype.APOSITIVE));
        assertEquals(true, util.booleanFormula(Bloodtype.APOSITIVE,Bloodtype.APOSITIVE));
        assertEquals(false, util.booleanFormula(Bloodtype.BNEGATIVE,Bloodtype.APOSITIVE));
        assertEquals(false, util.booleanFormula(Bloodtype.BPOSITIVE,Bloodtype.APOSITIVE));
        assertEquals(false, util.booleanFormula(Bloodtype.ABNEGATIVE,Bloodtype.APOSITIVE));
        assertEquals(false, util.booleanFormula(Bloodtype.ABPOSITIVE,Bloodtype.APOSITIVE));
    }

    @Test
    public void RecieverisBneg(){
        assertEquals(true, util.booleanFormula(Bloodtype.ZERONEGATIVE,Bloodtype.BNEGATIVE));
        assertEquals(false, util.booleanFormula(Bloodtype.ZEROPOSITIVE,Bloodtype.BNEGATIVE));
        assertEquals(false, util.booleanFormula(Bloodtype.ANEGATIVE,Bloodtype.BNEGATIVE));
        assertEquals(false, util.booleanFormula(Bloodtype.APOSITIVE,Bloodtype.BNEGATIVE));
        assertEquals(true, util.booleanFormula(Bloodtype.BNEGATIVE,Bloodtype.BNEGATIVE));
        assertEquals(false, util.booleanFormula(Bloodtype.BPOSITIVE,Bloodtype.BNEGATIVE));
        assertEquals(false, util.booleanFormula(Bloodtype.ABNEGATIVE,Bloodtype.BNEGATIVE));
        assertEquals(false, util.booleanFormula(Bloodtype.ABPOSITIVE,Bloodtype.BNEGATIVE));
    }
    @Test
    public void RecieverisBpos(){
        assertEquals(true, util.booleanFormula(Bloodtype.ZERONEGATIVE,Bloodtype.BPOSITIVE));
        assertEquals(true, util.booleanFormula(Bloodtype.ZEROPOSITIVE,Bloodtype.BPOSITIVE));
        assertEquals(false, util.booleanFormula(Bloodtype.ANEGATIVE,Bloodtype.BPOSITIVE));
        assertEquals(false, util.booleanFormula(Bloodtype.APOSITIVE,Bloodtype.BPOSITIVE));
        assertEquals(true, util.booleanFormula(Bloodtype.BNEGATIVE,Bloodtype.BPOSITIVE));
        assertEquals(true, util.booleanFormula(Bloodtype.BPOSITIVE,Bloodtype.BPOSITIVE));
        assertEquals(false, util.booleanFormula(Bloodtype.ABNEGATIVE,Bloodtype.BPOSITIVE));
        assertEquals(false, util.booleanFormula(Bloodtype.ABPOSITIVE,Bloodtype.BPOSITIVE));
    }
    @Test
    public void RecieverisABneg(){
        assertEquals(true, util.booleanFormula(Bloodtype.ZERONEGATIVE,Bloodtype.ABNEGATIVE));
        assertEquals(false, util.booleanFormula(Bloodtype.ZEROPOSITIVE,Bloodtype.ABNEGATIVE));
        assertEquals(true, util.booleanFormula(Bloodtype.ANEGATIVE,Bloodtype.ABNEGATIVE));
        assertEquals(false, util.booleanFormula(Bloodtype.APOSITIVE,Bloodtype.ABNEGATIVE));
        assertEquals(true, util.booleanFormula(Bloodtype.BNEGATIVE,Bloodtype.ABNEGATIVE));
        assertEquals(false, util.booleanFormula(Bloodtype.BPOSITIVE,Bloodtype.ABNEGATIVE));
        assertEquals(true, util.booleanFormula(Bloodtype.ABNEGATIVE,Bloodtype.ABNEGATIVE));
        assertEquals(false, util.booleanFormula(Bloodtype.ABPOSITIVE,Bloodtype.ABNEGATIVE));
    }
    @Test
    public void RecieverisABpos(){
        assertEquals(true, util.booleanFormula(Bloodtype.ZERONEGATIVE,Bloodtype.ABPOSITIVE));
        assertEquals(true, util.booleanFormula(Bloodtype.ZEROPOSITIVE,Bloodtype.ABPOSITIVE));
        assertEquals(true, util.booleanFormula(Bloodtype.ANEGATIVE,Bloodtype.ABPOSITIVE));
        assertEquals(true, util.booleanFormula(Bloodtype.APOSITIVE,Bloodtype.ABPOSITIVE));
        assertEquals(true, util.booleanFormula(Bloodtype.BNEGATIVE,Bloodtype.ABPOSITIVE));
        assertEquals(true, util.booleanFormula(Bloodtype.BPOSITIVE,Bloodtype.ABPOSITIVE));
        assertEquals(true, util.booleanFormula(Bloodtype.ABNEGATIVE,Bloodtype.ABPOSITIVE));
        assertEquals(true, util.booleanFormula(Bloodtype.ABPOSITIVE,Bloodtype.ABPOSITIVE));
    }

}