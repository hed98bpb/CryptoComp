import java.util.Arrays;
import java.util.List;

public class Utility {

    public Utility(){}

    public boolean tableLookup(Bloodtype donor, Bloodtype recipient){
        return listCompatibility(donor).contains(recipient);
    }

    public boolean booleanFormula(Bloodtype donor, Bloodtype recipient){
        LayoutConfig lc = new LayoutConfig(donor, recipient);
        return booleanCircuit(lc);
    }

    private boolean booleanCircuit(LayoutConfig lc) {

        return ANDgate(ORgate(lc.cbar,lc.f),ORgate(lc.bbar,lc.e),ORgate(lc.abar,lc.d));

    }


    public Testpair InputToBloodtypeEnums(String arg0, String arg1){
        return new Testpair(convertToBloodtype(arg0), convertToBloodtype(arg1));
    }

    public Bloodtype convertToBloodtype(String arg){
        if (arg.equals(Bloodtype.ZERONEGATIVE.name)) return Bloodtype.ZERONEGATIVE;
        if (arg.equals(Bloodtype.ZEROPOSITIVE.name)) return Bloodtype.ZEROPOSITIVE;
        if (arg.equals(Bloodtype.ANEGATIVE.name)) return Bloodtype.ANEGATIVE;
        if (arg.equals(Bloodtype.APOSITIVE.name)) return Bloodtype.APOSITIVE;
        if (arg.equals(Bloodtype.BNEGATIVE.name)) return Bloodtype.BNEGATIVE;
        if (arg.equals(Bloodtype.BPOSITIVE.name)) return Bloodtype.BPOSITIVE;
        if (arg.equals(Bloodtype.ABNEGATIVE.name)) return Bloodtype.ABNEGATIVE;
        return Bloodtype.ABPOSITIVE;
    }

    public List<Bloodtype> listCompatibility(Bloodtype type){
        if (type.equals(Bloodtype.ZERONEGATIVE)) return Arrays.asList(Bloodtype.ZEROPOSITIVE, Bloodtype.ZEROPOSITIVE, Bloodtype.ABNEGATIVE, Bloodtype.APOSITIVE, Bloodtype.BNEGATIVE, Bloodtype.BPOSITIVE, Bloodtype.ABNEGATIVE, Bloodtype.ABPOSITIVE);
        if (type.equals(Bloodtype.ZEROPOSITIVE)) return Arrays.asList(Bloodtype.ZEROPOSITIVE, Bloodtype.APOSITIVE, Bloodtype.BPOSITIVE, Bloodtype.ABPOSITIVE);
        if (type.equals(Bloodtype.ANEGATIVE)) return Arrays.asList(Bloodtype.ABNEGATIVE, Bloodtype.APOSITIVE, Bloodtype.ABNEGATIVE, Bloodtype.ABPOSITIVE);
        if (type.equals(Bloodtype.APOSITIVE)) return Arrays.asList(Bloodtype.APOSITIVE, Bloodtype.ABPOSITIVE);
        if (type.equals(Bloodtype.BNEGATIVE)) return Arrays.asList(Bloodtype.BNEGATIVE, Bloodtype.BPOSITIVE, Bloodtype.ABNEGATIVE, Bloodtype.ABPOSITIVE);
        if (type.equals(Bloodtype.BPOSITIVE)) return Arrays.asList(Bloodtype.BPOSITIVE, Bloodtype.ABPOSITIVE);
        if (type.equals(Bloodtype.ABNEGATIVE)) return Arrays.asList(Bloodtype.ABNEGATIVE, Bloodtype.ABPOSITIVE);
        return Arrays.asList(Bloodtype.ABPOSITIVE);
    }

    public static boolean ORgate(boolean... xs){
        boolean result = false;
        for (boolean x : xs) {
            result = result || x;
        }
        return result;
    }

    public static boolean ANDgate(boolean... xs){
        boolean result = true;
        for (boolean x : xs) {
            result = result && x;
        }
        return result;
    }
}


final class Testpair {
    private final Bloodtype donor;
    private final Bloodtype recipient;

    public Testpair(Bloodtype donor, Bloodtype recipient) {
        this.donor = donor;
        this.recipient = recipient;
    }

    public Bloodtype getDonor() {
        return donor;
    }

    public Bloodtype getRecipient() {
        return recipient;
    }
}
