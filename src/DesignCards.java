import javax.annotation.processing.Generated;
import java.util.*;

/**
 * Step 0: how many cards? includes Jokers?
 * Step 1: Deck Cards
 * Step 2: Deck Card. Relationship: involve
 * Step 3: Deck contains List<Card> cards, Card[] cards
 * Step 4: fields & methods.
 *         class Card: value(int), suit(enum: Suit)
 *                     setter, getter
 *         class Deck: ArrayList<Card> cards | Card[]
 *                     id ? (multiple deck)
 *                     constructor to create object -> new ArrayList<Card>(), 2 loop for value & suit
 *                     deal(); shuffle();
 *                     shuffle(): random
 *                     deal(): one card | multiple card | random | specific -> overloading
 * Step 5:
 */

public class DesignCards {
}

enum Suit{
    //    enum suit; // if we can get all possible value, use enum (exhaustive method)
    Heart,
    Diamond,
    Spade,
    Club
}

class Card {
    private final int value;
    private final Suit suit;

    public Card(int value, Suit suit) {
        this.value = value;
        this.suit = suit;
    }

    public int getCardVal() {
        return value;
    }

    public Suit getCardSuit() {
        return suit;
    }

}

class Deck {

    /**
     * shuffle
     * c0, c1, c2, ...c51
     * p0, p1, p2
     * prob = 1/52, or swap inplace manipulate -> right to left for loop
     * random(k)[0, k) -> random.nextInt(k)
     */
    // fields
    private ArrayList<Card> cards;
//    private static final int DEFAULTSIZE = 52;
//    Random random = new Random(); // option 3
    Random random; // option 4

//    Singleton // option 5: all the game should have the same random

    // methods
    public Deck() {
        random = new Random(); // option 4 gaurentee the same random
        cards = new ArrayList<Card>(); // it's only a shell
        for (Suit suit : Suit.values()) {
            for (int i = 1; i <= 13; i++) {
                cards.add(new Card(i, suit));
            }
        }
    }

    public Card deal() {
        return cards.remove(cards.size() - 1); // remove methods return the removed element
    }

    public List<Card> deal(int num) { // reuse deal
        List<Card> dealCards = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            dealCards.add(deal());
        }
        return dealCards;
    }

    public void shuffle() {
//        Random random = new Random(); // option 2: call shuffle() create random all the time
        for (int i = 0; i < cards.size(); i++) {
            // don't use magic number; preventive programming, you can do it from cards.size - 1 to 0
            Random random = new Random(); // option 1
            int j = random.nextInt(cards.size() - i) + i; // call the random all the time
            // swap
            Card card1 = cards.get(i);
            Card card2 = cards.get(j);
            cards.set(i, card2);
            cards.set(j, card1);

        }
    }
}

class Hand {
    // fields
    protected final int id; // Uuid; if will be extended, use protected
    protected ArrayList<Card> handCards;

//    @GenerateId
    public Hand(int id) {
        this.id = id;
        this.handCards = new ArrayList<>();
    }

//    public Hand() { // temporary id
//
//    }

    // add controller to decoupling
    public void addCard(Card card) { // use a third-party controller
        // corner case
        handCards.add(card);
    }

    public void addCard(List<Card> cards) { // List better than ArrayList Universal
        // corner case // protective programming
        for (Card card : cards) {
            addCard(card);
        }
    }

//    public addCards()

    public Card removeCard(int i) {
        // corner case
        if (handCards.size() == 0 || i > handCards.size()) return null;
        return handCards.remove(i);
    }
}

class BackJackHand extends Hand{ // 21 point
    /**
     * 2 ~ 10 score as it's value
     * J,Q,K  score 10
     * A      score either 1 or 11
     */

    public BackJackHand(int id) {
        super(id);
    }

//    public int score() {
//        if (handCards.size() == 0) return 0;
//        int sum = 0;
//        for (Card c : handCards) {
//            int val = c.getCardVal();
//            if (val > 1 && val <=10) { // 2 4 A 3 7, A will first be 11 then be 1, too complicated
//                sum += val;
//            } else if (val == 11 || val == 12 || val == 13) { // J Q K
//                sum += 10;
//            } else { // A what if multiple A wrong
//                if (sum > 10) sum += 1;
//                else sum += 11;
//            }
//        }
//        return sum;
//    }

    private int score() {
        int res = 0, val, aceCount = 0;
        for (Card card : handCards) {
            val = card.getCardVal();
            if (val >= 2 && val <= 10) res += card.getCardVal();
            if (val >= 11 && val <= 13) res += 10;
            if (val == 1) aceCount++;
        }
        for (int i = aceCount; i > 0; i--) { // A can be 11
            int possibleSum = res + 11 * i + aceCount - i;
            if (possibleSum <= 21) {
                return possibleSum;
            }
        }
        return res + aceCount; // aceCount = 0 or A cannot be 11

    }

    public boolean isBursted() {
        return score() > 21;
    }

    public boolean is21() {
        return score() == 21;
    }

    public boolean isBJ() {
        return is21() && handCards.size() == 2;
    }

}

class Game { // extend to runnable application
//    main() {
//        new Deck;
//        new List<BlackJackHand> list;
//
//        deck.shuffle();
//
//        while() {
//            for … list
//                    // UI Window ask if wanna add card or something else
//                    cards = deck.deal();
//            handCard.addCard(cards);
//
//            for … list
//            score hand.score();
//				…
//        }
//    }
}

//class TexasHolder extends Hand {
    // compare method, two tiers, first pattern, second value if the same pattern
//}