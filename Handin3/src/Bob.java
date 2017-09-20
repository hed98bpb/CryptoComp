import java.security.SecureRandom;

/**
 * Created by Root on 08-09-2017.
 */
public class Bob {

    private Bloodtype b;
    public SecureRandom rand = new SecureRandom();

    private boolean ub, vb, wb, d, da, db, e, ea, eb;
    private boolean[] xas;
    private boolean[][] circuitValues;

    public Bob(int layers, int wires, Bloodtype bloodtype){
        b = bloodtype;
        circuitValues = new boolean[layers][wires];
    }

    public void xor(int layer, int wire){
        circuitValues[layer][wire] = circuitValues[--layer][wire] ^ circuitValues[--layer][++wire];
    }

    public void andWithConstant(int layer, int wire, boolean c){
        circuitValues[layer][wire] = circuitValues[--layer][wire] ^ c;
    }

    public void not(int layer, int wires){
        xorWithConstant(layer, wires);
    }

    private void xorWithConstant(int layer, int wires) {
        circuitValues[layer][wires] =  circuitValues[--layer][wires];
    }

    public boolean getRand() {
        return rand.nextBoolean();
    }

    public void initializeInputWires() {
        xas = new boolean[3];

        for (int i = 0; i < 3; i++){
            xas[i] = getRand();
        }

        circuitValues[0][0] = b.encoding.charAt(0) == 0 ? false ^ xas[0] : true ^ xas[0];
        circuitValues[0][2] = b.encoding.charAt(1) == 0 ? false ^ xas[1] : true ^ xas[1];
        circuitValues[0][4] = b.encoding.charAt(2) == 0 ? false ^ xas[2] : true ^ xas[2];

    }

    public boolean[] sendSharesForInputWires() {
        return xas;
    }

    public void setInputWiresfromAlice(boolean[] inputWiresfromAlice) {
        circuitValues[0][1] = inputWiresfromAlice[0];
        circuitValues[0][3] = inputWiresfromAlice[1];
        circuitValues[0][5] = inputWiresfromAlice[2];
    }

    public void identity(int layer, int wire) {
        circuitValues[layer][wire] = circuitValues[--layer][wire];
    }

    public void setTriple(boolean ub, boolean vb, boolean wb) {
        this.ub = ub;
        this.vb = vb;
        this.wb = wb;
    }

    public void identity(int layer, int fromWire, int toWire) {
        circuitValues[layer][toWire] = circuitValues[--layer][fromWire];
    }

    public void calculateDAndEShares(int layer, int wire) {
        db = circuitValues[--layer][wire] ^ ub;
        eb = circuitValues[--layer][++wire] ^ vb;
    }

    public boolean[] sendDAndEShares() {
        return new boolean[] {db, eb};
    }

    public void setDAndESharesFromAlice(boolean[] bobsDAndE) {
        this.da = bobsDAndE[0];
        this.ea = bobsDAndE[1];
    }

    public void calculateDAndEValues() {
        d = da ^ db;
        e = ea ^ eb;
    }

    public boolean sendOutput() {
        return circuitValues[5][0];
    }

    public void calculateZValue(int layer, int wire) {
        circuitValues[layer][wire] = wb ^ (e & circuitValues[--layer][wire]) ^ (d & circuitValues[--layer][++wire]) ^ (e & d);
    }
}
