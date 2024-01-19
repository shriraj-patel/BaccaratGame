import java.util.ArrayList;
import java.util.Collections;

public class BaccaratDealer 
{
    ArrayList<Card> deck = new ArrayList<Card>();

    public void generateDeck()
    {
        Card c = new Card("Clubs", 2, "2_of_clubs.png");
        deck.add(c);
        c = new Card("Diamonds", 2, "2_of_diamonds.png");
        deck.add(c);
        c = new Card("Hearts", 2, "2_of_hearts.png");
        deck.add(c);
        c = new Card("Spades", 2, "2_of_spades.png");
        deck.add(c);

        c = new Card("Clubs", 3, "3_of_clubs.png");
        deck.add(c);
        c = new Card("Diamonds", 3, "3_of_diamonds.png");
        deck.add(c);
        c = new Card("Hearts", 3, "3_of_hearts.png");
        deck.add(c);
        c = new Card("Spades", 3, "3_of_spades.png");
        deck.add(c);

        c = new Card("Clubs", 4, "4_of_clubs.png");
        deck.add(c);
        c = new Card("Diamonds", 4, "4_of_diamonds.png");
        deck.add(c);
        c = new Card("Hearts", 4, "4_of_hearts.png");
        deck.add(c);
        c = new Card("Spades", 4, "4_of_spades.png");
        deck.add(c);

        c = new Card("Clubs", 5, "5_of_clubs.png");
        deck.add(c);
        c = new Card("Diamonds", 5, "5_of_diamonds.png");
        deck.add(c);
        c = new Card("Hearts", 5, "5_of_hearts.png");
        deck.add(c);
        c = new Card("Spades", 5, "5_of_spades.png");
        deck.add(c);

        c = new Card("Clubs", 6, "6_of_clubs.png");
        deck.add(c);
        c = new Card("Diamonds", 6, "6_of_diamonds.png");
        deck.add(c);
        c = new Card("Hearts", 6, "6_of_hearts.png");
        deck.add(c);
        c = new Card("Spades", 6, "6_of_spades.png");
        deck.add(c);

        c = new Card("Clubs", 7, "7_of_clubs.png");
        deck.add(c);
        c = new Card("Diamonds", 7, "7_of_diamonds.png");
        deck.add(c);
        c = new Card("Hearts", 7, "7_of_hearts.png");
        deck.add(c);
        c = new Card("Spades", 7, "7_of_spades.png");
        deck.add(c);

        c = new Card("Clubs", 8, "8_of_clubs.png");
        deck.add(c);
        c = new Card("Diamonds", 8, "8_of_diamonds.png");
        deck.add(c);
        c = new Card("Hearts", 8, "8_of_hearts.png");
        deck.add(c);
        c = new Card("Spades", 8, "8_of_spades.png");
        deck.add(c);

        c = new Card("Clubs", 9, "9_of_clubs.png");
        deck.add(c);
        c = new Card("Diamonds", 9, "9_of_diamonds.png");
        deck.add(c);
        c = new Card("Hearts", 9, "9_of_hearts.png");
        deck.add(c);
        c = new Card("Spades", 9, "9_of_spades.png");
        deck.add(c);

        c = new Card("Clubs", 0, "10_of_clubs.png");
        deck.add(c);
        c = new Card("Diamonds", 0, "10_of_diamonds.png");
        deck.add(c);
        c = new Card("Hearts", 0, "10_of_hearts.png");
        deck.add(c);
        c = new Card("Spades", 0, "10_of_spades.png");
        deck.add(c);

        c = new Card("Clubs", 1, "ace_of_clubs.png");
        deck.add(c);
        c = new Card("Diamonds", 1, "ace_of_diamonds.png");
        deck.add(c);
        c = new Card("Hearts", 1, "ace_of_hearts.png");
        deck.add(c);
        c = new Card("Spades", 1, "ace_of_spades.png");
        deck.add(c);

        c = new Card("Clubs", 0, "jack_of_clubs.png");
        deck.add(c);
        c = new Card("Diamonds", 0, "jack_of_diamonds.png");
        deck.add(c);
        c = new Card("Hearts", 0, "jack_of_hearts.png");
        deck.add(c);
        c = new Card("Spades", 0, "jack_of_spades.png");
        deck.add(c);

        c = new Card("Clubs", 0, "king_of_clubs.png");
        deck.add(c);
        c = new Card("Diamonds", 0, "king_of_diamonds.png");
        deck.add(c);
        c = new Card("Hearts", 0, "king_of_hearts.png");
        deck.add(c);
        c = new Card("Spades", 0, "king_of_spades.png");
        deck.add(c);

        c = new Card("Clubs", 0, "queen_of_clubs.png");
        deck.add(c);
        c = new Card("Diamonds", 0, "queen_of_diamonds.png");
        deck.add(c);
        c = new Card("Hearts", 0, "queen_of_hearts.png");
        deck.add(c);
        c = new Card("Spades", 0, "queen_of_spades.png");
        deck.add(c);
    }

    public ArrayList<Card> dealHand()
    {
        ArrayList<Card> hand = new ArrayList<Card>();
        hand.add(deck.get(0));
        deck.remove(0);
        hand.add(deck.get(0));
        deck.remove(0);

        return hand;
    }

    public Card drawOne()
    {
        Card c = deck.get(0);
        deck.remove(0);

        return c;
    }

    public void shuffleDeck()
    {
        generateDeck();
        Collections.shuffle(deck);
    }

    public int deckSize()
    {
        return deck.size();
    }
}
