package com.tmags.game.Utils;

public class EnemyDef {
    public String label;
    public String texturePath;
    public String soundEffectPath;
    public Integer textureWidth;
    public Integer textureHeight;
    public Integer hitBoxRadius;

    public EnemyDef(String label, String texturePath, String soundEffectPath, Integer textureWidth, Integer textureHeight, Integer hitBoxRadius) {
        this.label = label;
        this.texturePath = texturePath;
        this.soundEffectPath = soundEffectPath;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.hitBoxRadius = hitBoxRadius;
    }

    public String getSoundEffectPath() {
        return soundEffectPath;
    }

    public void setSoundEffectPath(String soundEffectPath) {
        this.soundEffectPath = soundEffectPath;
    }
}
