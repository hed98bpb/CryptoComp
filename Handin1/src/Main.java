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

        if (args[2].equals("0")) results = util.tableLookup(testpair.getDonor(), testpair.getRecipient());
        if (args[2].equals("0")) results = util.tableLookup(testpair.getDonor(), testpair.getRecipient());

        System.out.print(results.toString());
    }



}

