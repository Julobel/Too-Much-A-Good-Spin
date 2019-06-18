/**
 * Created by Jules Aubel on 17/06/19.
 */

package com.tmags.game.GameStates;

import com.tmags.game.Managers.GameStateManager;

public abstract class GameState {
    protected GameStateManager gsm;

    protected GameState(GameStateManager gsm) {
        this.gsm = gsm;
    }

    public abstract void init();
    public abstract void update(float dt);
    public abstract void draw();
    public abstract void handleInput();
    public abstract void dispose();
}
