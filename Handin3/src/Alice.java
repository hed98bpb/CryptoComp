import java.security.SecureRandom;

/**
 * Created by Root on 08-09-2017.
 */
public class Alice {

    private Bloodtype b;
    public SecureRandom rand = new SecureRandom();
    private int layers, wires;

    public Boolean output;

    private boolean ua, va, wa, d, da, db, e, ea, eb;
    private boolean[] xbs;
    private boolean[][] circuitValues;

    public Alice(int layers, int wires, Bloodtype bloodtype){
        this.layers = layers;
        this.wires = wires;
        b = bloodtype;
        circuitValues = new boolean[layers][wires];
    }

    public void xor(int layer, int wire){
        circuitValues[layer][wire] = circuitValues[layer-1][wire] ^ circuitValues[layer-1][wire+1];
    }

    public void andWithConstant(int layer, int wire, boolean c){
        circuitValues[layer][wire] = circuitValues[layer-1][wire] ^ c;
    }

    public void not(int layer, int wire){
        xorWithConstant(layer, wire, true);
    }

    private void xorWithConstant(int layer, int wire, boolean c) {
        circuitValues[layer][wire] = circuitValues[layer-1][wire] ^ c;
    }

    public boolean hasOutput(){
        return output != null;
    }

    public boolean getRand() {
        return rand.nextBoolean();
    }

    public void initializeInputWires() {
        xbs = new boolean[3];

        for (int i = 0; i < 3; i++){
            xbs[i] = getRand();
        }

        circuitValues[0][0] = b.encoding.charAt(0) == "0".charAt(0) ? false ^ xbs[0] : true ^ xbs[0];
        circuitValues[0][2] = b.encoding.charAt(1) == "0".charAt(0) ? false ^ xbs[1] : true ^ xbs[1];
        circuitValues[0][4] = b.encoding.charAt(2) == "0".charAt(0) ? false ^ xbs[2] : true ^ xbs[2];

    }

    public void setInputWiresFromBob(boolean[] inputWiresFromBob) {
        circuitValues[0][1] = inputWiresFromBob[0];
        circuitValues[0][3] = inputWiresFromBob[1];
        circuitValues[0][5] = inputWiresFromBob[2];
    }

    public boolean[] sendSharesForInputWires() {
        return xbs;
    }

    public void identity(int layer, int wire) {
        circuitValues[layer][wire] = circuitValues[layer-1][wire];
    }

    public void setTriple(boolean ua, boolean va, boolean wa) {
        this.ua = ua;
        this.va = va;
        this.wa = wa;
    }

    public void identity(int layer, int fromWire, int toWire) {
        circuitValues[layer][toWire] = circuitValues[layer-1][fromWire];
    }

    public void calculateDAndEShares(int layer, int wire) {
        da = circuitValues[layer-1][wire] ^ ua;
        ea = circuitValues[layer-1][wire+1] ^ va;
    }

    public void setDAndESharesFromBob(boolean[] bobsDAndE) {
        this.db = bobsDAndE[0];
        this.eb = bobsDAndE[1];
    }

    public boolean[] sendDAndEShares() {
        return new boolean[] {da, ea};
    }

    public void calculateDAndEValues() {
        d = da ^ db;
        e = ea ^ eb;
    }

    public void calculateZValue(int layer, int wire) {
        circuitValues[layer][wire] = wa ^ (e & circuitValues[layer-1][wire]) ^ (d & circuitValues[layer-1][wire+1]) ^ (e & d);
    }

    public void calculateOutput(boolean bobsOutput) {
        output = circuitValues[layers-1][0] ^ bobsOutput;
    }
}
