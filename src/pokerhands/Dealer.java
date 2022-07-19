
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Dealer {
    private int numberOfPlayers = 2;
    private int highCard;
    private Deck deck;
    private List<Player> players;
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
        setUpGame(numberOfPlayers);
    }

    private void setUpGame(int numberOfPlayers) {
        createPlayers();
        deck.shuffleDeck();
        gameLoop();
    }

    private void gameLoop() {
        dealCardsToPlayers();
        rankHands();
        deck.collectCards();
        deck.shuffleDeck();
    }

    public void createPlayer(String name) {
        players.add(new Player(new Hand(), name));
    }

    private void createPlayers() {
        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(new Player(new Hand()));
        }
    }

    public void dealCardsToPlayers() {
        players.forEach(player -> dealCardsToPlayer(player));
    }

    private void dealCardsToPlayer(Player player) {
        int oneMore = 0;
        Hand hand = player.getHand();
        int missing = hand.getCARDS_LIMIT() - hand.getCards().size();
        int[] hitted = new int[missing];
        Arrays.stream(hitted).forEach(card -> hitted[card] = oneMore);
        hand.addCards(deck.removeCards(hitted));
    }

    public void rankHands() {
        List<Integer> scores = new ArrayList<>();
        List<Integer> highCards = new ArrayList<>();
        for (Player player : players) {
            handScore = new HandScore(player.getHand());
            scores.add(handScore.getScore().getScoreValue());
            highCards.add(handScore.getHighCard());
        }
        int maxRank = Collections.max(scores);
        int minRank = Collections.min(scores);
        highCard = Collections.max(highCards);
        handWinner = players.get(scores.indexOf(maxRank));
        if (minRank == maxRank) {
            handWinner = players.get(scores.indexOf(highCard));
        }
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getPlayer(int player) {
        return players.get(player);
    }

    public int getHighCard() {
        return highCard;
    }

    public HandScore getHandScore() {
        return handScore;
    }

    public Deck getDeck() {
        return deck;
    }

    public Player getHandWinner() {
        return handWinner;
    }

    public String winnerToString() {
        return handWinner.toString();
    }

}