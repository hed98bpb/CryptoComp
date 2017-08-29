public enum Bloodtype {
    ZERONEGATIVE("0-", 0),
    ZEROPOSITIVE("0+", 0),
    ANEGATIVE("A-", 0),
    APOSITIVE("A+", 0),
    BNEGATIVE("B-", 0),
    BPOSITIVE("B+", 0),
    ABNEGATIVE("AB-", 0),
    ABPOSITIVE("AB+", 0);

    public String name;
    public int encoding;

    Bloodtype(String name, int encoding){
        this.name = name;
        this.encoding = encoding;
    }
}