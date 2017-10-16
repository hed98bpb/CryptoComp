import java.math.BigInteger;

public class Bob {

    BigInteger dA, dB, dR, rA, rB, rR;

    Bloodtype bloodtype;

    public Bob(Bloodtype bloodtype) {
        this.bloodtype = bloodtype;
        dA = new BigInteger(bloodtype.encoding.charAt(0)+"");
        dB = new BigInteger(bloodtype.encoding.charAt(1)+"");
        dR = new BigInteger(bloodtype.encoding.charAt(2)+"");
    }

    public BigInteger answerMessage(BigInteger[] m1) {
        rA = m1[0];
        rB = m1[1];
        rR = m1[2];
        BigInteger one = new BigInteger("1");

        // (1+(1+rA)*dA)*(1+(1+rB)*dB)*(1+(1+rR)*dR)
        return one.add(((one.add(rA)).multiply(dA)))
                .multiply((one.add((one.add(rB)).multiply(dB))))
                .multiply((one.add((one.add(rR)).multiply(dR))));
    }
}
