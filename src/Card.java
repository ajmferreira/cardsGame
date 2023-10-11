
// Card class represents a playing card.

public class Card {
    private String face; // face of card ("Ace", "Deuce", ...)
    private String suit; // suit of card ("Hearts", "Diamonds", ...)

    private int value;

    // two-argument constructor initializes card's face and suit AND value
    public Card(String cardFace, String cardSuit) {
        face = cardFace;
        suit = cardSuit;
        cardValue();
    }

    @Override
    public String toString() {
        return face + suit;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==this){
            return true;
        }
        if (!(obj instanceof Card)) {
            return false;
        }

        Card c = (Card) obj;
        return this.face.equals(c.face) && this.suit.equals(c.suit);
    }

    public String getFace() {
        return face;
    }

    public String getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    public void cardValue(){
        switch(this.getFace()){
            case "2":
                this.value = 2;
                break;
            case "3":
                this.value = 3;
                break;
            case "4":
                this.value = 4;
                break;
            case "5":
                this.value = 5;
                break;
            case "6":
                this.value = 6;
                break;
            case "7":
                this.value = 7;
                break;
            case "8":
                this.value = 8;
                break;
            case "9":
                this.value = 9;
                break;
            case "10":
                this.value = 10;
                break;
            case "J":
                this.value = 11;
                break;
            case "Q":
                this.value = 12;
                break;
            case "K":
                this.value = 13;
                break;
            default:
                this.value = 1;
        }
    }

}

