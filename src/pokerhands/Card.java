public class Card {
    Rank rank;
    Suit suit;

    Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public char getRankSymbol() {
        return rank.getSymbol();
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

    public String getSuitName() {
        return suit.getName();
    }

    @Override
    public String toString() {
        return rank.toString() + suit.toString();
    }
}