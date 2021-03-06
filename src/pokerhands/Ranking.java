public enum Ranking {
    HIGH_CARD("High Card", 0),
    ONE_PAIR("One Pair", 1, 2),
    TWO_PAIR("Two Pairs", 2, 4),
    THREE_OF_A_KIND("Three of a Kind", 3, 3),
    STRAIGHT("Straight", 4),
    ROYAL_STRAIGHT("Royal Straight", 5),
    FLUSH("Flush", 6),
    FULL_HOUSE("Full House", 7, 5),
    FOUR_OF_A_KIND("Four of a Kind", 8, 8),
    STRAIGHT_FLUSH("Straight Flush", 9),
    ROYAL_FLUSH("Royal Flush", 10);

    private String name;
    private int value;
    private int pairsScore;

    Ranking(String name, int score, int pairsScore) {
        this.name = name;
        this.value = score;
        this.pairsScore = pairsScore;
    }

    Ranking(String name, int score) {
        this.name = name;
        this.value = score;
    }

    Ranking(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
    
    public int getPairsScore() {
        return pairsScore;
    }

    @Override
    public String toString() {
        return String.valueOf(name);
    }
}
