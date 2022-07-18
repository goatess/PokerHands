package pokerhands;

public class Card {
    int rankValue;
    char suitValue;
    Rank rank;
    Suit suit;

    Card(Rank rank, Suit suit) {
        // super();
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public String toString() {
        return rank.toString() + suit.toString();
    }

    public String rankToString(Rank rank) {
        String rankSymbol = String.valueOf(rank.getRankSymbol());
        return rankSymbol;

    }

    public String toString(Suit suit) {
        return suit.toString();
    }
}
/*
 * Black: 2H 3D 5S 9C KD White: 2C 3H 4S 8C AH
 * Black: 2H 4S 4C 2D 4H White: 2S 8S AS QS 3S
 * Black: 2H 3D 5S 9C KD White: 2C 3H 4S 8C KH
 * Black: 2H 3D 5S 9C KD White: 2D 3H 5C 9S KH
 */
