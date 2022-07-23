import java.util.List;

public class Player {
    private Hand hand;
    private String name;
    private Ranking handRanking;
    private boolean isWinner;
    private boolean isTie;

    Player(Hand hand) {
        this.hand = hand;
    }

    Player(Hand hand, String name) {
        this.hand = hand;
        this.name = name;
    }

    Player(String name) {
        this.hand = new Hand();
        this.name = name;
    }

    public Player() {
    }

    public void putCardsToHand(List<Card> cards) {
        hand.addCards(cards);
    }

    public void discard() {
        int[]discarded = new int[]{-1,-1,-1,-1,-1};
        for (int card : discarded) {
            if (card != -1) {
                hand.removeCard(card);
            }
        }
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    @Override
    public String toString() {
        String playerString = name + ": " + hand.toString();
        System.out.println(playerString);
        return playerString;
    }

    public void setWinner(boolean isWinner, boolean isTie) {
        this.isWinner = isWinner;
        this.isTie = isTie;
    }

    public void setRanking(Ranking handRanking) {
        this.handRanking = handRanking;
    }

    public Ranking getHandRanking() {
        return handRanking;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public boolean isTie() {
        return isTie;
    }
}
