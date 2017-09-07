import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    // The program is executed with the input x y z in the console
    // x = donor bloodtype
    // y = recipient bloodtype
    // z = function which is {0,1}
    // with 0 being tablelookup and 1

    public static void main(String[] args) {
        Boolean results = null;
        Utility util = new Utility();
        Testpair testpair = util.InputToBloodtypeEnums(args[0], args[1]);

        //init
        int f[][] = new int[8][8];
        String s = new String(); //string for debug printing of table

        //Generate truth table and store ugly debug string for later printing
        for (int i = 0; i < 8; i ++) {
            for (int k = 0; k < 8; k++) {
                boolean b = util.booleanFormula(Bloodtype.values()[i], Bloodtype.values()[k]);
                if (b){
                    f[i][k] = 1;
                } else
                    f[i][k] = 0;
                if(i==0){
                    s+=Bloodtype.values()[7-k]+ " ";
                    if(k==7){
                        s+="\t"+"  Doner/Reciever";
                        s+="\n";
                        s += f[0][0] + "           ";
                        s += f[0][1] + "           ";
                        s += f[0][2] + "           ";
                        s += f[0][3] + "           ";
                        s += f[0][4] + "           ";
                        s += f[0][5] + "           ";
                        s += f[0][6] + "           ";
                        s += f[0][7] + "           ";
                        s+=Bloodtype.values()[7];
                        s+="\n";
                    }
                } else {
                    s += f[i][k] + "           ";
                    if(k==7){
                        s +=Bloodtype.values()[7-i];
                        s +="\n";
                    }
                }
            }
        }

        //System.out.println(Arrays.deepToString(f).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
        System.out.print(s);

        if (args[2].equals("0")) results = util.tableLookup(testpair.getDonor(), testpair.getRecipient());
        if (args[2].equals("1")) results = util.booleanFormula(testpair.getDonor(), testpair.getRecipient());

        System.out.println("\nMatch? " + results.toString());
    }



}

