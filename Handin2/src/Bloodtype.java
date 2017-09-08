public enum Bloodtype {
    ZERONEGATIVE("0-", "000"), // 0
    ZEROPOSITIVE("0+", "001"), // 1
    ANEGATIVE("A-", "100"),    // 2
    APOSITIVE("A+", "101"),    // 3
    BNEGATIVE("B-", "010"),    // 4
    BPOSITIVE("B+", "011"),    // 5
    ABNEGATIVE("AB-", "110"),  // 6
    ABPOSITIVE("AB+", "111");  // 7

    public String name;
    public String encoding;

    Bloodtype(String name, String encoding){
        this.name = name;
        this.encoding = encoding;
    }

    public int encodingToInt() {
        if (encoding == "000") return 0;
        if (encoding == "001") return 1;
        if (encoding == "010") return 2;
        if (encoding == "011") return 3;
        if (encoding == "100") return 4;
        if (encoding == "101") return 5;
        if (encoding == "110") return 6;
        return 7;
    }
}