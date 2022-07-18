import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class PokerHandsTest {
    @Test

    public void there_are_52_cards_in_deck() {
        // arrange
        Deck testDeck = new Deck();
        final int CARDS_IN_DECK = testDeck.getCardsNumber();

        // act
        int actualSize = testDeck.getCards().size();

        // assert
        assertEquals(CARDS_IN_DECK, actualSize);
    }

    @Test
    public void each_card_has_a_suit_and_rank() {
        // arrange
        final String CARD = "2H";
        Deck deck = new Deck();

        // act
        String actualValue = deck.getCards().get(2).toString();

        // assert
        assertEquals(CARD, actualValue);
    }

    @Test
    public void each_card_has_its_own_suit_and_rank() {
        // arrange
        final String CARD = "AS";
        Deck deck = new Deck();

        // act
        String actualValue = deck.getCards().get(51).toString();

        // assert
        assertEquals(CARD, actualValue);
    }

    @Test
    public void each_card_has_its_own_rank() {
        Rank rank = Rank.TWO;

        if (rank == Rank.TWO) {
            rank.toString();
        }

    }

    @Test
    public void full_card_bundle_values() {
        // arrange
        final String CARD = "[2C, 2D, 2H, 2S, 3C, 3D, 3H, 3S, 4C, 4D, 4H, 4S, 5C, 5D, 5H, 5S, 6C, 6D, 6H, 6S, 7C, 7D, 7H, 7S, 8C, 8D, 8H, 8S, 9C, 9D, 9H, 9S, TC, TD, TH, TS, JC, JD, JH, JS, QC, QD, QH, QS, KC, KD, KH, KS, AC, AD, AH, AS]";
        Deck deck = new Deck();

        // act
        String actualValue = deck.toString();

        // assert
        assertEquals(CARD, actualValue);

    }

    @Test
    public void there_are_5_cards_in_hand() {
        // arrange
        Deck cards = new Deck();
        Hand hand = new Hand(cards.getCards());
        final int CARDS_IN_HAND = hand.getHAND_LIMIT();

        // act
        int actualSize = hand.getCards().size();

        // assert
        assertEquals(CARDS_IN_HAND, actualSize);
    }

    @Test
    public void add_to_hand_first_5_cards_of_deck() {
        // arrange
        final String CARD = "[2C, 2D, 2H, 2S, 3C]";
        Deck deck = new Deck();
        Hand hand = new Hand(deck.getCards());

        // act
        String actualValue = hand.toString();

        // assert
        assertEquals(CARD, actualValue);
    }

    @Test
    public void cards_added_to_hand_are_deleted_from_deck() {
        // arrange
        final String CARD = "[3D, 3H, 3S, 4C, 4D, 4H, 4S, 5C, 5D, 5H, 5S, 6C, 6D, 6H, 6S, 7C, 7D, 7H, 7S, 8C, 8D, 8H, 8S, 9C, 9D, 9H, 9S, TC, TD, TH, TS, JC, JD, JH, JS, QC, QD, QH, QS, KC, KD, KH, KS, AC, AD, AH, AS]";
        Deck deck = new Deck();
        int[] indices = { 0, 0, 0, 0, 0 };
        Hand hand = new Hand(deck.removeCards(indices));
        final int CARDS_IN_DECK = deck.getCardsNumber() - hand.getHandNumber();

        // act
        String actualCards = deck.toString();
        int actualCardsInDeck = deck.getCards().size();

        // assert
        assertEquals(CARD, actualCards);
        assertEquals(CARDS_IN_DECK, actualCardsInDeck);
    }

    @Test
    public void shuffle_deck() {
        // arrange
        final String CARDS = "2C,2D,2H,2S,3C,3D,3H,3S,4C,4D,4H,4S,5C,5D,5H,5S,6C,6D,6H,6S,7C,7D,7H,7S,8C,8D,8H,8S,9C,9D,9H,9S,TC,TD,TH,TS,JC,JD,JH,JS,QC,QD,QH,QS,KC,KD,KH,KS,AC,AD,AH,AS,";
        Deck deck = new Deck();

        // act
        deck.shuffleDeck();
        String cardsShuffled = deck.toString();

        // assert
        assertTrue(CARDS, cardsShuffled != CARDS);
    }

    @Test
    public void hand_gets_shuffled_first_5_cards_of_deck() {
        // arrange
        final String CARDS = "2C,2D,2H,2S,3C,";
        Deck deck = new Deck();
        deck.shuffleDeck();
        int[] indices = { 0, 0, 0, 0, 0 };
        Hand hand = new Hand(deck.removeCards(indices));

        // act
        String cardsShuffled = hand.toString();

        // assert
        assertTrue(CARDS, cardsShuffled != CARDS);
    }

    @Test
    public void get_2_different_hands_from_the_same_deck() {
        // arrange
        Deck deck = new Deck();
        deck.shuffleDeck();
        int[] indices = { 0, 0, 0, 0, 0 };
        Hand hand = new Hand(deck.removeCards(indices));
        final String CARDS_1 = hand.toString();

        // act
        Hand hand2 = new Hand(deck.removeCards(indices));
        String cards2 = hand2.toString();

        // assert
        assertTrue(CARDS_1, CARDS_1 != cards2);
    }

    @Test
    public void get_2_players_with_a_hand_of_cards_each_one() {
        // arrange
        Deck deck = new Deck();
        deck.shuffleDeck();
        int[] indices = { 0, 0, 0, 0, 0 };

        // act
        Player player1 = new Player(new Hand(deck.removeCards(indices)), "Black");
        Player player2 = new Player(new Hand(deck.removeCards(indices)), "White");
        String player1Hand = player1.toString();
        String player2Hand = player2.toString();

        // assert
        assertNotEquals(player1Hand, player2Hand);

    }
}
/*
 * High Card: Hands which do not fit any higher category are ranked by the value
 * of their highest card. If the highest cards have the same value, the hands
 * are ranked by the next highest, and so on.
 * 
 * 
 * Pair: 2 of the 5 cards in the hand have the same value. Hands which both
 * contain a pair are ranked by the value of the cards forming the pair. If
 * these values are the same, the hands are ranked by the values of the cards
 * not forming the pair, in decreasing order.
 * 
 * 
 * Two Pairs: The hand contains 2 different pairs. Hands which both contain 2
 * pairs are ranked by the value of their highest pair. Hands with the same
 * highest pair are ranked by the value of their other pair. If these values are
 * the same the hands are ranked by the value of the remaining card.
 * 
 * 
 * Three of a Kind: Three of the cards in the hand have the same value. Hands
 * which both contain three of a kind are ranked by the value of the 3 cards.
 * 
 * 
 * Straight: Hand contains 5 cards with consecutive values. Hands which both
 * contain a straight are ranked by their highest card.
 * 
 * 
 * Flush: Hand contains 5 cards of the same suit. Hands which are both flushes
 * are ranked using the rules for High Card.
 * 
 * 
 * Full House: 3 cards of the same value, with the remaining 2 cards forming a
 * pair. Ranked by the value of the 3 cards.
 * 
 * 
 * Four of a kind: 4 cards with the same value. Ranked by the value of the 4
 * cards.
 * 
 * 
 * Straight flush: 5 cards of the same suit with consecutive values. Ranked by
 * the highest card in the hand.
 * 
 */
