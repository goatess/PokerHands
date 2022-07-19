import java.util.Arrays;
import java.util.List;

public class HandScore {

    public static final int ONE_PAIR = 2;
    public static final int TWO_PAIR = 4;
    public static final int THREE_OF_A_KIND = 3;
    public static final int FULL_HOUSE = 5;
    public static final int FOUR_OF_A_KIND = 6;
    private PokerHand score;
    private int highCardPosition;
    private List<Card> cards;
    private int[] rankFrequency = new int[15];
    private int[] suitFrequency = new int[4];

    public HandScore(Hand hand) {
        this.cards = hand.getCards();
        score = PokerHand.HIGH_CARD;

        if (cards.size() != 5) {
            throw new IllegalArgumentException("Hand incorrect size");
        }
        elaborateFrequencyTable();
        findHighCard();
        scoreHand();
    }

    private void elaborateFrequencyTable() {
        for (Card card : cards) {
            rankFrequency[card.getRankValue()]++;
            suitFrequency[card.getSuitValue()]++;
        }
    }

    private void scoreHand() {
        int[] sorted = sortRankArray();
        score = PokerHand.HIGH_CARD;

        if (isStraight(sorted)) {
            if (isFlush()) {
                score = isRoyal(sorted) ? PokerHand.ROYAL_FLUSH : PokerHand.STRAIGHT_FLUSH;
            } else {
                score = isRoyal(sorted) ? PokerHand.ROYAL_STRAIGHT : PokerHand.STRAIGHT;
            }
        } else {
            if (isFlush())
                score = PokerHand.FLUSH;
        }

        int pairsNumber = countPairs();
        switch (pairsNumber) {
            case ONE_PAIR:
                if (score.compareTo(PokerHand.ONE_PAIR) < 0)
                    score = PokerHand.ONE_PAIR;
                break;
            case TWO_PAIR:
                if (score.compareTo(PokerHand.TWO_PAIR) < 0)
                    score = PokerHand.TWO_PAIR;
                break;
            case THREE_OF_A_KIND:
                if (score.compareTo(PokerHand.THREE_OF_A_KIND) < 0)
                    score = PokerHand.THREE_OF_A_KIND;
                break;
            case FULL_HOUSE:
                if (score.compareTo(PokerHand.FULL_HOUSE) < 0)
                    score = PokerHand.FULL_HOUSE;
                break;
            case FOUR_OF_A_KIND:
                if (score.compareTo(PokerHand.FOUR_OF_A_KIND) < 0)
                    score = PokerHand.FOUR_OF_A_KIND;
                break;
            default:
        }
    }

    private int countPairs() {
        int pairCount = 0;
        for (int each : rankFrequency) {
            if (each == 4) {
                pairCount = FOUR_OF_A_KIND;
                break;
            } else if (each > 1) {
                pairCount += each;
            }
        }
        return pairCount;
    }

    private boolean isFlush() {
        for (int each : suitFrequency) {
            if (each == cards.size())
                return true;
        }
        return false;
    }

    private boolean isStraight(int[] sorted) {
        boolean isStraight = true;
        for (int i = 1; i < cards.size(); i++) {
            if (sorted[i] - sorted[i-1] > 1)
                isStraight = false;
        }
        return isStraight;
    }

    private boolean isRoyal(int[] sorted) {
        for (int i : sorted) {
            if (i == 14) {
                return true;
            }
        }
        return false;
    }

    private int[] sortRankArray() {
        int[] sorted = new int[5];
        int i = 0;
        for (Card card : cards) {
            sorted[i++] = card.getRankValue();
        }
        Arrays.sort(sorted);
        return sorted;
    }

    private void findHighCard() {
        int max = -1;
        for (Card card : cards) {
            if (card.getRankValue() > max) {
                max = card.getRankValue();
                highCardPosition = cards.indexOf(card);
            }
        }
    }

    public PokerHand getScore() {
        return score;
    }

    public int getHighCard() {
        return highCardPosition;
    }
}