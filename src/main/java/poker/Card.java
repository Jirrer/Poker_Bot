package poker;

public class Card {
    private String Suit;
    private int value;

    public Card(String suit, int value) {
        this.Suit = suit;
        this.value = value;
    }

    public String getSuit() { return this.Suit; }
    
    public int getValue() { return this.value; }
}
