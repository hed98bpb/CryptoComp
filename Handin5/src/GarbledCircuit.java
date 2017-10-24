import java.util.ArrayList;

public class GarbledCircuit {
    public static final int _NoInputWires_ = 6;
    public static final int _NoOfWires_ = 20;

    public ArrayList<Wire> wires = new ArrayList<>();
    public Wire d;
    public ArrayList<Wire> enc;

    private Utility util = new Utility();

    private ArrayList<GarbledGate> gates = new ArrayList<>();

    public GarbledCircuit(){
       wires = util.generateWireKeys(wires);
       d = wires.get(_NoOfWires_-1);
       enc = getEncoding();

       makeFirstNotGates();
       wires.set(7, wires.get(1));
       wires.set(9, wires.get(3));
       wires.set(11, wires.get(5));
       makeFirstAndGates();
       makeSecondNotGates();
       makeThirdAndNotGates();
       makeFinalAndGate();
    }

    private void makeFirstNotGates(){
        gates.add(new GarbledGate(wires.get(0),wires.get(6)));
        gates.add(new GarbledGate(wires.get(2),wires.get(8)));
        gates.add(new GarbledGate(wires.get(4),wires.get(10)));
    }

    private void makeFirstAndGates(){
        gates.add(new GarbledGate(wires.get(6), wires.get(7), wires.get(12)));
        gates.add(new GarbledGate(wires.get(8), wires.get(9), wires.get(13)));
        gates.add(new GarbledGate(wires.get(10), wires.get(11), wires.get(14)));
    }

    private void makeSecondNotGates(){
        gates.add(new GarbledGate(wires.get(12),wires.get(15)));
        gates.add(new GarbledGate(wires.get(13),wires.get(16)));
        gates.add(new GarbledGate(wires.get(14),wires.get(17)));
    }

    private void makeThirdAndNotGates(){
        gates.add(new GarbledGate(wires.get(15), wires.get(16), wires.get(18)));
        gates.add(new GarbledGate(wires.get(17),wires.get(19)));
    }

    private void makeFinalAndGate(){
        gates.add(new GarbledGate(wires.get(18), wires.get(19), wires.get(20)));
    }

    public Wire getD() {
        return d;
    }

    public ArrayList<Wire> getEncoding(){
        ArrayList<Wire> res = new ArrayList<>();
        for (int i = 0; i < _NoInputWires_; i++){
            res.add(wires.get(i));
        }
        return res;
    }

    public ArrayList<Wire> getEnc() {
        return enc;
    }

    public ArrayList<GarbledGate> getGates() {
        return gates;
    }

    public Wire outputWire(){
        return wires.get(_NoOfWires_-1);
    }
}