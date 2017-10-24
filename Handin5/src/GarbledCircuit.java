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

    }

    public ArrayList<Wire> getEncoding(){
        ArrayList<Wire> res = new ArrayList<>();
        for (int i = 0; i < _NoInputWires_; i++){
            res.add(wires.get(i));
        }
        return res;
    }

    private void makeFirstNotGates(){
        gates.add(new GarbledGate(wires.get(0),wires.get(6)));
        gates.add(new GarbledGate(wires.get(2),wires.get(8)));
        gates.add(new GarbledGate(wires.get(4),wires.get(10)));
    }

}