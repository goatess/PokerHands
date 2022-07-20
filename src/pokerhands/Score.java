import java.util.Arrays;
import java.util.List;

public class Score {
    public static final int PAIRED = 2;
    public static final int TWO_PAIRED = 4;
    public static final int THREE_PAIRED = 3;
    public static final int FULL_PAIRED = 5;
    public static final int FOUR_PAIRED = 6;
    private int highest = 0;
    private Ranking ranking = Ranking.HIGH_CARD;
    private List<Card> cards;
    private int[] ranksFrequencies = new int[Rank.values().length];
    private int[] suitsFrequencies = new int[Suit.values().length];

    Score(Hand hand) {
        this.cards = hand.getCards();

        if (cards.size() != 5) {
            throw new IllegalArgumentException("Hand incorrect size");
        }
        
        makeFrequencyTables();
        findHighestCard();
        rankHand();
    }

    private void findHighestCard() {
        for (Card card : cards) {
            if (card.getRankPoints() >= highest) {
                highest = card.getRankPoints();
            }
        }
    }

    private void makeFrequencyTables() {
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
        int[] sortedHand = new int[cards.size()];
        ranking = Ranking.HIGH_CARD;
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
            case TWO_PAIRED:
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

    public Ranking getRanking() {
        return ranking;
    }

    public int getHighest() {
        return highest;
    }
}
