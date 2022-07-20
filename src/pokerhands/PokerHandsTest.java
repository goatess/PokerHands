import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class PokerHandsTest {
    @Test
    public void there_are_52_cards_in_deck() {
        Deck testDeck = new Deck();
        final int CARDS_IN_DECK = testDeck.getCardsNumber();
        int actualSize = testDeck.getCards().size();
        assertEquals(CARDS_IN_DECK, actualSize);
    }

    @Test
    public void each_card_gets_a_suit_and_rank() {
        final String CARD = "2H";
        Deck deck = new Deck();
        String actualValue = deck.getCard(2).toString();
        int actualCardFrequency = Collections.frequency(deck.getCards(), deck.getCard(2));
        assertEquals(1, actualCardFrequency);
        assertEquals(CARD, actualValue);
    }

    @Test
    public void each_card_gets_its_own_suit_and_rank() {
        final String CARD = "AS";
        Deck deck = new Deck();
        String actualValue = deck.getCard(51).toString();
        int actualCardFrequency = Collections.frequency(deck.getCards(), deck.getCard(51));
        assertEquals(1, actualCardFrequency);
        assertEquals(CARD, actualValue);
    }

    @Test
    public void full_cards_bundle_values_are_displayed_correctly() {
        final String CARD = "[2C, 2D, 2H, 2S, 3C, 3D, 3H, 3S, 4C, 4D, 4H, 4S, 5C, 5D, 5H, 5S, 6C, 6D, 6H, 6S, 7C, 7D, 7H, 7S, 8C, 8D, 8H, 8S, 9C, 9D, 9H, 9S, TC, TD, TH, TS, JC, JD, JH, JS, QC, QD, QH, QS, KC, KD, KH, KS, AC, AD, AH, AS]";
        Deck deck = new Deck();
        String actualValue = deck.toString();
        assertEquals(CARD, actualValue);
    }

    @Test
    public void there_are_only_5_cards_in_hand() {
        Deck cards = new Deck();
        Hand hand = new Hand(cards.getCards());
        final int CARDS_IN_HAND = hand.getCARDS_LIMIT();
        int actualSize = hand.getCards().size();
        assertEquals(CARDS_IN_HAND, actualSize);
    }

    @Test
    public void cards_added_to_hand_are_first_5_cards_of_deck() {
        final String CARD = "[2C, 2D, 2H, 2S, 3C]";
        Deck deck = new Deck();
        Hand hand = new Hand(deck.getCards());
        String actualValue = hand.toString();
        assertEquals(CARD, actualValue);
    }

    @Test
    public void cards_added_to_hand_are_deleted_from_deck() {
        Deck deck = new Deck();
        Hand hand; 
        final String CARDS = "[3D, 3H, 3S, 4C, 4D, 4H, 4S, 5C, 5D, 5H, 5S, 6C, 6D, 6H, 6S, 7C, 7D, 7H, 7S, 8C, 8D, 8H, 8S, 9C, 9D, 9H, 9S, TC, TD, TH, TS, JC, JD, JH, JS, QC, QD, QH, QS, KC, KD, KH, KS, AC, AD, AH, AS]";
        final int CARDS_IN_DECK;
        String actualCards;
        int actualCardsInDeck; 
        hand = new Hand(deck.drawCards());
        CARDS_IN_DECK = deck.getCardsNumber() - hand.getHandNumber();
        actualCards = deck.toString();
        actualCardsInDeck = deck.getCards().size();
        assertEquals(CARDS, actualCards);
        assertEquals(CARDS_IN_DECK, actualCardsInDeck);
    }

    @Test
    public void order_of_cards_is_different_when_deck_is_shuffled() {
        final String CARDS = "2C,2D,2H,2S,3C,3D,3H,3S,4C,4D,4H,4S,5C,5D,5H,5S,6C,6D,6H,6S,7C,7D,7H,7S,8C,8D,8H,8S,9C,9D,9H,9S,TC,TD,TH,TS,JC,JD,JH,JS,QC,QD,QH,QS,KC,KD,KH,KS,AC,AD,AH,AS,";
        Deck deck = new Deck();
        String cardsShuffled = deck.toString();
        deck.shuffleCards();
        assertTrue(cardsShuffled != CARDS);
    }

    @Test
    public void hand_cards_are_different_when_deck_is_shuffled() {
        final String CARDS = "2C,2D,2H,2S,3C,";
        Deck deck = new Deck();
        deck.shuffleCards();
        Hand hand = new Hand(deck.drawCards());
        String cardsShuffled = hand.toString();
        assertTrue(CARDS, cardsShuffled != CARDS);
    }

    @Test
    public void get_2_different_hands_from_the_same_deck() {
        Deck deck = new Deck();
        deck.shuffleCards();
        Hand hand = new Hand(deck.drawCards());
        final String CARDS_1 = hand.toString();
        Hand hand2 = new Hand(deck.drawCards());
        String cards2 = hand2.toString();
        assertTrue(CARDS_1, CARDS_1 != cards2);
    }

    @Test
    public void get_2_players_with_a_hand_of_cards_each_one() {
        Deck deck = new Deck();
        deck.shuffleCards();
        Player player1 = new Player(new Hand(deck.drawCards()), "Black");
        Player player2 = new Player(new Hand(deck.drawCards()), "Black");
        String player1Hand = player1.toString();
        String player2Hand = player2.toString();
        assertNotEquals(player1Hand, player2Hand);
    }

    @Test
    public void get_royal_flush_score() {
        final String HAND = "Black: [AS, KS, QS, JS, TS] Royal Flush";
        Deck deck = new Deck();
        Score handScore; 
        Player player = new Player(new Hand(), "Black");
        List<Card> cards = deck.drawCards(51, 47, 43, 39, 35);
        player.getHand().addCards(cards);
        handScore = new Score(player);
        assertEquals(Ranking.ROYAL_FLUSH, handScore.getRanking());
        assertEquals(HAND, handScore.toString());
    }

    @Test
    public void get_flush_score() {
        final String HAND = "Black: [2C, 4C, 5C, 7C, 9C] Flush";
        Deck deck = new Deck();
        Player player = new Player(new Hand(), "Black");
        Score handScore;
        List<Card> cards = deck.drawCards(0, 8, 12, 20, 28);
        player.getHand().addCards(cards);
        handScore = new Score(player);
        assertEquals(Ranking.FLUSH, handScore.getRanking());
        assertEquals(HAND, handScore.toString());
    }

    @Test
    public void get_straight_flush_score() {
        final String HAND = "Black: [2C, 3C, 4C, 5C, 6C] Straight Flush";
        Deck deck = new Deck();
        Player player = new Player(new Hand(), "Black");
        Score handScore;
        List<Card> cards = deck.drawCards(0, 4, 8, 12, 16);
        player.getHand().addCards(cards);
        handScore = new Score(player);
        assertEquals(Ranking.STRAIGHT_FLUSH, handScore.getRanking());
        assertEquals(HAND, handScore.toString());
    }

    @Test
    public void get_straight_score() {
        final String HAND = "Black: [2C, 3D, 4H, 5S, 6S] Straight";
        Deck deck = new Deck();
        Player player = new Player(new Hand(), "Black");
        Score handScore;
        List<Card> cards = deck.drawCards(0, 5, 10, 15, 19);
        player.getHand().addCards(cards);
        handScore = new Score(player);
        assertEquals(Ranking.STRAIGHT, handScore.getRanking());
        assertEquals(HAND, handScore.toString());
    }

    @Test
    public void get_royal_straight_score() {
        final String HAND = "Black: [TC, JC, QD, KH, AS] Royal Straight";
        Deck deck = new Deck();
        Player player = new Player(new Hand(), "Black");
        Score handScore;
        List<Card> cards = deck.drawCards(32, 36, 41, 46, 51);
        player.getHand().addCards(cards);
        handScore = new Score(player);
        assertEquals(Ranking.ROYAL_STRAIGHT, handScore.getRanking());
        assertEquals(HAND, handScore.toString());
    }

    @Test
    public void get_HIGH_CARD_score() {
        final String HAND = "Black: [2C, 4S, 7H, TD, AS] High Card";
        final int HIGH_CARD_POINTS = 11;
        Deck deck = new Deck();
        Player player = new Player(new Hand(), "Black");
        Score handScore;
        List<Card> cards = deck.drawCards(0, 11, 22, 33, 51);
        player.getHand().addCards(cards);
        handScore = new Score(player);
        assertEquals(Ranking.HIGH_CARD, handScore.getRanking());
        assertEquals(HAND, handScore.toString());
        assertEquals(HIGH_CARD_POINTS, handScore.getHighest());
    }

    @Test
    public void get_ONE_PAIR_score() {
        final String HAND = "Black: [2C, 2D, 4H, 5S, 6S] One Pair";
        Deck deck = new Deck();
        Player player = new Player(new Hand(), "Black");
        Score handScore;
        List<Card> cards = deck.drawCards(0, 1, 10, 15, 19);
        player.getHand().addCards(cards);
        handScore = new Score(player);
        assertEquals(Ranking.ONE_PAIR, handScore.getRanking());
        assertEquals(HAND, handScore.toString());
    }

    @Test
    public void get_TWO_PAIR_score() {
        final String HAND = "Black: [2C, 2D, 4H, 6H, 6S] Two Pairs";
        Deck deck = new Deck();
        Player player = new Player(new Hand(), "Black");
        Score handScore;
        List<Card> cards = deck.drawCards(0, 1, 10, 18, 19);
        player.getHand().addCards(cards);
        handScore = new Score(player);
        assertEquals(Ranking.TWO_PAIR, handScore.getRanking());
        assertEquals(HAND, handScore.toString());
    }

    @Test
    public void get_three_of_a_kind_score() {
        final String HAND = "Black: [2C, 2D, 2H, 5S, 6S] Three of a Kind";
        Deck deck = new Deck();
        Player player = new Player(new Hand(), "Black");
        Score handScore;
        List<Card> cards = deck.drawCards(0, 1, 2, 15, 19);
        player.getHand().addCards(cards);
        handScore = new Score(player);
        assertEquals(Ranking.THREE_OF_A_KIND, handScore.getRanking());
        assertEquals(HAND, handScore.toString());
    }

    @Test
    public void get_four_of_a_kind_score() {
        final String HAND = "Black: [AS, AH, AD, AC, KS] Four of a Kind";
        Deck deck = new Deck();
        Player player= new Player(new Hand(), "Black");
        Score handScore;
        List<Card> cards = deck.drawCards(51, 50, 49, 48, 47);
        player.getHand().addCards(cards);
        handScore = new Score(player);
        assertEquals(Ranking.FOUR_OF_A_KIND, handScore.getRanking());
        assertEquals(HAND, handScore.toString());
    }

    @Test
    public void get_full_house_score() {
        final String HAND = "Black: [AS, AH, AD, KS, KH] Full House";
        Deck deck = new Deck();
        Score handScore;
        Player player= new Player(new Hand(), "Black");
        List<Card> cards = deck.drawCards(51, 50, 49, 47, 46);
        player.getHand().addCards(cards);
        handScore = new Score(player);
        assertEquals(Ranking.FULL_HOUSE, handScore.getRanking());
        assertEquals(HAND, handScore.toString());
    }

    @Test
    public void rank_hand_and_get_winning_player() {
        final String WINNER = "Winner: [2C, 4C, 5C, 7C, 9C] Flush";
        String actualWinner = "Loser: [5D, 2S, 7C, TS, 9D] High Card"; 
        Deck deck = new Deck();
        Score score;
        List<Player> players = new ArrayList<>();        
        players.add(new Player(new Hand(), "Winner"));
        players.add(new Player(new Hand(), "Loser"));
        Player winner = players.get(0);
        Player loser = players.get(1);
        winner.putCardsToHand(deck.drawCards(0, 8, 12, 20, 28));
        loser.putCardsToHand(deck.drawCards(13, 3, 20, 35, 29));
        score = new Score(players);
        actualWinner = score.toString();
        assertEquals(WINNER, actualWinner);
    }
}