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

    public Card removeCard(int cardPosition){
        if (cards.size() - 1 < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (cards.size() < 1) {
            throw new IllegalStateException("Hand is empty");
        }
        return cards.remove(cardPosition);
    }
  
    public void addCards(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public void discardHand() {
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
    public int getCardsMissing() {
        return CARDS_LIMIT - cards.size();
    }
    

    String cardsInHand;
    @Override
    public String toString() {
        cardsInHand = "";
        cards.forEach(card -> cardsInHand += card + " ");
        return cardsInHand;
    }
}
