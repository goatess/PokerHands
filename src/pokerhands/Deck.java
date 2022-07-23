
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> CARDS_BUNDLE = new ArrayList<Card>();
    private final int CARDS_LIMIT = 52;
    private List<Card> cards;
    private int cardsNumber;

    Deck() {
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                CARDS_BUNDLE.add(new Card(rank, suit));
            }
        }
        this.cards = CARDS_BUNDLE;
        cardsNumber = cards.size();
    }

    private Card drawCard() {
        if (cards.size() - 1 < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (cards.size() < 1) {
            throw new IllegalStateException("Deck is empty");
        }
        return cards.remove(0);
    }

    public List<Card> drawCards() {
        List<Card> drawn = new ArrayList<>();
        for (int card = 0; card < 5; card++) {
            drawn.add(drawCard());
        }
        return drawn;
    }

    public void shuffleCards() {
        Collections.shuffle(cards);
    }

    public void collectCards() {
        this.cards = CARDS_BUNDLE;
    }

    public List<Card> drawCards(int c1, int c2, int c3, int c4, int c5) {
        List<Card> cardsTest = new ArrayList<>();
        cardsTest.add(cards.get(c1));
        cardsTest.add(cards.get(c2));
        cardsTest.add(cards.get(c3));
        cardsTest.add(cards.get(c4));
        cardsTest.add(cards.get(c5));
        return cardsTest;
    }

    public Card getCard(int card) {
        return cards.get(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getCardsNumber() {
        return cardsNumber;
    }
    public int getCARDS_LIMIT() {
        return CARDS_LIMIT;
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}
