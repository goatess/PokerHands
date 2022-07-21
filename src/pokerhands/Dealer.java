import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dealer {
    private int playersNumber = 4;
    private int round;
    private int roundsNumber;
    private Deck deck;
    private Score score;
    private List<Player> players;

    Dealer(int playersNumber, int roundsNumber) { // automated constructor
        deck = new Deck();
        setRounds(roundsNumber);
        setPlayersNumber(playersNumber);
        createPlayers();
        setUpGame();
    }

    Dealer(String name1, String name2) { // only creates 2 players with a hand each and a deck of cards
        deck = new Deck();
        players = new ArrayList<>();
        players.add(new Player(new Hand(), name1));
        players.add(new Player(new Hand(), name2));

    }

    private void setUpGame() {
        round = 0;
        do {
            round++;
            dealHands();
            discardRound();
            score = new Score(players);
            this.toString();
            cleanBoard();
        } while (round < roundsNumber);
    }

    private void createPlayers() {
        players = new ArrayList<>();
        for (int i = 0; i < playersNumber; i++) {
            players.add(new Player("player" + i));
        }
    }

    private void dealHands() {
        deck.shuffleCards();
        players.forEach(player -> player.setHand(new Hand()));
        players.forEach(player -> dealCards(player.getHand()));

    }

    private void dealCards(Hand hand) {
        int oneMoreCard = 0;
        int[] drawn = new int[hand.getCardsMissing()];
        Arrays.stream(drawn).forEach(card -> drawn[card] = oneMoreCard);
        hand.addCards(deck.drawCards());
    }

    private void cleanBoard() {
        players.forEach(player -> player.getHand().discardHand());
        deck.collectCards();
    }

    private void discardRound() {
        players.forEach(player -> player.discard());
        players.forEach(player -> dealCards(player.getHand()));
        players.forEach(player -> player.getHand().toString());
    }

    private void setRounds(int roundsNumber) {
        if (roundsNumber > 0 && roundsNumber < 20) {
            this.roundsNumber = roundsNumber;
        } else
            throw new IllegalStateException("Incorrect number of rounds (set a number between 1 and 20)");
    }

    private void setPlayersNumber(int playersNumber) {
        if (playersNumber > 1 && playersNumber < 15) {
            this.playersNumber = playersNumber;
        } else
            throw new IllegalStateException("Incorrect number of players (set a number between 2 and 14)");
    }

    @Override
    public String toString() {
        String playersRanks = "";
        String playerRank = "";
        for (Player player : players) {
            playerRank = "\n" + player.toString() + player.getHandRanking().toString();
            if (player.isWinner()) {
                if (player.isTie()) {
                    playerRank += " (TIE)";
                } else
                    playerRank += " (WINNER)";
            }
            playersRanks += playerRank;

        }
        System.out.println(playersRanks);
        return playersRanks;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Score getScore() {
        return score;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setScore(Score score) {
        this.score = score;
    }
}
