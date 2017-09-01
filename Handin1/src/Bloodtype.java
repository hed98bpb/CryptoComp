public enum Bloodtype {
    ZERONEGATIVE("0-", "111"), // 111
    ZEROPOSITIVE("0+", "110"), // 110
    ANEGATIVE("A-", "101"),    // 101
    APOSITIVE("A+", "100"),    // 100
    BNEGATIVE("B-", "011"),    // 011
    BPOSITIVE("B+", "010"),    // 010
    ABNEGATIVE("AB-", "001"),  // 001
    ABPOSITIVE("AB+", "000");  // 000

    public String name;
    public String encoding;

    Bloodtype(String name, String encoding){
        this.name = name;
        this.encoding = encoding;
    }
}