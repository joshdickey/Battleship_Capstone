package edu.weberstate.cs3230;

import edu.weberstate.cs3230.assets.Ship;
import java.util.List;

/**
 * Created by joshd on 1/25/2017.
 */
public class Player {

    private  String name;
    private List<Ship> playerShips;
    private GameBoard gameboard;
    private boolean winner;


    public Player() {
        winner = false;

    }

    public GameBoard getGameboard() {
        return gameboard;
    }

    public void setGameboard(GameBoard gameboard) {
        this.gameboard = gameboard;
    }

    public List<Ship> getPlayerShips() {
        return playerShips;
    }

    public void setPlayerShips(List<Ship> playerShips) {

        this.playerShips = playerShips;
    }

    public  String getName(){
        return name;
    }

    void setName(String name) {
        if (name == null){
            throw new IllegalArgumentException("Name cannot be null");
        }else {
            this.name = name;
        }
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }


    public void removeDestroyedShip() {
        for (int i = 0 ; i < playerShips.size(); i++){
            if(playerShips.get(i).getStatus().equalsIgnoreCase("destroyed")){
                playerShips.remove(i);
                break;
            }

        }

    }
}
