
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
    public int getRankValue(){
        return rank.getValue();
    }

    public Suit getSuit() {
        return suit;
    }
    public int getSuitValue(){
        return suit.getCode();
    }

    public String toString() {
        return rank.toString() + suit.toString();
    }
}