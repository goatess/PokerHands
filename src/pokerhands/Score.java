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
    private Ranking winnerRanking;
    private int highest = 0;
    private int[] ranksFrequencies;
    private int[] suitsFrequencies;
    private List<Card> cards;
    private List<Ranking> rankings = new ArrayList<>();
    private List<Integer> highCards = new ArrayList<>();

    Score(List<Player> players) { // constructor for multiple hand scoring
        for (Player player : players) {
            scoreHand(player);
            rankings.add(winnerRanking);
            highCards.add(highest);
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
        makeFrequencyTables();
        findHighestCard();
        rankHand(player);
    }

    private void makeRankings(List<Player> players) {
        int won = selectWinner(players);
        giveRankingsToPlayers(players, won);
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

    private void rankHand(Player player) {
        this.winnerRanking = Ranking.HIGH_CARD;
        int[] sortedHand = new int[cards.size()];
        cards.forEach(card -> sortedHand[cards.indexOf(card)] = card.getRankValue());
        Arrays.sort(sortedHand);

        if (isStraight(sortedHand)) {
            if (isFlush()) {
                winnerRanking = isRoyal(sortedHand) ? Ranking.ROYAL_FLUSH : Ranking.STRAIGHT_FLUSH;
            } else {
                winnerRanking = isRoyal(sortedHand) ? Ranking.ROYAL_STRAIGHT : Ranking.STRAIGHT;
            }
        } else {
            if (isFlush())
                winnerRanking = Ranking.FLUSH;
        }

        int pairsNumber = countPairs();
        switch (pairsNumber) {
            case PAIRED:
                if (winnerRanking.compareTo(Ranking.ONE_PAIR) < 0)
                    winnerRanking = Ranking.ONE_PAIR;
                break;
            case TWO_AND_TWO:
                if (winnerRanking.compareTo(Ranking.TWO_PAIR) < 0)
                    winnerRanking = Ranking.TWO_PAIR;
                break;
            case THREE_PAIRED:
                if (winnerRanking.compareTo(Ranking.THREE_OF_A_KIND) < 0)
                    winnerRanking = Ranking.THREE_OF_A_KIND;
                break;
            case FULL_PAIRED:
                if (winnerRanking.compareTo(Ranking.FULL_HOUSE) < 0)
                    winnerRanking = Ranking.FULL_HOUSE;
                break;
            case FOUR_PAIRED:
                if (winnerRanking.compareTo(Ranking.FOUR_OF_A_KIND) < 0)
                    winnerRanking = Ranking.FOUR_OF_A_KIND;
                break;
            default:
        }
        player.setRanking(winnerRanking);
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

    private void giveRankingsToPlayers(List<Player> players, int won) {
        boolean isTie;
        if (Collections.frequency(highCards, highest) > 1) {
            isTie = true;
        } else
            isTie = false;

        for (Player player : players) {
            if (players.indexOf(player) == won) {
                player.setWinner(true, isTie);
            } else {
                player.setWinner(false, isTie);
            }
        }
    }

    private int selectWinner(List<Player> players) {
        int won;
        List<Integer> rankingsValues = new ArrayList<>();
        rankings.forEach(ranking -> rankingsValues.add(ranking.getValue()));
        int maxRankScored = Collections.max(rankingsValues);
        boolean isTie = (Collections.frequency(rankingsValues, maxRankScored) > 1);

        if (isTie) {
            highest = Collections.max(highCards);
            won = highCards.indexOf(highest);
        } else {
            won = rankingsValues.indexOf(maxRankScored);
        }
        winner = players.get(won);
        winnerRanking = rankings.get(won);
        return won;
    }

    public int getHighest() {
        return highest;
    }

    public Player getWinner() {
        return winner;
    }

    public Ranking getWinnerRanking() {
        return winnerRanking;
    }

    public List<Ranking> getRankings() {
        return rankings;
    }

    @Override
    public String toString() {
        return winner.toString() + winnerRanking.toString();
    }
}
