package pokerhands;

import java.util.List;

public class GameOperations {
    
    private Deck deck;
    private Hand hand;
    private List<Player> players;
    private Player player;
    private int numberOfPlayers = 2;
    private int[] indices;

    public GameOperations() {
    }

    void createRound() {
        buildDeck(numberOfPlayers);
        createPlayers();

    }

    public void buildDeck(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        deck = new Deck();
    }

    public void createPlayers() {
        for (int i = 0; i < numberOfPlayers; i++) {
            dealFirstCards();
            players.add(new Player(new Hand(deck.removeCards(dealFirstCards()))));
        }
    }

    int[] dealFirstCards() {
        for (int index = 0; index < hand.getHAND_LIMIT(); index++) {
            indices[index] = 0;
        }
        return indices;
    }

    int[] dealCards() {
        for (int index = 0; index < player.getHand().getHAND_LIMIT(); index++) {
            indices[index] = 0;
        }
        return indices;
    }
}