public class Main {

    public static void main(String[] args) {
        Boolean result = null;
        Utility util = new Utility();
        Testpair testpair = util.InputToBloodtypeEnums(args[0], args[1]);

        if (args.length == 3) {
            if (args[2].equals("0")) result = util.tableLookup(testpair.getDonor(), testpair.getRecipient());
            if (args[2].equals("1")) result = util.booleanFormula(testpair.getDonor(), testpair.getRecipient());
        } else {
            result = util.runProtocol(testpair.getDonor(), testpair.getRecipient());
        }

        System.out.println("\nMatch? = " + result.toString());
    }

}

