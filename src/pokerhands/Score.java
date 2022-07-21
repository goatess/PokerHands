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
    private int[] ranksFrequencies;
    private int[] suitsFrequencies;
    private Player rankedPlayer;
    private List<Card> cards;
    private List<Ranking> rankings = new ArrayList<>();
    private List<Integer> rankingsValues = new ArrayList<>();
    private List<Integer> highCards = new ArrayList<>();       
    private List<Integer> highPairs = new ArrayList<>();        

    Score(List<Player> players) {   // constructor for multiple hand scoring
        for (Player player : players) {
            scoreHand(player);
        }
        compareHands(players);
    }

    Score(Player player) {          // 1 hand scoring constructor
        scoreHand(player);
    }
    
    private void scoreHand(Player player) {
        rankedPlayer = player;
        cards = player.getHand().getCards();
        if (cards.size() != 5) {
            throw new IllegalArgumentException("Hand incorrect size");
        }
        makeFrequencyTables();
        findHighestCard();
        assignHandRanking();
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

    private void makeFrequencyTables() {
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
                pairedValue = f;
                pairs = FOUR_PAIRED;
            } else if (ranksFrequencies[f] > 1) {
                pairedValue = f;
                pairs += ranksFrequencies[f];
            }
        }
        highPairs.add(pairedValue);
        return pairs;
    }

    private void assignHandRanking() {
        int[] sortedHand = new int[cards.size()];
        cards.forEach(card -> sortedHand[cards.indexOf(card)] = card.getRankValue());
        Arrays.sort(sortedHand);
        
        Ranking ranking = Ranking.HIGH_CARD;
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
        rankedPlayer.setRanking(ranking);
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

    private void compareHands(List<Player> players) {
        rankings.forEach(ranking -> rankingsValues.add(ranking.getValue()));
        players.forEach(player -> player.setWinner(false, false));
        
        int highestRanking = Collections.max(rankingsValues);
        int highestCard = Collections.max(highCards);
        int highestPair = Collections.max(highPairs);
        boolean isTie = false;
        int win = -1;

        if (Collections.frequency(rankingsValues, highestRanking) == 1)
            win = rankingsValues.indexOf(highestRanking);
        else if (Collections.frequency(highPairs, highestPair) == 1)
            win = highPairs.indexOf(highestPair);
        else if (Collections.frequency(highCards, highestCard) == 1)
            win = highCards.indexOf(highestCard);
        else isTie = true;

        if (isTie) {                                  
            for (int tie = 0; tie < players.size(); tie++) {
                if (players.get(tie).getHandRanking().getValue() == highestRanking) {
                    players.get(tie).setWinner(true, true);
                    rankedPlayer = players.get(tie);
                }
            }
        } else {                                       
            players.get(win).setWinner(true, false);
            rankedPlayer = players.get(win);
        }
    }

    public Player getWinner() {
        return rankedPlayer;
    }

    public Ranking getMaxRanking() {
        return Collections.max(rankings);
    }

    public int getHighestCard() {
        return Collections.max(highCards);
    }
    public int getMaxRankingValues() {
        return Collections.max(rankingsValues);
    }

    public int getHighestPair() {
        return Collections.max(highPairs);
    }

    @Override
    public String toString() {
        return rankedPlayer + getMaxRanking().toString();
    }
}
