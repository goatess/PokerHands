

public enum Suit {
    C("CLUBS",1), D("DIAMONDS",2), H("HEARTS",3), S("SPADES",4);

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
