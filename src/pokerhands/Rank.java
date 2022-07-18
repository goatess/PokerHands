public enum Rank {
    TWO('2', 2, 2),
    THREE('3', 3, 3),
    FOUR('4', 4, 4),
    FIVE('5', 5, 5),
    SIX('6', 6, 6),
    SEVEN('7', 7, 7),
    EIGHT('8', 8, 8),
    NINE('9', 9, 9),
    TEN('T', 10, 10),
    JACK('J', 10, 11),
    QUEEN('Q', 10, 12),
    KING('K', 10, 13),
    ACE('A', 11, 14);

    private int points;
    private char symbol;
    private int value;

    private Rank(char symbol, int points, int value) {
        this.symbol = symbol;
        this.points = points;
        this.value = value;
    }

    public int getRankPoints() {
        return points;
    }

    public char getRankSymbol() {
        return symbol;
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        return String.valueOf(getRankSymbol());
    }
}
