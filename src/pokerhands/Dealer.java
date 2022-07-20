import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dealer {
    private Deck deck;
    private int playersNumber = 2;
    private List<Player> players;
    private int rounds;

    Dealer() {
        this.deck = new Deck();
        players = new ArrayList<>();
    }

    Dealer(int playersNumber, int rounds) {
        this.rounds = rounds;
        this.playersNumber = playersNumber;
        this.deck = new Deck();
        players = new ArrayList<>();
        setUpGame();
    }

    private void setUpGame() {
        createPlayers();
        rounds = 0;
        do {
            rounds++;
            dealCards();
            new Score(players);
            cleanBoard();
        } while (rounds < 5);
    }

    private void createPlayers() {
        for (int i = 0; i < playersNumber; i++) {
            players.add(new Player(new Hand()));
        }
    }

    public void dealCards() {
        deck.shuffleCards();
        players.forEach(player -> dealHand(player.getHand()));
    }

    private void dealHand(Hand hand) {
        int oneMoreCard = 0;
        int[] drawn = new int[hand.getCARDS_LIMIT()];
        Arrays.stream(drawn).forEach(card -> drawn[card] = oneMoreCard);
        hand.addCards(deck.drawCards());
    }

    private void cleanBoard() {
        players.forEach(player -> player.getHand().discardHand());
        deck.collectCards();
    }

    public List<Player> getPlayers() {
        return players;
    }
}
