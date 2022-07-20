public enum Ranking {
    HIGH_CARD("High Card",0),
    ONE_PAIR("One Pair",1),
    TWO_PAIR("Two Pairs",2),
    THREE_OF_A_KIND("Three of a Kind",3),
    STRAIGHT("Straight",4),
    ROYAL_STRAIGHT("Royal Straight",5),
    FLUSH("Flush",6),
    FULL_HOUSE("Full House",7),
    FOUR_OF_A_KIND("Four of a Kind",8),
    STRAIGHT_FLUSH("Straight Flush",9),
    ROYAL_FLUSH("Royal Flush",10);

    private String name;
    private int score;

    Ranking(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }
    public int getScore() {
        return score;
    }
}
