import java.util.List;

public class Player {
    private Hand hand;
    private String name;

    Player(Hand hand) {
        this.hand = hand;
    }

    Player(Hand hand, String name) {
        this.hand = hand;
        this.name = name;
    }

    public void putCardsToHand(List<Card> cards){
        hand.addCards(cards);
    }

    public Hand getHand() {
        return hand;
    }

    @Override
    public String toString() {
        return name + ": " + hand.getCards().toString();
    }
}
