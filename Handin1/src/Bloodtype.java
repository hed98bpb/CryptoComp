<<<<<<< HEAD
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
=======
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
>>>>>>> 3ffe60d64d4d4fa926a307faa56f8d9cfded2fa9
}