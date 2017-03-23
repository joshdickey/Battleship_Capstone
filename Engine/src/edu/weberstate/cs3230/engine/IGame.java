package edu.weberstate.cs3230.engine;

/**
 * Created by jdickey on 3/13/2017.
 */
public interface IGame {

    void startGame();

    void setBoardSize(int boardSize);

    int getBoardSize();

    void addPlayer(String name);

}
