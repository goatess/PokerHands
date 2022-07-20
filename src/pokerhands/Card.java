public class Card {
    Rank rank;
    Suit suit;

    Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int getRankPoints() {
        return rank.getPoints();
    }

    public int getRankValue() {
        return rank.getValue();
    }

    public int getSuitValue() {
        return suit.getValue();
    }

    @Override
    public String toString() {
        return rank.toString() + suit.toString();
    }
}
