public class Player {
    private Hand hand;
    private String name;
    
    Player() {
        hand = new Hand();
    }

    Player(Hand hand) {
        this.hand = hand;
    }

    Player(String name) {
        this.hand = new Hand();
        this.name = name;
    }

    Player(Hand hand, String name) {
        this.hand = hand;
        this.name = name;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return name + ": " + hand.getCards().toString();
    }
}
