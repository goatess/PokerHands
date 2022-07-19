import java.util.Comparator;


public class CardComparator implements Comparator<Card> {
  public int compare(Card c1, Card c2) {
    return c2.getRank().getValue() - c1.getRank().getValue();
  }

  public int compareSuit(Card c1, Card c2) {
    return c2.getSuit().getCode() - c1.getSuit().getCode();
  }
};