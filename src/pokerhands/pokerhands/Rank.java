package pokerhands;

public enum Rank {
    TWO('2', 2),
    THREE('3', 3),
    FOUR('4', 4),
    FIVE('5', 5),
    SIX('6', 6),
    SEVEN('7', 7),
    EIGHT('8', 8),
    NINE('9', 9),
    TEN('T', 10),
    JACK('J', 10),
    QUEEN('Q', 10),
    KING('K', 10),
    ACE('A', 11);

    private int rankPoints;
    private char symbol;

    private Rank(char symbol, int points) {
        this.symbol = symbol;
        this.rankPoints = points;
    }

    public int getRankPoints() {
        return rankPoints;
    }

    public char getRankSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return String.valueOf(getRankSymbol());
    }

}
