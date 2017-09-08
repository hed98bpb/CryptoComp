public class Main {

    // The program is executed with the input x y z in the console
    // x = donor bloodtype
    // y = recipient bloodtype
    // z = function which is {0,1}
    // with 0 being tablelookup and 1
    // if only two args are given the OTTT protocol is executed !!!!

    public static void main(String[] args) {
        Boolean result = null;
        Utility util = new Utility();
        Testpair testpair = util.InputToBloodtypeEnums(args[0], args[1]);

        if (args.length == 3) {
            if (args[2].equals("0")) result = util.tableLookup(testpair.getDonor(), testpair.getRecipient());
            if (args[2].equals("1")) result = util.booleanFormula(testpair.getDonor(), testpair.getRecipient());
        } else {
            // setup
            boolean T[][] = new boolean[8][8];
            String s = ""; //string for debug printing of table
            makeTable(util, T, s);

            // make Dealer, Alice and Bob
            Dealer d = new Dealer(T, 3);
            // (Step 4 of the dealer is simulated by inputting the values as below)
            Alice alice = new Alice(d.Ma, d.r, d.n, testpair.getRecipient());
            Bob bob = new Bob(d.Mb, d.s, d.n, testpair.getDonor());

            // Run protocol
            result = runProtocol(alice, bob);

        }

        //System.out.println(Arrays.deepToString(f).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));

        System.out.println("\nMatch? = " + result.toString());
    }

    public static Boolean runProtocol(Alice alice, Bob bob) {
        Boolean result;
        alice.calculateValue();
        // Alice sends u to Bob
        bob.u = alice.u;
        bob.calculateValue();
        // Bob sends v, zb to Alice
        alice.v = bob.v;
        alice.zb = bob.zb;
        // Alice computes the output
        result = alice.calculateOutput();
        return result;
    }

    public static void makeTable(Utility util, boolean[][] f, String s) {
        //Generate truth table T and store ugly debug string for later printing
        for (int i = 0; i < 8; i ++) {
            for (int k = 0; k < 8; k++) {
                boolean b = util.booleanFormula(Bloodtype.values()[i], Bloodtype.values()[k]);
                if (b){
                    f[i][k] = true;
                } else
                    f[i][k] = false;
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
    }

}

