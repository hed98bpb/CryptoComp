public enum Bloodtype {
    ZERONEGATIVE("0-", "000"), // 111
    ZEROPOSITIVE("0+", "001"), // 110
    ANEGATIVE("A-", "100"),    // 101
    APOSITIVE("A+", "101"),    // 100
    BNEGATIVE("B-", "010"),    // 011
    BPOSITIVE("B+", "011"),    // 010
    ABNEGATIVE("AB-", "110"),  // 001
    ABPOSITIVE("AB+", "111");  // 000

    public String name;
    public String encoding;

    Bloodtype(String name, String encoding){
        this.name = name;
        this.encoding = encoding;
    }
}