

import java.util.Arrays;
import java.util.List;

public class Dealer {

    private Deck deck;
    private List<Player> players;
    private int numberOfPlayers = 2;

    public static final int ONE_PAIR = 7;
    public static final int TWO_PAIR = 9;
    public static final int THREE_OF_A_KIND = 11;
    public static final int FULL_HOUSE = 13;
    public static final int FOUR_OF_A_KIND = 17;

    public Dealer() {
    }

    void createGame(int numberOfPlayers) {
        deck = new Deck();
        deck.shuffleDeck(); 
        createPlayers();
    }

    public void createPlayers() {
        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(new Player(new Hand(deck.removeCards(dealFirstCards()))));
        }
    }

    public void createPlayer(String name) {
        players.add(new Player(new Hand(deck.removeCards(dealFirstCards())), name));
    }

    int[] dealFirstCards() {
        Hand hand = new Hand();
        int[] indices = new int[hand.getHAND_LIMIT()];
        Arrays.stream(indices).forEach(index -> indices[index] = 0);
        return indices;
    }

    void dealCardsToPlayer(Player player) {
        int cardsNeeded = player.getHand().getHAND_LIMIT() - player.getHand().getCards().size();
        int[] indices = new int[cardsNeeded];
        Arrays.stream(indices).forEach(index -> indices[index] = 0);
        player.getHand().addCards(deck.removeCards(indices));
    }

    void dealCardsToPlayers() {
        players.forEach(player -> dealCardsToPlayer(player));
    }

    void compareHands(){
        players.forEach(player -> player.getHand().getValue());
    }
}