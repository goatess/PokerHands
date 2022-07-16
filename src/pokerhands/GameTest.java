package pokerhands;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GameTest {
    @Test
    public void there_are_52_cards_in_deck(){
        //arrange
        final int CARDS_IN_DECK = 52;
        int actualCardsInDeck = 0;
        PokerGame pokerGame = new PokerGame();
        
        //act
        actualCardsInDeck = pokerGame.getDeckCardNumber();

        //assert
        assertEquals(CARDS_IN_DECK, actualCardsInDeck);
    }

    @Test
    public void each_card_has_a_suit_and_value(){
        //arrange
        final String CARD = "2H";
        String actualCard = "";

        //act
        PokerCard pokerCard = new PokerCard();
        actualCard = pokerCard.getCard();

        //assert
        assertEquals(CARD, actualCard);

/*
Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C AH
Black: 2H 4S 4C 2D 4H  White: 2S 8S AS QS 3S
Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C KH
Black: 2H 3D 5S 9C KD  White: 2D 3H 5C 9S KH
*/

    }
}
