
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Dealer {

    private Deck deck;
    private List<Player> players = new ArrayList<>();

    private int numberOfPlayers = 2;
    private HandScore handScore;
    private Player handWinner;

    public Dealer() {
        this.deck = new Deck();
        players = new ArrayList<>();
    }

    public Dealer(int numberOfPlayers) {
        this.deck = new Deck();
        players = new ArrayList<>();
        this.numberOfPlayers = numberOfPlayers;
    }

    void setUp(int numberOfPlayers) {
        deck.shuffleDeck();
        createPlayers();
        rankHands();
        gameLoop();
    }

    void gameLoop() {
        deck.collectCards();
        deck.shuffleDeck();
        dealCardsToPlayers();
        rankHands();
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

    void rankHands() {
        List<Integer> scores = new ArrayList<>();
        List<Integer> highCards = new ArrayList<>();
        for (Player player : players) {
            handScore = new HandScore(player.getHand());
            scores.add(handScore.getScore().getScoreValue());
            highCards.add(handScore.getHighCard());
        }
        compareHands(scores, highCards);
    }

    Player compareHands(List<Integer> scores, List<Integer> highCards) {
        int maxRank = Collections.max(scores);
        int minRank = Collections.min(scores);
        int maxCard = Collections.max(highCards);
        handWinner = players.get(scores.indexOf(maxRank));
        if (minRank == maxRank) {
            handWinner = players.get(scores.indexOf(maxCard));
        }
        return handWinner;
    }

    public Player getHandWinner() {
        return handWinner;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public String winnerToString() {
        return handWinner.toString();
    }
}