
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hand {
    private final int HAND_LIMIT = 5;
    private List<Card> cards = new ArrayList<>(HAND_LIMIT);
    private int handNumber;

    public Hand(List<Card> cards) {
        for (int i = 0; i < HAND_LIMIT; i++) {
            Card card = cards.get(i);
            addCard(card);
        }
        this.handNumber = this.cards.size();
    }

    public Hand() {
    }

    public void addCard(Card card) {
        if (cards.size() < HAND_LIMIT) {
            cards.add(card);
        }
    }

    public void addCard(int index, Card card) {
        if (cards.size() < HAND_LIMIT) {
            cards.add(index, card);
        }
    }

    public void addCards(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public List<Card> removeCards(int[] indices) {
        List<Card> removedCards = new ArrayList<>();
        Arrays.stream(indices).forEach(index -> removedCards.add(cards.remove(index)));
        return removedCards;
    }

    public int getHAND_LIMIT() {
        return HAND_LIMIT;
    }

    public void createHand(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getHandNumber() {
        return handNumber;
    }

    public String toString() {
        return cards.toString();
    }
}