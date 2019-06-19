/**
 * Created by Jules Aubel on 17/06/19.
 */

package com.tmags.game.Managers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class GameInputProcessor extends InputAdapter {

    public boolean KeyDown(int k) {
        switch(k) {
            case Input.Keys.UP:
                GameKeys.setKey(GameKeys.UP, true);
                break;
            case Input.Keys.DOWN:
                GameKeys.setKey(GameKeys.DOWN, true);
                break;
            case Input.Keys.LEFT:
                GameKeys.setKey(GameKeys.LEFT, true);
                break;
            case Input.Keys.RIGHT:
                GameKeys.setKey(GameKeys.RIGHT, true);
                break;
            case Input.Keys.ESCAPE:
                GameKeys.setKey(GameKeys.ESCAPE, true);
                break;
            case Input.Keys.SPACE:
                GameKeys.setKey(GameKeys.SPACE, true);
                break;
            case Input.Keys.ENTER:
                GameKeys.setKey(GameKeys.ENTER, true);
                break;
            case Input.Keys.SHIFT_LEFT:
                GameKeys.setKey(GameKeys.SHIFT, true);
                break;
        }
        return true;
    }

    public boolean KeyUp(int k) {
        switch(k) {
            case Input.Keys.UP:
                GameKeys.setKey(GameKeys.UP, false);
                break;
            case Input.Keys.DOWN:
                GameKeys.setKey(GameKeys.DOWN, false);
                break;
            case Input.Keys.LEFT:
                GameKeys.setKey(GameKeys.LEFT, false);
                break;
            case Input.Keys.RIGHT:
                GameKeys.setKey(GameKeys.RIGHT, false);
                break;
            case Input.Keys.ESCAPE:
                GameKeys.setKey(GameKeys.ESCAPE, false);
                break;
            case Input.Keys.SPACE:
                GameKeys.setKey(GameKeys.SPACE, false);
                break;
            case Input.Keys.ENTER:
                GameKeys.setKey(GameKeys.ENTER, false);
                break;
            case Input.Keys.SHIFT_LEFT:
                GameKeys.setKey(GameKeys.SHIFT, false);
                break;
        }
        return true;
    }
}
