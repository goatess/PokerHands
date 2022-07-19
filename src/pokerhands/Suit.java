

public enum Suit {
    C("CLUBS",0), D("DIAMONDS",1), H("HEARTS",2), S("SPADES",3);

    private String suitName;
    private int code;

    private Suit(String name, int code) {
        this.suitName = name;
        this.code = code;
    }

    public String getSuitName() {
        return suitName;
    }
    public int getCode() {
        return code;
    }
}
