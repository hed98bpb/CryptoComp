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

            // make Dealer, Alice and Bob
            //  Dealer d = new Dealer(T, 3);
            //(Step 4 of the dealer is simulated by inputting the values as below)
            //  Alice alice = new Alice(d.Ma, d.r, d.n, testpair.getRecipient());
            //  Bob bob = new Bob(d.Mb, d.s, d.n, testpair.getDonor());

            //Run protocol
            //  result = runProtocol(alice, bob);

        }

        //System.out.println(Arrays.deepToString(f).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));

        System.out.println("\nMatch? = " + result.toString());
    }

    public static Boolean runProtocol(Alice alice, Bob bob) {
        Boolean result = false;
        // Layer 1

        // Layer 2

        // Layer 3
        return result;
    }


}

