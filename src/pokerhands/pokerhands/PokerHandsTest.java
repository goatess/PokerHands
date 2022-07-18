package pokerhands;

import static org.junit.Assert.assertEquals;
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
    public void full_card_bundle_values() {

        // arrange
        final String CARD = "2C,2D,2H,2S,3C,3D,3H,3S,4C,4D,4H,4S,5C,5D,5H,5S,6C,6D,6H,6S,7C,7D,7H,7S,8C,8D,8H,8S,9C,9D,9H,9S,TC,TD,TH,TS,JC,JD,JH,JS,QC,QD,QH,QS,KC,KD,KH,KS,AC,AD,AH,AS,";
        Deck deck = new Deck();

        // act
        String actualValue = deck.CardsToString();

        // assert
        assertEquals(CARD, actualValue);

    }

    @Test
    public void each_card_has_a_suit_and_value() {
        // arrange
        final String CARD = "2H";
        Deck deck = new Deck();

        // act
        String actualValue = deck.getCards().get(2).toString();

        // assert
        assertEquals(CARD, actualValue);
    }

    @Test
    public void each_card_has_its_own_suit_and_value() {
        // arrange
        final String CARD = "AS";
        Deck deck = new Deck();

        // act
        String actualValue = deck.getCards().get(51).toString();

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
        final String CARD = "2C,2D,2H,2S,3C,";
        Deck deck;
        Hand hand;

        // act
        deck = new Deck();
        hand = new Hand(deck.getCards());
        String actualValue = hand.CardsToString();

        // assert
        assertEquals(CARD, actualValue);
    }

    @Test
    public void cards_added_to_hand_are_deleted_from_deck() {

        // arrange
        final String CARD = "3D,3H,3S,4C,4D,4H,4S,5C,5D,5H,5S,6C,6D,6H,6S,7C,7D,7H,7S,8C,8D,8H,8S,9C,9D,9H,9S,TC,TD,TH,TS,JC,JD,JH,JS,QC,QD,QH,QS,KC,KD,KH,KS,AC,AD,AH,AS,";
        Deck deck = new Deck();
        int[] indices = { 0, 0, 0, 0, 0 };
        Hand hand = new Hand(deck.removeCards(indices));
        final int CARDS_IN_DECK = deck.getCardsNumber() - hand.getHandNumber();

        // act

        String actualCard = deck.CardsToString();
        int actualCardsInDeck = deck.getCards().size();

        // assert
        assertEquals(CARD, actualCard);
        assertEquals(CARDS_IN_DECK, actualCardsInDeck);
    }

    @Test
    public void shuffle_deck() {
        // arrange
        final String CARDS = "2C,2D,2H,2S,3C,3D,3H,3S,4C,4D,4H,4S,5C,5D,5H,5S,6C,6D,6H,6S,7C,7D,7H,7S,8C,8D,8H,8S,9C,9D,9H,9S,TC,TD,TH,TS,JC,JD,JH,JS,QC,QD,QH,QS,KC,KD,KH,KS,AC,AD,AH,AS,";
        Deck deck = new Deck();

        // act
        deck.shuffleDeck();
        String cardsShuffled = deck.CardsToString();

        // assert
        assertTrue(CARDS, cardsShuffled != CARDS);

    }

    @Test
    public void hand_gets_shuffled_first_5_cards_of_deck() {

        // arrange
        final String CARDS = "2C,2D,2H,2S,3C,";
        Deck deck;
        Hand hand;

        // act
        deck = new Deck();
        deck.shuffleDeck();
        hand = new Hand(deck.getCards());
        String cardsShuffled = hand.CardsToString();

        // assert
        assertTrue(CARDS, cardsShuffled != CARDS);
    }

    @Test
    public void get_2_different_hands_from_the_same_deck() {

        // arrange
        Deck deck = new Deck();
        deck.shuffleDeck();

        // act
        Hand hand = new Hand(deck.getCards());
        final String CARDS_1 = hand.CardsToString();
        Hand hand2 = new Hand(deck.getCards());
        String cards2 = hand2.CardsToString();

        // assert
        assertTrue(CARDS_1, CARDS_1 != cards2);
    }
}
