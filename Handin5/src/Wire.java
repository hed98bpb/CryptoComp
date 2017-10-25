/**
 * Created by Root on 24-10-2017.
 */
public class Wire {
    private String k0,k1;

    public Wire(String k0, String k1) {
        this.k0 = k0;
        this.k1 = k1;
    }

    public String k(int i) {
        if (i == 0) {
            return k0;
        } else {
            return k1;
        }
    }


    @Override
    public String toString(){
        return "k0: "+k0+"\n"+"k1: "+k1;
    }
}
