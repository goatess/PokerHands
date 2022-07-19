import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

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
    public void each_card_has_a_suit_and_rank() {
        final String CARD = "2H";
        Deck deck = new Deck();
        String actualValue = deck.getCards().get(2).toString();
        assertEquals(CARD, actualValue);
    }

    @Test
    public void each_card_has_its_own_suit_and_rank() {
        final String CARD = "AS";
        Deck deck = new Deck();
        String actualValue = deck.getCards().get(51).toString();
        assertEquals(CARD, actualValue);
    }

    @Test
    public void full_card_bundle_values() {
        final String CARD = "[2C, 2D, 2H, 2S, 3C, 3D, 3H, 3S, 4C, 4D, 4H, 4S, 5C, 5D, 5H, 5S, 6C, 6D, 6H, 6S, 7C, 7D, 7H, 7S, 8C, 8D, 8H, 8S, 9C, 9D, 9H, 9S, TC, TD, TH, TS, JC, JD, JH, JS, QC, QD, QH, QS, KC, KD, KH, KS, AC, AD, AH, AS]";
        Deck deck = new Deck();
        String actualValue = deck.toString();
        assertEquals(CARD, actualValue);
    }

    @Test
    public void there_are_5_cards_in_hand() {
        Deck cards = new Deck();
        Hand hand = new Hand(cards.getCards());
        final int CARDS_IN_HAND = hand.getHAND_LIMIT();
        int actualSize = hand.getCards().size();
        assertEquals(CARDS_IN_HAND, actualSize);
    }

    @Test
    public void add_to_hand_first_5_cards_of_deck() {
        final String CARD = "[2C, 2D, 2H, 2S, 3C]";
        Deck deck = new Deck();
        Hand hand = new Hand(deck.getCards());
        String actualValue = hand.toString();
        assertEquals(CARD, actualValue);
    }

    @Test
    public void cards_added_to_hand_are_deleted_from_deck() {
        final String CARD = "[3D, 3H, 3S, 4C, 4D, 4H, 4S, 5C, 5D, 5H, 5S, 6C, 6D, 6H, 6S, 7C, 7D, 7H, 7S, 8C, 8D, 8H, 8S, 9C, 9D, 9H, 9S, TC, TD, TH, TS, JC, JD, JH, JS, QC, QD, QH, QS, KC, KD, KH, KS, AC, AD, AH, AS]";
        Deck deck = new Deck();
        int[] indices = { 0, 0, 0, 0, 0 };
        Hand hand = new Hand(deck.removeCards(indices));
        final int CARDS_IN_DECK = deck.getCardsNumber() - hand.getHandNumber();
        String actualCards = deck.toString();
        int actualCardsInDeck = deck.getCards().size();
        assertEquals(CARD, actualCards);
        assertEquals(CARDS_IN_DECK, actualCardsInDeck);
    }

    @Test
    public void shuffle_deck() {
        final String CARDS = "2C,2D,2H,2S,3C,3D,3H,3S,4C,4D,4H,4S,5C,5D,5H,5S,6C,6D,6H,6S,7C,7D,7H,7S,8C,8D,8H,8S,9C,9D,9H,9S,TC,TD,TH,TS,JC,JD,JH,JS,QC,QD,QH,QS,KC,KD,KH,KS,AC,AD,AH,AS,";
        Deck deck = new Deck();
        deck.shuffleDeck();
        String cardsShuffled = deck.toString();
        assertTrue(CARDS, cardsShuffled != CARDS);
    }

    @Test
    public void hand_gets_shuffled_first_5_cards_of_deck() {
        final String CARDS = "2C,2D,2H,2S,3C,";
        Deck deck = new Deck();
        deck.shuffleDeck();
        int[] indices = { 0, 0, 0, 0, 0 };
        Hand hand = new Hand(deck.removeCards(indices));
        String cardsShuffled = hand.toString();
        assertTrue(CARDS, cardsShuffled != CARDS);
    }

    @Test
    public void get_2_different_hands_from_the_same_deck() {
        Deck deck = new Deck();
        deck.shuffleDeck();
        int[] indices = { 0, 0, 0, 0, 0 };
        Hand hand = new Hand(deck.removeCards(indices));
        final String CARDS_1 = hand.toString();
        Hand hand2 = new Hand(deck.removeCards(indices));
        String cards2 = hand2.toString();
        assertTrue(CARDS_1, CARDS_1 != cards2);
    }

    @Test
    public void get_2_players_with_a_hand_of_cards_each_one() {
        Deck deck = new Deck();
        deck.shuffleDeck();
        int[] indices = { 0, 0, 0, 0, 0 };
        Player player1 = new Player(new Hand(deck.removeCards(indices)), "Black");
        Player player2 = new Player(new Hand(deck.removeCards(indices)), "White");
        String player1Hand = player1.toString();
        String player2Hand = player2.toString();
        assertNotEquals(player1Hand, player2Hand);
    }

    @Test
    public void get_royal_flush_score() {
        Deck deck = new Deck();
        Player player1 = new Player(new Hand(), "Black");
        Hand hand = player1.getHand();
        hand.addCard(deck.getCards().get(51));
        hand.addCard(deck.getCards().get(47));
        hand.addCard(deck.getCards().get(43));
        hand.addCard(deck.getCards().get(39));
        hand.addCard(deck.getCards().get(35));
        HandScore handScore = new HandScore(hand);
        PokerHand score = handScore.getScore();
        String player1Hand = player1.toString();
        final String HAND = "Black: [AS, KS, QS, JS, TS]";
        assertEquals(PokerHand.ROYAL_FLUSH, score);
        assertEquals(HAND, player1Hand);
    }
    @Test
    public void get_flush_score() {
        Deck deck = new Deck();
        Player player1 = new Player(new Hand(), "Black");
        Hand hand = player1.getHand();
        hand.addCard(deck.getCards().get(0));
        hand.addCard(deck.getCards().get(8));
        hand.addCard(deck.getCards().get(12));
        hand.addCard(deck.getCards().get(20));
        hand.addCard(deck.getCards().get(28));
        HandScore handScore = new HandScore(hand);
        PokerHand score = handScore.getScore();
        String player1Hand = player1.toString();
        final String HAND = "Black: [2C, 4C, 5C, 7C, 9C]";
        assertEquals(PokerHand.FLUSH, score);
        assertEquals(HAND, player1Hand);
    }
    @Test
    public void get_straight_flush_score() {
        Deck deck = new Deck();
        Player player1 = new Player(new Hand(), "Black");
        Hand hand = player1.getHand();
        hand.addCard(deck.getCards().get(0));
        hand.addCard(deck.getCards().get(4));
        hand.addCard(deck.getCards().get(8));
        hand.addCard(deck.getCards().get(12));
        hand.addCard(deck.getCards().get(16));
        HandScore handScore = new HandScore(hand);
        PokerHand score = handScore.getScore();
        String player1Hand = player1.toString();
        final String HAND = "Black: [2C, 3C, 4C, 5C, 6C]";
        assertEquals(PokerHand.STRAIGHT_FLUSH, score);
        assertEquals(HAND, player1Hand);
    }

    @Test
    public void get_straight_score() {
        Deck deck = new Deck();
        Player player1 = new Player(new Hand(), "Black");
        Hand hand = player1.getHand();
        hand.addCard(deck.getCards().get(0));
        hand.addCard(deck.getCards().get(5));
        hand.addCard(deck.getCards().get(10));
        hand.addCard(deck.getCards().get(15));
        hand.addCard(deck.getCards().get(19));
        HandScore handScore = new HandScore(hand);
        PokerHand score = handScore.getScore();
        String player1Hand = player1.toString();
        final String HAND = "Black: [2C, 3D, 4H, 5S, 6S]";
        assertEquals(PokerHand.STRAIGHT, score);
        assertEquals(HAND, player1Hand);
    }

    @Test
    public void get_royal_straight_score() {
        Deck deck = new Deck();
        Player player1 = new Player(new Hand(), "Black");
        Hand hand = player1.getHand();
        hand.addCard(deck.getCards().get(0));
        hand.addCard(deck.getCards().get(5));
        hand.addCard(deck.getCards().get(10));
        hand.addCard(deck.getCards().get(15));
        hand.addCard(deck.getCards().get(19));
        HandScore handScore = new HandScore(hand);
        PokerHand score = handScore.getScore();
        String player1Hand = player1.toString();
        final String HAND = "Black: [2C, 3D, 4H, 5S, 6S]";
        assertEquals(PokerHand.STRAIGHT, score);
        assertEquals(HAND, player1Hand);
    }
    @Test
    public void get_HIGH_CARD_score() {
        Deck deck = new Deck();
        Player player1 = new Player(new Hand(), "Black");
        Hand hand = player1.getHand();
        hand.addCard(deck.getCards().get(0));
        hand.addCard(deck.getCards().get(11));
        hand.addCard(deck.getCards().get(22));
        hand.addCard(deck.getCards().get(33));
        hand.addCard(deck.getCards().get(51));
        HandScore handScore = new HandScore(hand);
        PokerHand score = handScore.getScore();
        String player1Hand = player1.toString();
        final String HAND = "Black: [2C, 4S, 7H, TD, AS]";
        int highCard = handScore.getHighCard();
        assertEquals(PokerHand.HIGH_CARD, score);
        assertEquals(HAND, player1Hand);
        assertEquals(4, highCard);
    }
    @Test
    public void get_ONE_PAIR_score() {
        Deck deck = new Deck();
        Player player1 = new Player(new Hand(), "Black");
        Hand hand = player1.getHand();
        hand.addCard(deck.getCards().get(0));
        hand.addCard(deck.getCards().get(1));
        hand.addCard(deck.getCards().get(10));
        hand.addCard(deck.getCards().get(15));
        hand.addCard(deck.getCards().get(19));
        HandScore handScore = new HandScore(hand);
        PokerHand score = handScore.getScore();
        String player1Hand = player1.toString();
        final String HAND = "Black: [2C, 2D, 4H, 5S, 6S]";
        assertEquals(PokerHand.ONE_PAIR, score);
        assertEquals(HAND, player1Hand);
    }
    @Test
    public void get_TWO_PAIR_score() {
        Deck deck = new Deck();
        Player player1 = new Player(new Hand(), "Black");
        Hand hand = player1.getHand();
        hand.addCard(deck.getCards().get(0));
        hand.addCard(deck.getCards().get(1));
        hand.addCard(deck.getCards().get(10));
        hand.addCard(deck.getCards().get(18));
        hand.addCard(deck.getCards().get(19));
        HandScore handScore = new HandScore(hand);
        PokerHand score = handScore.getScore();
        String player1Hand = player1.toString();
        final String HAND = "Black: [2C, 2D, 4H, 6H, 6S]";
        assertEquals(PokerHand.TWO_PAIR, score);
        assertEquals(HAND, player1Hand);
    }
    @Test
    public void get_three_of_a_kind_score() {
        Deck deck = new Deck();
        Player player1 = new Player(new Hand(), "Black");
        Hand hand = player1.getHand();
        hand.addCard(deck.getCards().get(0));
        hand.addCard(deck.getCards().get(1));
        hand.addCard(deck.getCards().get(2));
        hand.addCard(deck.getCards().get(15));
        hand.addCard(deck.getCards().get(19));
        HandScore handScore = new HandScore(hand);
        PokerHand score = handScore.getScore();
        String player1Hand = player1.toString();
        final String HAND = "Black: [2C, 2D, 2H, 5S, 6S]";
        assertEquals(PokerHand.THREE_OF_A_KIND, score);
        assertEquals(HAND, player1Hand);
    }
    @Test
    public void get_four_of_a_kind_score() {
        final String HAND = "Black: [AS, AH, AD, AC, KS]";
        Deck deck = new Deck();
        Player player1 = new Player(new Hand(), "Black");
        List<Card> cards = deck.getCards(51, 50, 49, 48, 47);
        player1.getHand().addCards(cards);        
        HandScore handScore = new HandScore(player1.getHand());
        assertEquals(PokerHand.FOUR_OF_A_KIND, handScore.getScore());
        assertEquals(HAND, player1.toString());
    }
    @Test
    public void get_full_house_score() {
        final String HAND = "Black: [AS, AH, AD, KS, KH]";
        Deck deck = new Deck();
        Player player1 = new Player(new Hand(), "Black");
        List<Card> cards = deck.getCards(51, 50, 49, 47, 46);
        player1.getHand().addCards(cards);
        HandScore handScore = new HandScore(player1.getHand());
        assertEquals(PokerHand.FULL_HOUSE, handScore.getScore());
        assertEquals(HAND, player1.toString());
    }
    @Test
    public void get_winning_player() {
        Dealer game = new Dealer();
        game.createPlayer("Black");
        game.createPlayer("White");
        Player player1 = game.getPlayers().get(0);
        game.rankHands();
        assertEquals(player1, game.getHandWinner());
    }
}