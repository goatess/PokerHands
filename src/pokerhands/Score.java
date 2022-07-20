import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Score {
    public static final int PAIRED = 2;
    public static final int TWO_AND_TWO = 4;
    public static final int THREE_PAIRED = 3;
    public static final int FULL_PAIRED = 5;
    public static final int FOUR_PAIRED = 6;
    private Player winner;
    private Ranking ranking;
    private int highest = 0;
    private int[] ranksFrequencies;
    private int[] suitsFrequencies;
    private List<Card> cards;
    private List<Ranking> rankings = new ArrayList<>();
    private List<Integer> highCards = new ArrayList<>();

    Score(List<Player> players) {
        for (Player player : players) {

            scoreHand(player.getHand());
            rankings.add(ranking);
            highCards.add(highest);
        }
        scoreHands(players);
    }

    Score(Player player) {
        this.winner = player;
        scoreHand(player.getHand());
    }

    private void scoreHand(Hand hand) {
        this.cards = hand.getCards();

        if (cards.size() != 5) {
            throw new IllegalArgumentException("Hand incorrect size");
        }

        makeFrequencyTables();
        findHighestCard();
        rankHand();
    }

    private void findHighestCard() {
        this.highest = 0;
        for (Card card : cards) {
            if (card.getRankPoints() >= highest) {
                highest = card.getRankPoints();
            }
        }
    }

    private void makeFrequencyTables() {
        ranksFrequencies = new int[Rank.values().length];
        suitsFrequencies = new int[Suit.values().length];
        for (Card card : cards) {
            ranksFrequencies[card.getRankValue()]++;
            suitsFrequencies[card.getSuitValue()]++;
        }
    }

    private int countPairs() {
        int pairs = 0;
        for (int rankFreq : ranksFrequencies) {
            if (rankFreq == 4) {
                pairs = FOUR_PAIRED;
            } else if (rankFreq > 1) {
                pairs += rankFreq;
            }
        }
        return pairs;
    }

    private void rankHand() {
        this.ranking = Ranking.HIGH_CARD;
        int[] sortedHand = new int[cards.size()];
        cards.forEach(card -> sortedHand[cards.indexOf(card)] = card.getRankValue());
        Arrays.sort(sortedHand);

        if (isStraight(sortedHand)) {
            if (isFlush()) {
                ranking = isRoyal(sortedHand) ? Ranking.ROYAL_FLUSH : Ranking.STRAIGHT_FLUSH;
            } else {
                ranking = isRoyal(sortedHand) ? Ranking.ROYAL_STRAIGHT : Ranking.STRAIGHT;
            }
        } else {
            if (isFlush())
                ranking = Ranking.FLUSH;
        }

        int pairsNumber = countPairs();
        switch (pairsNumber) {
            case PAIRED:
                if (ranking.compareTo(Ranking.ONE_PAIR) < 0)
                    ranking = Ranking.ONE_PAIR;
                break;
            case TWO_AND_TWO:
                if (ranking.compareTo(Ranking.TWO_PAIR) < 0)
                    ranking = Ranking.TWO_PAIR;
                break;
            case THREE_PAIRED:
                if (ranking.compareTo(Ranking.THREE_OF_A_KIND) < 0)
                    ranking = Ranking.THREE_OF_A_KIND;
                break;
            case FULL_PAIRED:
                if (ranking.compareTo(Ranking.FULL_HOUSE) < 0)
                    ranking = Ranking.FULL_HOUSE;
                break;
            case FOUR_PAIRED:
                if (ranking.compareTo(Ranking.FOUR_OF_A_KIND) < 0)
                    ranking = Ranking.FOUR_OF_A_KIND;
                break;
            default:
        }
    }

    private boolean isFlush() {
        for (int suitFreq : suitsFrequencies) {
            if (suitFreq == cards.size())
                return true;
        }
        return false;
    }

    private boolean isStraight(int[] sortedHand) {
        for (int card = 1; card < cards.size(); card++) {
            if (sortedHand[card] - sortedHand[card - 1] > 1)
                return false;
        }
        return true;
    }

    private boolean isRoyal(int[] sortedHand) {
        for (int card : sortedHand) {
            if (card == Rank.ACE.getValue()) {
                return true;
            }
        }
        return false;
    }

    int player;
    private void scoreHands(List<Player> players) {
        List<Integer> rankingsValues = new ArrayList<>();
        rankings.forEach(ranking -> rankingsValues.add(ranking.getValue()) );
        
        int maxScore = Collections.max(rankingsValues);
        int minScore = Collections.min(rankingsValues);
        highest = Collections.max(highCards);
        
        if (minScore == maxScore) {
            player = highCards.indexOf(highest);   
        } else {
            player = rankingsValues.indexOf(maxScore); 
        }
        winner = players.get(player);
        ranking = rankings.get(player);
    }

    public Ranking getRanking() {
        return ranking;
    }

    public int getHighest() {
        return highest;
    }

    public Player getWinner() {
        return winner;
    }

    @Override
    public String toString() {
        return winner.toString() + " " + ranking.toString();
    }
}
