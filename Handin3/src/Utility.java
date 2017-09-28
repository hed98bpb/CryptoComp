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
            result = result & x;
        }
        return result;
    }

    /*
        the layer/wire structure follows a simple rule. A gate in layer n with
        output wire k, takes what's in layer n-1 at wires k and k+1. If unary a
        gate in layer n with output wire k, takes what's in layer n-1 at wire k.

        the circuit is drawn and implemented in layers. A picture of the circuit
        can be found in the folder.
    */

    public static Boolean runProtocol(Alice alice, Bob bob) {

        Dealer dealer = new Dealer();
        int layer = 0;

        // Layer 0
        initializeInputWires(alice, bob);

        // Layer 1
        layer++;
        for (int i = 0; i < 6; i += 2) {
            alice.not(layer, i);
            bob.not(layer, i);
        }
        for (int i = 1; i < 6; i += 2) {
            alice.identity(layer, i);
            bob.identity(layer,i);
        }

        // Layer 2
        layer++;
        for (int i = 0; i < 6; i += 2){
            and(alice, bob, dealer, layer, i);
        }

        // Layer 3
        layer++;
        removeSpaceInLayer2(alice, bob, layer);
        for (int i = 0; i < 3; i++) {
            alice.not(layer, i);
            bob.not(layer, i);
        }

        // Layer 4
        layer++;
        and(alice, bob, dealer, layer, 0);
        alice.identity(layer, 2, 1);
        bob.identity(layer, 2, 1);

        // Layer 5
        layer++;
        and(alice, bob, dealer, layer, 0);

        // output
        alice.calculateOutput(bob.sendOutput());

        if (alice.hasOutput()) return alice.output;
        else throw new Error("No output was computed");
    }

    public static void removeSpaceInLayer2(Alice alice, Bob bob, int layer) {
        alice.identity(layer, 2, 1);
        bob.identity(layer, 2, 1);
        alice.identity(layer, 4, 2);
        bob.identity(layer, 4, 2);
    }


    public static void and(Alice alice, Bob bob, Dealer dealer, int layer, int wire) {

        boolean[] sixtuplet;
        sixtuplet = dealer.generateuvwSixtuplet();

        alice.setTriple(sixtuplet[0], sixtuplet[2], sixtuplet[4]);
        bob.setTriple(sixtuplet[1], sixtuplet[3], sixtuplet[5]);

        alice.calculateDAndEShares(layer, wire);
        bob.calculateDAndEShares(layer, wire);

        alice.setDAndESharesFromBob(bob.sendDAndEShares());
        bob.setDAndESharesFromAlice(alice.sendDAndEShares());

        alice.calculateDAndEValues();
        bob.calculateDAndEValues();

        alice.calculateZValue(layer, wire);
        bob.calculateZValue(layer, wire);
    }

    public static void initializeInputWires(Alice alice, Bob bob){

        alice.initializeInputWires();
        bob.initializeInputWires();

        alice.setInputWiresFromBob(bob.sendSharesForInputWires());
        bob.setInputWiresfromAlice(alice.sendSharesForInputWires());

    }

    public Boolean runProtocol(Bloodtype doner, Bloodtype recipoient) {
        Alice alice = new Alice(6, 6, recipoient);
        Bob bob = new Bob(6, 6, doner);
        return runProtocol(alice, bob);
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
