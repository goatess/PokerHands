public enum Suit {
    C("CLUBS", 0), D("DIAMONDS", 1), H("HEARTS", 2), S("SPADES", 3);

    private String name;
    private int value;

    private Suit(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
