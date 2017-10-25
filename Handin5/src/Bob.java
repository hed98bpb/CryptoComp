import java.util.ArrayList;
import java.util.List;

/**
 * Created by Root on 08-09-2017.
 */
public class Bob {

    private Bloodtype b;
    private String[] X;
    private String[] Y = new String[3];
    private String Z;

    public Bob(Bloodtype bloodtype){
        b = bloodtype;
    }

    public void receiveX(String[] strings) {
        X = strings;
    }

    public void fakeOT(List<Wire> wires) {
        ArrayList<Wire> temp = new ArrayList<>();
        temp.add(wires.get(1));
        temp.add(wires.get(3));
        temp.add(wires.get(5));
        for (int i = 0; i<b.encoding.toCharArray().length; i++){
            if(Character.compare(b.encoding.toCharArray()[i], '1') == 0) {
                Y[i] = temp.get(i).k(1);
            } else
                Y[i] = temp.get(i).k(0);
        }
    }

    public void evaluateCircuit(ArrayList<GarbledGate> gates) {
        //Evaluate NOT-gates
        String w6 = gates.get(0).evaluate(X[0]);
        String w8 = gates.get(1).evaluate(X[1]);
        String w10 = gates.get(2).evaluate(X[2]);

        //Evaluate AND-gates
        String w12 = gates.get(3).evaluate(w6+Y[0]);
        String w13 = gates.get(4).evaluate(w8+Y[1]);
        String w14 = gates.get(5).evaluate(w10+Y[2]);

        //Evaluate NOT-gates
        String w15 = gates.get(6).evaluate(w12);
        String w16 = gates.get(7).evaluate(w13);
        String w17 = gates.get(8).evaluate(w14);

        //Evaluate AND-gate
        String w18 = gates.get(9).evaluate(w15+w16);
        String w19 = w17;

        //Evaluate final AND-gate
        String w20 = gates.get(10).evaluate(w18+w19);

        //Set Z to the output of last gate
        Z=w20;
    }

    public String sendZ() {
        return Z;
    }
}
