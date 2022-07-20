import java.util.Arrays;
import java.util.List;

public class Score {
    public static final int ONE_PAIR = 2;
    public static final int TWO_PAIR = 4;
    public static final int THREE_OF_A_KIND = 3;
    public static final int FULL_HOUSE = 5;
    public static final int FOUR_OF_A_KIND = 6;

    private Ranking ranking;
    private int highCard;
    private List<Card> cards;
    private int[] rankFrequency = new int[13];
    private int[] suitFrequency = new int[4];

    Score(Hand hand) {
        this.cards = hand.getCards();
        ranking = Ranking.HIGH_CARD;

        if (cards.size() != 5) {
            throw new IllegalArgumentException("Hand incorrect size");
        }
        makeFrequencyTables();
        findHighCard();
        rankHand();
    }

    private void findHighCard() {
        int max = -1;
        for (Card card : cards) {
            if (card.getRank().getPoints() > max) {
                max = card.getRank().getPoints();
                highCard = cards.indexOf(card);
            }
        }
    }

    private void makeFrequencyTables() {
        for (Card card : cards) {
            rankFrequency[card.getRankValue()]++;
            suitFrequency[card.getSuitValue()]++;
        }
    }

    private void rankHand() {
        int[] sorted = new int[cards.size()];
        ranking = Ranking.HIGH_CARD;
        cards.forEach(card -> sorted[cards.indexOf(card)] = card.getRankValue());
        Arrays.sort(sorted);

        if (isStraight(sorted)) {
            if (isFlush()) {
                ranking = isRoyal(sorted) ? Ranking.ROYAL_FLUSH : Ranking.STRAIGHT_FLUSH;
            } else {
                ranking = isRoyal(sorted) ? Ranking.ROYAL_STRAIGHT : Ranking.STRAIGHT;
            }
        } else {
            if (isFlush())
                ranking = Ranking.FLUSH;
        }

        int pairsNumber = countPairs();
        switch (pairsNumber) {
            case ONE_PAIR:
                if (ranking.compareTo(Ranking.ONE_PAIR) < 0)
                    ranking = Ranking.ONE_PAIR;
                break;
            case TWO_PAIR:
                if (ranking.compareTo(Ranking.TWO_PAIR) < 0)
                    ranking = Ranking.TWO_PAIR;
                break;
            case THREE_OF_A_KIND:
                if (ranking.compareTo(Ranking.THREE_OF_A_KIND) < 0)
                    ranking = Ranking.THREE_OF_A_KIND;
                break;
            case FULL_HOUSE:
                if (ranking.compareTo(Ranking.FULL_HOUSE) < 0)
                    ranking = Ranking.FULL_HOUSE;
                break;
            case FOUR_OF_A_KIND:
                if (ranking.compareTo(Ranking.FOUR_OF_A_KIND) < 0)
                    ranking = Ranking.FOUR_OF_A_KIND;
                break;
            default:
        }
    }

    private boolean isFlush() {
        for (int each : suitFrequency) {
            if (each == cards.size())
                return true;
        }
        return false;
    }

    private boolean isStraight(int[] sorted) {
        for (int i = 1; i < cards.size(); i++) {
            if (sorted[i] - sorted[i-1] > 1)
                return false;
        }
        return true;
    }

    private boolean isRoyal(int[] sorted) {
        for (int i : sorted) {
            if (i == Rank.ACE.getValue()) {
                return true;
            }
        }
        return false;
    }

    private int countPairs() {
        int pairCount = 0;
        int fourTimes = 4;
        for (int each : rankFrequency) {
            if (each == fourTimes) {
                pairCount = FOUR_OF_A_KIND;
                break;
            } else if (each > 1) {
                pairCount += each;
            }
        }
        return pairCount;
    }

    public Ranking getScore() {
        return ranking;
    }

    public int getHighCard() {
        return highCard;
    }
}