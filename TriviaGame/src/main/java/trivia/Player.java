package trivia;
public class Player {
    private final String name;
    private int position;
    private int goldCoins;
    private boolean inPenaltyBox;

    public Player(String name) {
        this.name = name;
        this.position = 1;
        this.goldCoins = 0;
        this.inPenaltyBox = false;
    }

    public String getName() { return name; }
    public int getPosition() { return position; }
    public int getGoldCoins() { return goldCoins; }
    public boolean isInPenaltyBox() { return inPenaltyBox; }
    

    public void printPosition(){
        System.out.println(getName()
        + "'s new location is "
        + getPosition());
    }

    public void move(int roll) {
        position = (position + roll);
        if (getPosition() > 12) position-=12;;
    }

    public void addGoldCoin() {
        goldCoins++;
    }

    public void setInPenaltyBox(boolean inPenaltyBox) {
        this.inPenaltyBox = inPenaltyBox;
    }
}
