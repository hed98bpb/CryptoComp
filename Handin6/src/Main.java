public class Main {

    public static void main(String[] args) {
        Boolean result = null;
        Utility util = new Utility();
        Testpair testpair = util.InputToBloodtypeEnums(args[0], args[1]);

        result = util.runProtocol(testpair.getDonor(), testpair.getRecipient());

        System.out.println("\nMatch? = " + result.toString());
    }

}

