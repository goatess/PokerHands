public enum PokerHand {
    HIGH_CARD("High Card",1),
    ONE_PAIR("One Pair",2),
    TWO_PAIR("Two Pairs",3),
    THREE_OF_A_KIND("Three of a Kind",4),
    STRAIGHT("Straight",5),
    ROYAL_STRAIGHT("Royal Straight",6),
    FLUSH("Flush",7),
    FULL_HOUSE("Full House",8),
    FOUR_OF_A_KIND("Four of a Kind",9),
    STRAIGHT_FLUSH("Straight Flush",10),
    ROYAL_FLUSH("Royal Flush",11);

    private String name;
    private int scoreValue;

    PokerHand(String name, int scoreValue) {
        this.name = name;
        this.scoreValue = scoreValue;
    }

    public String getName() {
        return name;
    }
    public int getScoreValue() {
        return scoreValue;
    }
}