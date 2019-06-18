/**
 * Created by Jules Aubel on 17/06/19.
 */

package com.tmags.game.Managers;


import com.tmags.game.GameStates.GameState;
import com.tmags.game.GameStates.MenuState;
import com.tmags.game.GameStates.PlayState;

public class GameStateManager {

    private GameState gameState;
    public static final int MENU = 0;
    public static final int PLAY = 1;
    public static final int SCORE = 2;
    public GameStateManager() {
        setGameState(MENU);
    }

    /**
     * Gestion état du jeu
     * @param state
     */
    public void setGameState(int state) {
        if(gameState != null) gameState.dispose();
        switch(state) {
            case MENU:
                gameState = new MenuState(this);
                break;
            case PLAY:
                gameState = new PlayState(this);
                break;
            case SCORE:
                // TODO Gestion top 10 meilleurs scores
                // gameState = new ScoreState(this);
                break;
            default:
                gameState = new MenuState(this);
                break;
        }
    }

    public void update(float dt) {
        gameState.update(dt);
    }

    public void draw() {
        gameState.draw();
    }
}
