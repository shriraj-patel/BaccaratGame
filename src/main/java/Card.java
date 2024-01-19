public class Card 
{
    String suite;
    String fileName;
    int value;

    public Card(String theSuite, int theValue)
    {
        this.suite = theSuite;
        this.value = theValue;
    }

    public Card(String theSuite, int theValue, String theFileName)
    {
        this.suite = theSuite;
        this.value = theValue;
        this.fileName = theFileName;
    }
}
