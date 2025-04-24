package poker;

import java.util.Scanner;


// TODO
// add position functionality
public class Main {
    static private void commitAction(Action action) {
        System.out.println("Bot " + action.type + " $" +action.value);
    }

    static private boolean checkValue(int value) {
        if (value > 14 || value < 2) { return false; }
        else {return true; }
    }

    static private boolean checkSuit(String suit) {
        switch (suit) {
            case "Hearts": return true;
            case "Diamonds": return true;
            case "Clubs": return true;
            case "Spades": return true;
            default: return false;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 

        String botPlayStyle = "neutral"; // passive, neutral, aggressive 

        // Pre Flop
        String pocketSuit_1 = "Hearts";         // given from input
        int pocketValue_1 = 10;                  // given from input
        String pocketSuit_2 = "Hearts";       // given from input
        int pocketValue_2 = 9;                  // given from input

        if (!checkValue(pocketValue_1)) { scanner.close(); throw new IllegalArgumentException("Bad Card Value for card 1"); } 
        if (!checkValue(pocketValue_2)) { scanner.close(); throw new IllegalArgumentException("Bad Card Value for card 2"); } 
        if (!checkSuit(pocketSuit_1)) { scanner.close(); throw new IllegalArgumentException("Bad Card Suit for card 1"); } 
        if (!checkSuit(pocketSuit_2)) { scanner.close(); throw new IllegalArgumentException("Bad Card Suit for card 2"); } 


        Card card1 = new Card(pocketSuit_1, pocketValue_1);
        Card card2 = new Card(pocketSuit_2, pocketValue_2);

        Bot bot = new Bot(card1, card2, botPlayStyle, 75, 50, 1000);
        

        while (true) {
            Action inputAction = new Action(scanner.next(), scanner.nextInt(), 0, scanner.nextInt()); // Get Scanner Input
            Action outputAction = bot.decision(inputAction);

            commitAction(outputAction);

            if (inputAction.type.equals("advance") && !outputAction.type.equals("raise")) { break; }        
        }
        

        // Flop
        String cardString_1 = "Clubs";         // given from input
        String cardString_2 = "Clubs";         // given from input
        String cardString_3 = "Spades";        // given from input
        int cardValue_1 = 4;                   // given from input
        int cardValue_2 = 13;                  // given from input
        int cardValue_3 = 10;                  // given from input

        Card communityCard_1 = new Card(cardString_1, cardValue_1);
        Card communityCard_2 = new Card(cardString_2, cardValue_2);
        Card communityCard_3 = new Card(cardString_3, cardValue_3);

        bot.addCard(communityCard_1);
        bot.addCard(communityCard_2);
        bot.addCard(communityCard_3);

        while (true) {
            Action inputAction = new Action(scanner.nextLine(), 0, 0, 75); // Get Scanner Input
            Action outputAction = bot.decision(inputAction);

            commitAction(outputAction);

            if (inputAction.type.equals("advance") && !outputAction.type.equals("raise")) { break; }        
        }

        // Turn
        String cardString_4 = "Hearts";     // given from input
        int cardValue_4 = 14;               // given from input

        Card communityCard_4 = new Card(cardString_4, cardValue_4);

        bot.addCard(communityCard_4);

        while (true) {
            Action inputAction = new Action(scanner.nextLine(), 0, 0, 75); // Get Scanner Input
            Action outputAction = bot.decision(inputAction);

            commitAction(outputAction);

            if (inputAction.type.equals("advance") && !outputAction.type.equals("raise")) { break; }        
        }

        // River
        String cardString_5 = "Diamonds";     // given from input
        int cardValue_5 = 2;                  // given from input

        Card communityCard_5 = new Card(cardString_5, cardValue_5);

        bot.addCard(communityCard_5);

        while (true) {
            Action inputAction = new Action(scanner.nextLine(), 0, 0, 75); // Get Scanner Input
            Action outputAction = bot.decision(inputAction);

            commitAction(outputAction);

            if (inputAction.type.equals("advance") && !outputAction.type.equals("raise")) { break; }        
        }

        scanner.close();
    }
}