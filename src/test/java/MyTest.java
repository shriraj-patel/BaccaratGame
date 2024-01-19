import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MyTest {

	@Test
	void CardConstructor() 
	{
		Card c = new Card("Hearts", 8);
		assertEquals("Hearts", c.suite);
		assertEquals(8, c.value);
	}

	@Test
	void generateDeck1() 
	{
		BaccaratDealer dealer = new BaccaratDealer();
		dealer.generateDeck();
		assertEquals(52, dealer.deckSize());
	}

	@Test
	void generateDeck2() 
	{
		BaccaratDealer dealer = new BaccaratDealer();
		Card c = new Card("Clubs", 2, "2_of_clubs.png");
		dealer.generateDeck();

		assertEquals(c.suite, dealer.deck.get(0).suite);
		assertEquals(c.value, dealer.deck.get(0).value);
		assertEquals(c.fileName, dealer.deck.get(0).fileName);
	}

	@Test
	void dealHand1() 
	{
		BaccaratDealer dealer = new BaccaratDealer();
		dealer.generateDeck();
		ArrayList<Card> hand = dealer.dealHand();
		assertEquals(2, hand.size());
	}

	@Test
	void dealHand2() 
	{
		BaccaratDealer dealer = new BaccaratDealer();
		dealer.generateDeck();
		Card c1 = dealer.deck.get(0);
		Card c2 = dealer.deck.get(1);
		ArrayList<Card> hand = dealer.dealHand();

		assertEquals(c1.suite, hand.get(0).suite);
		assertEquals(c1.value, hand.get(0).value);
		assertEquals(c1.fileName, hand.get(0).fileName);
		assertEquals(c2.suite, hand.get(1).suite);
		assertEquals(c2.value, hand.get(1).value);
		assertEquals(c2.fileName, hand.get(1).fileName);
	}

	@Test
	void drawOne1() 
	{
		BaccaratDealer dealer = new BaccaratDealer();
		dealer.generateDeck();
		ArrayList<Card> hand = new ArrayList<Card>();
		hand.add(dealer.drawOne());
		assertEquals(1, hand.size());
	}

	@Test
	void drawOne2() 
	{
		BaccaratDealer dealer = new BaccaratDealer();
		dealer.generateDeck();
		ArrayList<Card> hand = new ArrayList<Card>();
		Card c = dealer.deck.get(0);
		hand.add(dealer.drawOne());

		assertEquals(c.suite, hand.get(0).suite);
		assertEquals(c.value, hand.get(0).value);
		assertEquals(c.fileName, hand.get(0).fileName);
	}

	@Test
	void shuffleDeck1() 
	{
		BaccaratDealer dealer = new BaccaratDealer();
		dealer.shuffleDeck();
		assertEquals(52, dealer.deckSize());
	}

	@Test
	void shuffleDeck2() 
	{
		BaccaratDealer dealer1 = new BaccaratDealer();
		dealer1.generateDeck();

		BaccaratDealer dealer2 = new BaccaratDealer();
		dealer2.shuffleDeck();
		assertNotEquals(dealer1.deck, dealer2.deck);
	}

	@Test
	void deckSize1() 
	{
		BaccaratDealer dealer = new BaccaratDealer();
		assertEquals(0, dealer.deckSize());
	}

	@Test
	void deckSize2() 
	{
		BaccaratDealer dealer = new BaccaratDealer();
		dealer.generateDeck();
		dealer.dealHand();
		dealer.drawOne();
		assertEquals(49, dealer.deckSize());
	}

	@Test
	void whoWon1() 
	{
		BaccaratGameLogic gameLogic = new BaccaratGameLogic();
		ArrayList<Card> hand1 = new ArrayList<Card>();
		ArrayList<Card> hand2 = new ArrayList<Card>();
		hand1.add(new Card("Hearts", 6));
		hand1.add(new Card("Spades", 3));
		hand2.add(new Card("Diamonds", 1));
		hand2.add(new Card("Clubs", 6));
		assertEquals("Player", gameLogic.whoWon(hand1, hand2));
	}

	@Test
	void whoWon2() 
	{
		BaccaratGameLogic gameLogic = new BaccaratGameLogic();
		ArrayList<Card> hand1 = new ArrayList<Card>();
		ArrayList<Card> hand2 = new ArrayList<Card>();
		hand1.add(new Card("Hearts", 8));
		hand1.add(new Card("Spades", 6));
		hand2.add(new Card("Diamonds", 9));
		hand2.add(new Card("Clubs", 5));
		assertEquals("Draw", gameLogic.whoWon(hand1, hand2));
	}

	@Test
	void handTotal1() 
	{
		BaccaratGameLogic gameLogic = new BaccaratGameLogic();
		ArrayList<Card> hand1 = new ArrayList<Card>();
		hand1.add(new Card("Hearts", 6));
		hand1.add(new Card("Spades", 3));
		assertEquals(9, gameLogic.handTotal(hand1));
	}

	@Test
	void handTotal2() 
	{
		BaccaratGameLogic gameLogic = new BaccaratGameLogic();
		ArrayList<Card> hand1 = new ArrayList<Card>();
		hand1.add(new Card("Hearts", 6));
		hand1.add(new Card("Spades", 9));
		assertEquals(5, gameLogic.handTotal(hand1));
	}

	@Test
	void evaluateBankerDraw1() 
	{
		BaccaratGameLogic gameLogic = new BaccaratGameLogic();
		ArrayList<Card> hand1 = new ArrayList<Card>();
		hand1.add(new Card("Hearts", 2));
		hand1.add(new Card("Spades", 2));
		Card c = new Card("Diamonds", 3);
		assertTrue(gameLogic.evaluateBankerDraw(hand1, c));
	}

	@Test
	void evaluateBankerDraw2() 
	{
		BaccaratGameLogic gameLogic = new BaccaratGameLogic();
		ArrayList<Card> hand1 = new ArrayList<Card>();
		hand1.add(new Card("Hearts", 4));
		hand1.add(new Card("Spades", 2));
		Card c = new Card("Diamonds", 5);
		assertFalse(gameLogic.evaluateBankerDraw(hand1, c));
	}

	@Test
	void evaluatePlayerDraw1() 
	{
		BaccaratGameLogic gameLogic = new BaccaratGameLogic();
		ArrayList<Card> hand1 = new ArrayList<Card>();
		hand1.add(new Card("Hearts", 3));
		hand1.add(new Card("Spades", 2));
		assertTrue(gameLogic.evaluatePlayerDraw(hand1));
	}

	@Test
	void evaluatePlayerDraw2() 
	{
		BaccaratGameLogic gameLogic = new BaccaratGameLogic();
		ArrayList<Card> hand1 = new ArrayList<Card>();
		hand1.add(new Card("Hearts", 3));
		hand1.add(new Card("Spades", 3));
		assertFalse(gameLogic.evaluatePlayerDraw(hand1));
	}

	@Test
	void evaluateWinnings1() 
	{
		BaccaratGame baccarat = new BaccaratGame();
		baccarat.playerHand.add(new Card("Hearts", 8));
		baccarat.playerHand.add(new Card("Spades", 6));
		baccarat.bankerHand.add(new Card("Diamonds", 9));
		baccarat.bankerHand.add(new Card("Clubs", 5));
		baccarat.betChoice = "Draw";
		baccarat.currentBet = 1000;
		assertEquals(8000, baccarat.evaluateWinnings());
	}

	@Test
	void evaluateWinnings2() 
	{
		BaccaratGame baccarat = new BaccaratGame();
		baccarat.playerHand.add(new Card("Hearts", 9));
		baccarat.playerHand.add(new Card("Spades", 7));
		baccarat.bankerHand.add(new Card("Diamonds", 9));
		baccarat.bankerHand.add(new Card("Clubs", 5));
		baccarat.betChoice = "Banker";
		baccarat.currentBet = 1000;
		assertEquals(-1000, baccarat.evaluateWinnings());
	}

}
