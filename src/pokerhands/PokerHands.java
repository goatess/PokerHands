import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PokerHands {
    private Deck deck;
    private Score score;
    private int highCard;
    private int playersNum = 2;
    private List<Player> players;
    private Player winner;
    private Hand winnerHand;
    private int rounds;

    PokerHands() {
        this.deck = new Deck();
        players = new ArrayList<>();
    }

    PokerHands(int playersNum, int rounds) {
        this.rounds = rounds;
        this.playersNum = playersNum;
        this.deck = new Deck();
        players = new ArrayList<>();
        setUpGame(playersNum);
    }

    private void setUpGame(int playersNum) {
        createPlayers();
        rounds = 0;
        do {
            rounds++;
            deck.shuffleDeck();
            dealHands();
            rankHands();
            cleanBoard();
        } while (rounds < 5);
    }

    private void createPlayers() {
        for (int i = 0; i < playersNum; i++) {
            players.add(new Player(new Hand()));
        }
    }

    public void dealHands() {
        players.forEach(player -> dealHand(player.getHand()));
    }

    private void dealHand(Hand hand) {
        int oneMoreCard = 0;
        int[] drawn = new int[hand.getCARDS_LIMIT()];
        Arrays.stream(drawn).forEach(card -> drawn[card] = oneMoreCard);
        hand.addCards(deck.drawCards());
    }

    private void cleanBoard() {
        players.forEach(player -> player.getHand().discard());
        deck.collectCards();
    }

    public void rankHands() {
        List<Integer> scores = new ArrayList<>();
        List<Integer> highCards = new ArrayList<>();

        for (Player player : players) {
            score = new Score(player.getHand());
            scores.add(score.getScore().getScore());
            highCards.add(score.getHighCard());
        }
        int maxScore = Collections.max(scores);
        int minScore = Collections.min(scores);
        highCard = Collections.max(highCards);

        if (minScore == maxScore) {
            winner = players.get(scores.indexOf(highCard));
        } else
            winner = players.get(scores.indexOf(maxScore));
        winnerHand = winner.getHand();
    }

    public Player getPlayer(int player) {
        return players.get(player);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getPlayersNum() {
        return playersNum;
    }

    public int getRounds() {
        return rounds;
    }

    public int getHighCard() {
        return highCard;
    }

    public Score getHandScore() {
        return score;
    }

    public Deck getDeck() {
        return deck;
    }

    public Player getWinner() {
        return winner;
    }

    @Override
    public String toString() {
        return winner.toString();
    }

    public Hand getWinnerHand() {
        return winnerHand;
    }
}