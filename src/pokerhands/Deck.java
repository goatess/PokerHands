
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Deck {
    private final List<Card> FULL_CARDS = new ArrayList<Card>();
    private final int DECK_LIMIT = 52;
    private List<Card> cards;
    private int cardsNumber;

    public Deck() {
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                FULL_CARDS.add(new Card(rank, suit));
            }
        }
        this.cards = FULL_CARDS;
        cardsNumber = cards.size();
    }

    public Deck(List<Card> cards) {
        this.cards = cards;
    }

    public Card removeCard(int index) {
        if (cards.size() - 1 < index) {
            throw new IndexOutOfBoundsException();
        }
        return cards.remove(index);
    }

    public Card removeCard() {
        if (cards.size() < 1) {
            throw new IllegalStateException("Deck is empty");
        }
        return cards.remove(0);
    }

    public List<Card> removeCards(int[] indices) {
        List<Card> removedCards = new ArrayList<>();
        Arrays.stream(indices).forEach(index -> removedCards.add(cards.remove(index)));
        return removedCards;
    }

    public void shuffleDeck() {
        Collections.shuffle(cards);
    }

    public int getDECK_LIMIT() {
        return DECK_LIMIT;
    }

    public List<Card> getFullBundle() {
        return FULL_CARDS;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getCardsNumber() {
        return cardsNumber;
    }

    public String toString() {
        return cards.toString();
    }

    public void setCards(List<Card> cards) {

        this.cards = cards;
    }

}