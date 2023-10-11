import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class DeckBox {

    private int numberOfDecks;
    private static int cardsTotalNumber;
    private ArrayList<Card> decks = new ArrayList<>();

    private static final Random randomNumbers = new Random();

    public DeckBox(int numberOfDecks) {
        DeckOfCards myDeckOfCards = new DeckOfCards();
        this.numberOfDecks = numberOfDecks;
        this.cardsTotalNumber = numberOfDecks * 52;
        for (int i = 0; i < numberOfDecks; i++) {

            myDeckOfCards.shuffle();
            for (Card card : myDeckOfCards.getDeck()) {
                decks.add(card);
            }
        }
        totalShuffle();
    }

    public void totalShuffle() {
        for (int i = 0; i < decks.size(); i++) {
            int newPosition = randomNumbers.nextInt(cardsTotalNumber);
            // Swapping the elements on position i with the new position
            Collections.swap(decks, i, newPosition);
        }
    }


    public ArrayList<Card> deckCopy() {
        return (ArrayList<Card>) decks.clone();

    }

}
