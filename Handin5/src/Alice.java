import java.util.ArrayList;

/**
 * Created by Root on 08-09-2017.
 */
public class Alice {

    private Bloodtype b;
    private GarbledCircuit gb;

    private String[] X = new String[3];
    private String Z;

    public Alice(Bloodtype bloodtype){
        b = bloodtype;
    }


    public boolean calculateOutput() throws Exception {
        if(Z.equals(gb.d.k(1))){
            return true;
        }
        if(Z.equals(gb.d.k(0))){
            return false;
        }
        throw new Exception("We dun goofed");
    }

    public void makeGarbledCurcuit() {
        gb = new GarbledCircuit();
    }

    public GarbledCircuit getGb() {
        return gb;
    }

    public String[] makeEncodingX() {
        ArrayList<Wire> temp = new ArrayList<>();
        temp.add(gb.enc.get(0));
        temp.add(gb.enc.get(2));
        temp.add(gb.enc.get(4));
        for (int i = 0; i<b.encoding.toCharArray().length; i++){
            if(Character.compare(b.encoding.toCharArray()[i], '1') == 0) {
                X[i] = temp.get(i).k(1);
            } else
                X[i] = temp.get(i).k(0);
        }
        return X;
    }

    public void receiveZ(String s) {
        Z = s;
    }
}
