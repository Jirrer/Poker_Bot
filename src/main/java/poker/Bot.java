package poker;

import java.util.Random;

public class Bot {
    private Card[] pocketCards = new Card[2];
    private Card[] communityCards = new Card[5];
    private int gameRound;
    private String playStyle;
    private int potSize;
    private int blindSize;
    private int moneyLeft;


    private int getPreFlopOdds(Card card1, Card card2) {
        int suited = 0;

        if (card1.getSuit().equals(card2.getSuit())) { suited = 1; }
        PreFlopValue pocketValue = new PreFlopValue(card1.getValue(), card2.getValue(), suited);

        preFlopHandValue handValue = new preFlopHandValue(pocketValue);

        return handValue.handValue;
    }

    private Action preFlop(Action inputAction) {
        String decision;
        int value = 0;

        int pocketOdds = getPreFlopOdds(pocketCards[0], pocketCards[1]);

        int perfectRange = -1;
        int highRange = -1;
        int mediumRange = -1;
        int lowRange = -1;

        if (playStyle.equals("passive")) { perfectRange = 6; highRange = 14; mediumRange = 31; lowRange = 41;}
        if (playStyle.equals("neutral")) { perfectRange = 10; highRange = 26; mediumRange = 38; lowRange = 49;}
        if (playStyle.equals("aggressive")) { perfectRange = 14; highRange = 31; mediumRange = 49; lowRange = 56;}

        int toCall = inputAction.value;
        float callType = toCall / inputAction.pot;
        
        if (toCall == 0) { decision = "check"; }
        else {
            decision = "fold";
            
            if (toCall <= blindSize && pocketOdds >= lowRange) {decision = "Call"; value = toCall; }
            else if (callType < 1.0 && pocketOdds >= mediumRange) {decision = "Call"; value = toCall;}
            else if (toCall < 2.0 && pocketOdds >= highRange) {decision = "Call"; value = toCall;}
            else if (pocketOdds >= perfectRange) {decision = "Call"; value = blindSize;}
        }

        if (potSize == (blindSize * 1.5)) {
            if (pocketOdds >= highRange) {
                decision = "raise";
                value = 1 * potSize;
            }
        } else {
            if (pocketOdds >= perfectRange) {
                Random rand = new Random();

                if (rand.nextInt(5) == 0) {
                    decision = "All-In";
                    value = moneyLeft;
                } else {
                    decision = "raise";
                    value = (int)(potSize * 2.5);
                }

            }
        }

        this.potSize = inputAction.pot;

        return new Action(decision, value, inputAction.playerCount, this.potSize);
    }

    private Action flop(Action inpuAction) {

        return new Action("check", 0, inpuAction.playerCount, this.potSize);
    }

    private Action turn(Action inpuAction) {

        return new Action("check", 0, inpuAction.playerCount, this.potSize);
    }

    private Action river(Action inpuAction) {

        return new Action("check", 0, inpuAction.playerCount, this.potSize);
    }

    private void findGameRound() {
        int round = 0;

        for (Card x : communityCards) {
            if (x != null) { round++; }
        }

        switch (round) {
            case 0: this.gameRound = 0; System.out.println("Starting Pre-Flop"); break;
            case 3: this.gameRound = 1; System.out.println("Starting Flop"); break;
            case 4: this.gameRound = 2; System.out.println("Starting Turn");break;
            case 5: this.gameRound = 3; System.out.println("Starting River");break;
            default: break;
        }
    }

    public Bot(Card card1, Card card2, String playStyle, int potSize, int blindSize, int moneyAmmount) {
        this.pocketCards[0] = card1;
        this.pocketCards[1] = card2;
        this.playStyle = playStyle;
        this.potSize = potSize;
        this.blindSize = blindSize;
        this.moneyLeft = moneyAmmount;

        findGameRound();
    }

    public void addCard(Card card) {
        for (int x = 0; x < 5; x++) {
            if (this.communityCards[x] == null) {
                this.communityCards[x] = card;
                break;
            }
        }

        findGameRound();
    }

    public Action decision(Action inputAction) {
        switch (gameRound) {
            case 0: return preFlop(inputAction);
            case 1: return flop(inputAction);
            case 2: return turn(inputAction);
            case 3: return river(inputAction);
            default: return new Action("fold", 0, 0, this.potSize);
        }
    }
}
