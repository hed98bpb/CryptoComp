import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utility {

    public Utility() {
    }


    public Boolean runProtocol(Alice alice, Bob bob) {
        alice.makeGarbledCurcuit();
        bob.receiveX(alice.makeEncodingX());
        bob.fakeOT(alice.getGb().enc); //TODO: make real OT
        bob.evaluateCircuit(alice.getGb().getGates());
        alice.receiveZ(bob.sendZ());
        try {
            return alice.calculateOutput();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Wire> generateWireKeys(ArrayList<Wire> wires) {
        SecureRandom rand = new SecureRandom(SecureRandom.getSeed(256));
        for(int j = 0; j < GarbledCircuit._NoOfWires_; j++){
            String k0 = "";
            for (int i = 0; i < 128; i++) {
                k0 = k0 + rand.nextInt(2);
            }
            String k1 = "";
            for (int i = 0; i < 128; i++) {
                k1 = k1 + rand.nextInt(2);
            }
            wires.add(new Wire(k0,k1));
        }
        return wires;
    }

    public boolean tableLookup(Bloodtype donor, Bloodtype recipient) {
        return listCompatibility(donor).contains(recipient);
    }

    public boolean booleanFormula(Bloodtype donor, Bloodtype recipient) {
        LayoutConfig lc = new LayoutConfig(donor, recipient);
        return booleanCircuit(lc);
    }

    private boolean booleanCircuit(LayoutConfig lc) {

        return ANDgate(ORgate(lc.cbar, lc.f), ORgate(lc.bbar, lc.e), ORgate(lc.abar, lc.d));

    }

    public static boolean ORgate(boolean... xs) {
        boolean result = false;
        for (boolean x : xs) {
            result = result || x;
        }
        return result;
    }

    public static boolean ANDgate(boolean... xs) {
        boolean result = true;
        for (boolean x : xs) {
            result = result & x;
        }
        return result;
    }

    public Testpair InputToBloodtypeEnums(String arg0, String arg1) {
        return new Testpair(convertToBloodtype(arg0), convertToBloodtype(arg1));
    }

    public Bloodtype convertToBloodtype(String arg) {
        if (arg.equals(Bloodtype.ZERONEGATIVE.name)) return Bloodtype.ZERONEGATIVE;
        if (arg.equals(Bloodtype.ZEROPOSITIVE.name)) return Bloodtype.ZEROPOSITIVE;
        if (arg.equals(Bloodtype.ANEGATIVE.name)) return Bloodtype.ANEGATIVE;
        if (arg.equals(Bloodtype.APOSITIVE.name)) return Bloodtype.APOSITIVE;
        if (arg.equals(Bloodtype.BNEGATIVE.name)) return Bloodtype.BNEGATIVE;
        if (arg.equals(Bloodtype.BPOSITIVE.name)) return Bloodtype.BPOSITIVE;
        if (arg.equals(Bloodtype.ABNEGATIVE.name)) return Bloodtype.ABNEGATIVE;
        return Bloodtype.ABPOSITIVE;
    }

    public List<Bloodtype> listCompatibility(Bloodtype type) {
        if (type.equals(Bloodtype.ZERONEGATIVE))
            return Arrays.asList(Bloodtype.ZEROPOSITIVE, Bloodtype.ZEROPOSITIVE, Bloodtype.ABNEGATIVE, Bloodtype.APOSITIVE, Bloodtype.BNEGATIVE, Bloodtype.BPOSITIVE, Bloodtype.ABNEGATIVE, Bloodtype.ABPOSITIVE);
        if (type.equals(Bloodtype.ZEROPOSITIVE))
            return Arrays.asList(Bloodtype.ZEROPOSITIVE, Bloodtype.APOSITIVE, Bloodtype.BPOSITIVE, Bloodtype.ABPOSITIVE);
        if (type.equals(Bloodtype.ANEGATIVE))
            return Arrays.asList(Bloodtype.ABNEGATIVE, Bloodtype.APOSITIVE, Bloodtype.ABNEGATIVE, Bloodtype.ABPOSITIVE);
        if (type.equals(Bloodtype.APOSITIVE)) return Arrays.asList(Bloodtype.APOSITIVE, Bloodtype.ABPOSITIVE);
        if (type.equals(Bloodtype.BNEGATIVE))
            return Arrays.asList(Bloodtype.BNEGATIVE, Bloodtype.BPOSITIVE, Bloodtype.ABNEGATIVE, Bloodtype.ABPOSITIVE);
        if (type.equals(Bloodtype.BPOSITIVE)) return Arrays.asList(Bloodtype.BPOSITIVE, Bloodtype.ABPOSITIVE);
        if (type.equals(Bloodtype.ABNEGATIVE)) return Arrays.asList(Bloodtype.ABNEGATIVE, Bloodtype.ABPOSITIVE);
        return Arrays.asList(Bloodtype.ABPOSITIVE);
    }

    public Boolean runProtocol(Bloodtype doner, Bloodtype recipoient) {
        Alice alice = new Alice(recipoient);
        Bob bob = new Bob(doner);
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
