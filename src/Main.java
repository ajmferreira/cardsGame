
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class Main {

    public static final int PRIZE_1000 = 1000;
    public static final int PRIZE_500 = 500;
    public static final int PRIZE_250 = 250;
    public static final int PRIZE_100 = 100;
    public static final int PRIZE_50 = 50;
    public static final int PRIZE_20 = 20;
    public static final int NUMBER_OF_DECKS = 20;

    public static HashMap<Integer, Integer> payout = new HashMap<>();

    private static final Random randomNumbers = new Random();



    public static void main(String[] args) {

        // theoretical probabilistic model
        double[] prizeProbDist = {0.00002439 ,0.00017118, 0.00041511, 0.002567711, 0.003397938, 0.05741403};

        double returnToPlayer;
        int totalWin = 0;
        int bet = 2;

        payout.put(PRIZE_20, 0);
        payout.put(PRIZE_50, 0);
        payout.put(PRIZE_100, 0);
        payout.put(PRIZE_250, 0);
        payout.put(PRIZE_500, 0);
        payout.put(PRIZE_1000, 0);

        //-------------------------------------------------------------------------
        // game configs
        //-------------------------------------------------------------------------

        DeckBox boxWith20 = new DeckBox(NUMBER_OF_DECKS);


        // set the number of spins
        Long numberOfGames = 100_000_000L;
        Long totalBets = numberOfGames * bet;
        System.out.println("Number of games: " + numberOfGames);

        System.out.println("Bet value: " + bet);

        int scene1Count = 0;
        int scene2Count = 0;
        int scene3Count = 0;
        int scene4Count = 0;
        int scene5Count = 0;
        int scene6Count = 0;

        for (long i = 0L; i < numberOfGames; i++) {



            ArrayList<Card> deckCopy = boxWith20.deckCopy();
            Card pickFirst = pickCardFromDeckBox(deckCopy);
            Card pickSecond = pickCardFromDeckBox(deckCopy);
            Card pickThird = pickCardFromDeckBox(deckCopy);

            ArrayList<Card> pick3 = new ArrayList<>();
            pick3.add(pickFirst);
            pick3.add(pickSecond);
            pick3.add(pickThird);

            if (scene1(pick3)) {
                scene1Count++;
                payout.put(PRIZE_1000, scene1Count);
                totalWin += PRIZE_1000;
            } else if (scene2(pick3)) {
                scene2Count++;
                payout.put(PRIZE_500, scene2Count);
                totalWin += PRIZE_500;

            } else if (scene3(pick3)) {
                scene3Count++;
                payout.put(PRIZE_250, scene3Count);
                totalWin += PRIZE_250;

            } else if (scene4(pick3)) {
                scene4Count++;
                payout.put(PRIZE_100, scene4Count);
                totalWin += PRIZE_100;

            } else if (scene5(pick3)) {
                scene5Count++;
                payout.put(PRIZE_50, scene5Count);
                totalWin += PRIZE_50;

            } else if (scene6(pick3)) {
                scene6Count++;
                payout.put(PRIZE_20, scene6Count);
                totalWin += PRIZE_20;
            }

        }



        returnToPlayer = ((double) totalWin / (totalBets)) * 100.0;
        // results printing

        System.out.println("=======================================\n");
        System.out.println("Prizes frequency: ");

        double p20 = (double) payout.get(PRIZE_20) / numberOfGames;
        System.out.format("prize 20 \t-> %d  \t-> p = %4.8f \n", payout.get(PRIZE_20), p20);
        double p50 = (double) payout.get(PRIZE_50) / numberOfGames;
        System.out.format("prize 50 \t-> %d  \t-> p = %4.8f \n", payout.get(PRIZE_50), p50);
        double p100 = (double) payout.get(PRIZE_100) / numberOfGames;
        System.out.format("prize 100 \t-> %d  \t-> p = %4.8f \n", payout.get(PRIZE_100), p100);
        double p250 =(double) payout.get(PRIZE_250) / numberOfGames;
        System.out.format("prize 250 \t-> %d  \t-> p = %4.8f \n", payout.get(PRIZE_250), p250);
        double p500 = (double) payout.get(PRIZE_500) / numberOfGames;
        System.out.format("prize 500 \t-> %d  \t-> p = %4.8f \n", payout.get(PRIZE_500), p500);
        double p1000 = (double) payout.get(PRIZE_1000) / numberOfGames;
        System.out.format("prize 1000 \t-> %d  \t-> p = %4.8f \n", payout.get(PRIZE_1000), p1000);

        System.out.format("total win = %d \n", totalWin);
        System.out.format("total bets = %d \n", totalBets);
        System.out.format("total games = %d \n", numberOfGames);
        System.out.format("RTP = %4.2f \n", returnToPlayer);

        //double[] prizeFreqDist = {p1000,p500,p250,p100,p50,p20};
//        double[] prizeFreqDist = {0.00002459 ,0.00017218, 0.00041611, 0.002560711, 0.003393938, 0.05749403};
//
//        KSTestExample kst = new KSTestExample(prizeProbDist, prizeFreqDist);
//        kst.KSTest();

    }


    /**
     * When the card is removed from the box, the total number of cards should be decremented
     *
     * @param deckBox
     * @return the picked card
     */
    public static Card pickCardFromDeckBox(ArrayList<Card> deckBox) {
        int pick = randomNumbers.nextInt(deckBox.size());
        Card card = deckBox.get(pick);
        deckBox.remove(pick);
        return card;
    }

    /**
     * Scenario 1 : All 3 cards drawn are the number 7 and are the same suit.
     *
     * @param threeCards
     * @return true if all cards are suited sevens
     */
    public static boolean scene1(ArrayList<Card> threeCards) {
        for (Card card : threeCards) {
            if (!card.getFace().equals("7")) {
                return false;
            }
        }
        return areOfSameSuit(threeCards);
    }

    /**
     * Scenario 2 : the 3 cards drawn have values 6, 7 and 8 with the same suit.
     *
     * @param threeCards
     * @return true if all cards are suited sevens
     */
    public static boolean scene2(ArrayList<Card> threeCards) {
        if (!areCards678(threeCards)) {
            return false;
        }
        return areOfSameSuit(threeCards);
    }

    /**
     * To check if the three picked cards have the same suit
     *
     * @param threeCards
     * @return true if the cards are of same suit
     */
    private static boolean areOfSameSuit(ArrayList<Card> threeCards) {
        if (!threeCards.get(0).getSuit().equals(threeCards.get(1).getSuit())) {
            return false;
        } else if (!threeCards.get(0).getSuit().equals(threeCards.get(2).getSuit())) {
            return false;
        } else if (!threeCards.get(1).getSuit().equals(threeCards.get(2).getSuit())) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Scenario 3 : All 3 cards drawn are the number 7 but not of the same suit
     *
     * @param threeCards
     * @return true if all cards are unsuited sevens
     */
    public static boolean scene3(ArrayList<Card> threeCards) {
        for (Card card : threeCards) {
            if (!card.getFace().equals("7")) {
                return false;
            }
        }
        return !areOfSameSuit(threeCards);
    }

    /**
     * Scenario 4 : the 3 cards drawn have values 6, 7 and 8 not all with same suit.
     *
     * @param threeCards
     * @return
     */
    public static boolean scene4(ArrayList<Card> threeCards) {
        if (!areCards678(threeCards)) {
            return false;
        }
        return !areOfSameSuit(threeCards);
    }

    /**
     * check if the three picked cards have face value 6, 7 and 8, resp
     *
     * @param threeCards
     * @return true if all three cards are 6, 7 and 8 (one of each)
     */
    private static boolean areCards678(ArrayList<Card> threeCards) {
        int sum = 0;
        for (Card card : threeCards) {
            if (card.getValue() < 6 || card.getValue() > 8) {
                return false;
            } else {
                sum += card.getValue();
            }
        }
        return sum == 21;
    }

    /**
     * All 3 cards drawn are of the same suit, and the values add up to 21.
     * The cards cannot be three 7’s of the same suit or 6,7, 8 of the same suit.
     *
     * @param threeCards
     * @return
     */
    public static boolean scene5(ArrayList<Card> threeCards) {
        if (scene1(threeCards) || scene2(threeCards)) {
            return false;
        }
        if (!areOfSameSuit(threeCards)) {
            return false;
        }
        int sum = 0;
        for (Card card : threeCards) {
            sum += card.getValue();
        }
        return sum == 21;
    }

    /**
     * The sum of all 3 cards drawn is 20.
     *
     * @param threeCards
     * @return true if sum == 20
     */
    public static boolean scene6(ArrayList<Card> threeCards) {
        int sum = 0;
        for (Card card : threeCards) {
            sum += card.getValue();
        }
        return sum == 20;
    }

}
