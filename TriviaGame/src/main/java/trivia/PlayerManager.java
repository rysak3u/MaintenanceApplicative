package trivia;

import java.util.ArrayList;

public class PlayerManager implements IPlayerManager {

    ArrayList<Player> players;
    int currentPlayer;
    public PlayerManager(){
        players = new ArrayList<Player>();
        currentPlayer = -1; //Initialisation à -1 car on fait le getNextPlayer dans roll à voir si il faut changer
    }

    @Override
    public Player getNextPlayer() {
        currentPlayer +=1;
        if(currentPlayer>= players.size()){
            currentPlayer =0;
        }
        return players.get(currentPlayer);
    }

    @Override
    public Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }

    @Override
    public void addPlayer(String playerName) {
        players.add(new Player(playerName));
    }

    @Override
    public int howManyPlayers() {
        return players.size();
    }
    
}
