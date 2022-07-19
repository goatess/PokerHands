
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hand {
    private final int CARDS_LIMIT = 5;
    private List<Card> cards = new ArrayList<>(CARDS_LIMIT);
    private int cardsNumber;

    public Hand(List<Card> cards) {
        for (int i = 0; i < CARDS_LIMIT; i++) {
            Card card = cards.get(i);
            addCard(card);
        }
        this.cardsNumber = this.cards.size();
    }

    public Hand() {
    }

    public void addCard(Card card) {
        if (cards.size() < CARDS_LIMIT) {
            cards.add(card);
        }
    }

    public void addCard(int index, Card card) {
        if (cards.size() < CARDS_LIMIT) {
            cards.add(index, card);
        }
    }

    public void addCards(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public List<Card> removeCards(int[] played) {
        List<Card> discarded = new ArrayList<>();
        Arrays.stream(played).forEach(card -> discarded.add(cards.remove(card)));
        return discarded;
    }

    public int getCARDS_LIMIT() {
        return CARDS_LIMIT;
    }

    public void setHand(int c1, int c2, int c3, int c4, int c5) {
        this.cards.addAll(cards);
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getHandNumber() {
        return cardsNumber;
    }

    public String toString() {
        return cards.toString();
    }
}