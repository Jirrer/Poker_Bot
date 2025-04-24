package poker;

public class Action {
    // advance - 0
    // check - 0
    // call - N

    public String type;
    public int value;
    public int playerCount;
    public int pot;

    public Action(String type, int value, int players, int pot) {
        this.type = type;
        this.value = value;
        this.playerCount = players;
        this.pot = pot;
    }


}
