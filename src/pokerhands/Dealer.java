import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dealer {
    private int playersNumber = 2;
    private int rounds;
    private Deck deck;
    private Score score;
    private List<Player> players;

    Dealer(int playersNumber, int rounds) { // automated constructor

        this.rounds = rounds;
        setPlayersNumber(playersNumber);
        players = new ArrayList<>();
        setUpGame();
    }

    Dealer(String name1, String name2) { // only creates 2 players with a hand each and a deck of cards
        deck = new Deck();
        players = new ArrayList<>();
        players.add(new Player(new Hand(), name1));
        players.add(new Player(new Hand(), name2));

    }

    private void setPlayersNumber(int playersNumber) {
        if (playersNumber > 1 && playersNumber < 15) {
            this.playersNumber = playersNumber;
        } else
            throw new IllegalStateException("Incorrect player number (set a number between 2 and 14)");
    }

    private void setUpGame() {
        deck = new Deck();
        createPlayers();
        rounds = 0;
        do {
            rounds++;
            dealCards();
            score = new Score(players);
            this.toString();
            cleanBoard();
        } while (rounds < 5);
    }

    private void dealCards() {
        deck.shuffleCards();
        players.forEach(player -> player.setHand(new Hand()));
        players.forEach(player -> dealHand(player.getHand()));
    }

    private void createPlayers() {
        for (int i = 0; i < playersNumber; i++) {
            players.add(new Player("player" + i));
        }
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

    public Score getScore() {
        return score;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    @Override
    public String toString() {
        String playersRanks = "";
        String playerRank = "";
        for (Player player : players) {
            playerRank = "\n" + player.toString() + player.getHandRanking().toString();
            if (player.isWinner()) {
                if(player.isTie()){
                    playerRank += " (TIE)";
                }else playerRank += " (WINNER)";
            }
            playersRanks += playerRank;
            
        }
        System.out.println(playersRanks);
        return playersRanks;
    }
}
