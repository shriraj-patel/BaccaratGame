import java.util.ArrayList;

public class BaccaratGameLogic 
{
    public String whoWon(ArrayList<Card> hand1, ArrayList<Card> hand2)
    {
        int playerValue = handTotal(hand1);
        int bankerValue = handTotal(hand2);

        if (playerValue == bankerValue)
        {
            return "Draw";
        }

        if (playerValue == 9)
        {
            return "Player";
        }

        if (bankerValue == 9)
        {
            return "Banker";
        }

        if (playerValue == 8)
        {
            return "Player";
        }

        if (bankerValue == 8)
        {
            return "Banker";
        }

        if (playerValue > bankerValue)
        {
            return "Player";
        }

        if (bankerValue > playerValue)
        {
            return "Banker";
        }

        return null;
    }

    public int handTotal(ArrayList<Card> hand)
    {
        int totalValue = 0;

        for (Card c : hand)
        {
            totalValue = totalValue + c.value;
        }

        if ((totalValue >= 10) && (totalValue <= 19))
        {
            totalValue = totalValue - 10;
        } 
        else if (totalValue >= 20)
        {
            totalValue = totalValue - 20;
        }

        return totalValue;
    }

    public boolean evaluateBankerDraw(ArrayList<Card> hand, Card playerCard)
    {
        if (handTotal(hand) <= 2)
        {
            return true;
        }

        if (handTotal(hand) >= 7)
        {
            return false;
        }

        if ((handTotal(hand) == 3) && ((playerCard.value == 0) || (playerCard.value == 1) || (playerCard.value == 9)))
        {
            return true;
        }

        if ((handTotal(hand) == 4) && ((playerCard.value == 2) || (playerCard.value == 3)))
        {
            return true;
        }

        if ((handTotal(hand) == 5) && ((playerCard.value == 4) || (playerCard.value == 5) || (playerCard == null)))
        {
            return true;
        }

        if ((handTotal(hand) == 6) && ((playerCard.value == 6) || (playerCard.value == 7)))
        {
            return true;
        }

        return false;
    }

    public boolean evaluatePlayerDraw(ArrayList<Card> hand)
    {
        if (handTotal(hand) <= 5)
        {
            return true;
        }

        return false;
    }
}
