import java.math.BigInteger;

public class Utility {

    public Utility() {
    }

    public Boolean runProtocol(Alice alice, Bob bob) {

        BigInteger[] m1 = alice.sendMessage();
        BigInteger m2 = bob.answerMessage(m1);

        return alice.decryptResult(m2);
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


    public Boolean runProtocol(Bloodtype doner, Bloodtype recipient) {
        Alice alice = new Alice(recipient);
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
