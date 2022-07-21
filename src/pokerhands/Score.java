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
    private int[] ranksFrequencies;
    private int[] suitsFrequencies;
    private List<Card> cards;
    private List<Ranking> rankings = new ArrayList<>();
    private List<Integer> rankingsValues = new ArrayList<>();
    private List<Integer> highCards = new ArrayList<>();
    private List<Integer> highPairs = new ArrayList<>();

    Score(List<Player> players) { // constructor for multiple hand scoring
        for (Player player : players) {
            scoreHand(player);
        }
        makeRankings(players);
    }

    Score(Player player) { // constructor for 1 hand scoring
        winner = player;
        scoreHand(player);
    }

    private void scoreHand(Player player) {
        cards = player.getHand().getCards();
        if (cards.size() != 5) {
            throw new IllegalArgumentException("Hand incorrect size");
        }
        makeFrequencyTables(player);
        findHighestCard();
        rankHand(player);
    }

    private void findHighestCard() {
        int highest = 0;
        for (Card card : cards) {
            if (card.getRankPoints() >= highest) {
                highest = card.getRankPoints();
            }
        }
        highCards.add(highest);
    }

    private void makeFrequencyTables(Player player) {
        ranksFrequencies = new int[Rank.values().length];
        suitsFrequencies = new int[Suit.values().length];
        for (Card card : cards) {
            ranksFrequencies[card.getRankValue()]++;
            suitsFrequencies[card.getSuitValue()]++;
        }

    }

    private int countPairs() {
        int pairedValue = 0;
        int pairs = 0;
        for (int f = 0; f < ranksFrequencies.length; f++) {
            if (ranksFrequencies[f] == 4) {
                pairs = FOUR_PAIRED;
                pairedValue = f;
            } else if (ranksFrequencies[f] > 1) {
                pairs += ranksFrequencies[f];
                pairedValue = f;
            }
        }
        highPairs.add(pairedValue);
        return pairs;
    }

    private void rankHand(Player player) {
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
        player.setRanking(ranking);
        rankings.add(ranking);
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

    private void makeRankings(List<Player> players) {
        int won = -1;
        rankings.forEach(ranking -> rankingsValues.add(ranking.getValue()));
        players.forEach(player -> player.setWinner(false, false));
        int maxRankScored = Collections.max(rankingsValues);
        int highest = Collections.max(highCards);
        int highestPair = Collections.max(highPairs);

        if (Collections.frequency(rankingsValues, maxRankScored) == 1) {
            won = rankingsValues.indexOf(maxRankScored);
            players.get(won).setWinner(true, false);

        } else if (Collections.frequency(highPairs, highestPair) == 1) {
            won = highPairs.indexOf(highestPair);
            players.get(won).setWinner(true, false);

        } else if (Collections.frequency(highCards, highest) == 1) {
            won = highCards.indexOf(highest);
            players.get(won).setWinner(true, false);

        } else {
            for (Player player : players) {
                if (player.getHandRanking().getValue() == maxRankScored) {
                    player.setWinner(true, true);
                }
            }
        }
        winner = players.get(won);
        ranking = rankings.get(won);
    }

    public Player getWinner() {
        return winner;
    }

    public Ranking getRanking() {
        return Collections.max(rankings);
    }

    public int getHighest() {
        return Collections.max(highCards);
    }

    @Override
    public String toString() {
        return winner + ranking.toString();
    }
}
