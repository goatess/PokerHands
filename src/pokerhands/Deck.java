
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> FULL_CARDS = new ArrayList<Card>();
    private final int DECK_LIMIT = 52;
    private List<Card> cards;
    private int cardsNumber;

    Deck() {
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                FULL_CARDS.add(new Card(rank, suit));
            }
        }
        this.cards = FULL_CARDS;
        cardsNumber = cards.size();
    }

    Deck(List<Card> cards) {
        this.cards = cards;
        this.cards = FULL_CARDS;
        cardsNumber = cards.size();
    }

    public void shuffleDeck() {
        Collections.shuffle(cards);
    }

    public void collectCards() {
        this.cards = FULL_CARDS;
    }

    public List<Card> drawCards() {
        List<Card> drawn = new ArrayList<>();
        for (int c = 0; c < 5; c++) {
           drawn.add(drawCard());    
        }
        return drawn;
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

    public List<Card> getCards(int c1, int c2, int c3, int c4, int c5) {
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

    @Override
    public String toString() {
        return cards.toString();
    }
}