package trivia;

public interface IPlayerManager {
    public Player getNextPlayer();

    public Player getCurrentPlayer();

    public void addPlayer(String playerName);

    
    public int howManyPlayers();
}
