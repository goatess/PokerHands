package pokerhands;

public enum Suit {
    C("CLUBS"), D("DIAMONDS"), H("HEARTS"), S("SPADES");

    private String suitName;

    private Suit(String name) {
        this.suitName = name;
    }

    public String getSuitName() {
        return suitName;
    }
}
