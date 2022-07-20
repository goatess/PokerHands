import java.util.ArrayList;
import java.util.List;

public class Hand {
    private final int CARDS_LIMIT = 5;
    private List<Card> cards = new ArrayList<>();
    private int cardsNumber;

    Hand(List<Card> cards) {
        for (int i = 0; i < CARDS_LIMIT; i++) {
            Card card = cards.get(i);
            addCard(card);
        }
        this.cardsNumber = this.cards.size();
    }

    Hand() {
    }

    public void addCard(Card card) {
        if (cards.size() < CARDS_LIMIT) {
            cards.add(card);
        }
    }

    public void addCards(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public void discard() {
        cards.clear();
    }

    public int getCARDS_LIMIT() {
        return CARDS_LIMIT;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getHandNumber() {
        return cardsNumber;
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}
