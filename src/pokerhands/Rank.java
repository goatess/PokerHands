public enum Rank {
    TWO('2', 2, 0),
    THREE('3', 3, 1),
    FOUR('4', 4, 2),
    FIVE('5', 5, 3),
    SIX('6', 6, 4),
    SEVEN('7', 7, 5),
    EIGHT('8', 8, 6),
    NINE('9', 9, 7),
    TEN('T', 10, 8),
    JACK('J', 10, 9),
    QUEEN('Q', 10, 10),
    KING('K', 10, 11),
    ACE('A', 11, 12);

    private int points;
    private char symbol;
    private int value;

    private Rank(char symbol, int points, int value) {
        this.symbol = symbol;
        this.points = points;
        this.value = value;
    }

    public int getPoints() {
        return points;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getValue() {
        return value;
    }
  
    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}