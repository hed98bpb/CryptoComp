import java.util.ArrayList;

public class GarbledCircuit {
    public static final int _NoInputWires_ = 6;
    public static final int _NoOfWires_ = 20;

    public ArrayList<Wire> wires = new ArrayList<>();
    public Wire d;
    public ArrayList<Wire> enc;

    private Utility util = new Utility();

    public GarbledCircuit(){
       wires = util.generateWireKeys(wires);
       d = wires.get(_NoOfWires_-1);
       enc = getEncoding();
    }

    public ArrayList<Wire> getEncoding(){
        ArrayList<Wire> res = new ArrayList<>();
        for (int i = 0; i < _NoInputWires_; i++){
            res.add(wires.get(i));
        }
        return res;
    }

    private ArrayList<GarbledGate> MakeFirstNotGates(){

        return null;
    }

}